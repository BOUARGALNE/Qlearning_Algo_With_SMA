package com.Bouargalne.mas;

import jade.core.Agent;
import jade.core.behaviours.Behaviour;
import jade.core.behaviours.OneShotBehaviour;
import jade.core.behaviours.SequentialBehaviour;
import jade.domain.DFService;
import jade.domain.FIPAAgentManagement.DFAgentDescription;
import jade.domain.FIPAAgentManagement.ServiceDescription;
import jade.domain.FIPAException;
import jade.lang.acl.ACLMessage;

import java.util.Random;

import static com.Bouargalne.mas.QLUtils.*;


public class QlearningAgent extends Agent {
    double[][] qTable = new double[GRID_SIZE*GRID_SIZE][ACTIONS_SIZE];
    int stateI=0;
    int stateJ=0;

    @Override
    public void setup(){
        SequentialBehaviour sb = new SequentialBehaviour();
        sb.addSubBehaviour(new OneShotBehaviour() {
            @Override
            public void action() {
                resetState();
            }
        });
        sb.addSubBehaviour(new Behaviour() {
            int iteration;
            @Override
            public void action() {
                    runQLearning();
            }
            @Override
            public boolean done() {
                return iteration>=MAX_EPOCHS || finished();
            }
        });

        sb.addSubBehaviour(new OneShotBehaviour() {
            @Override
            public void action() {
                //printQTable();
                sendQTable();
            }
        });
        addBehaviour(sb);

    }
    private void resetState(){
        stateI=0;
        stateJ=0;
    }
    private int chooseAction(double eps){
        Random random = new Random();
        double bestQ = 0;
        int act = 0;
        if(random.nextDouble() < eps){
            // Exploration
            act = random.nextInt(ACTIONS_SIZE);
        }else{
            // Exploiatation
            int st = stateI*GRID_SIZE + stateJ;
            for (int i = 0; i < ACTIONS_SIZE; i++) {
                if(qTable[st][i] > bestQ){
                    bestQ = qTable[st][i];
                    act = i;
                }
            }
        }
        return act;
    }

    private boolean finished(){
        return GRID[stateI][stateJ]==1;
    }
    private int executeAction(int action){
        stateI=Math.max(0,Math.min(stateI+ACTIONS[action][0],GRID_SIZE-1));
        stateJ=Math.max(0 ,Math.min(stateJ+ACTIONS[action][1],GRID_SIZE-1));
        return stateI*GRID_SIZE+stateJ;
    }

    private void showResult(){
        System.out.println("Agent "+getAID().getName()+" is starting from ("+stateI+","+stateJ+")");
        System.out.println(" ----- QTable results :");
        for (double []line:qTable){
            System.out.print("[");
            for (double qv :line) {
                System.out.print(qv+" , ");
            }
            System.out.println("]");
        }
        System.out.println("---- Actions : ");
        resetState();
        System.out.println("| States |   Actions    |  ");
        System.out.println("-------------------------");
        while (!finished()){
            int act= chooseAction(0);
            System.out.println("| "+stateI+" | "+stateJ+" |    action : "+act+" |");
            executeAction(act);
        }
    }

    public void sendQTable(){
        DFAgentDescription dfd=new DFAgentDescription();
        ServiceDescription sd=new ServiceDescription();
        sd.setType("QLearning");
        dfd.addServices(sd);
        DFAgentDescription[] result=null;
        try {
            result=DFService.search(this,dfd);
        } catch (FIPAException e) {
            throw new RuntimeException(e);
        }
        ACLMessage msg = new ACLMessage(ACLMessage.INFORM);
        msg.addReceiver(result[0].getName());
        for (double[] doubles : qTable) {
            for (double aDouble : doubles) {
                msg.setContent(msg.getContent() + aDouble + ",");
            }
            msg.setContent(msg.getContent() + "\n");
        }
        //System.out.println("Message sent: "+msg.getContent());
        send(msg);
    }

    @Override
    public void takeDown(){
        try {
            DFService.deregister(this);
        } catch (FIPAException e) {
            throw new RuntimeException(e);
        }
    }
    public void runQLearning(){
        //IN MAS : This is a One Shot BEhavior
        int iteration=0;
        int currentState;
        int newState;
        resetState();
        //This is a Cycling Behavior
        while (iteration<MAX_EPOCHS){
            resetState();
            while (!finished()){
                //Curent state and best action
                currentState=stateI*GRID_SIZE+stateJ;
                int action=chooseAction(0.3);

                //Next state and its best action
                newState=executeAction(action);
                int action2=chooseAction(0);

                //Update Q-Table
                qTable[currentState][action]=qTable[currentState][action] +ALPHA*(GRID[stateI][stateJ] +GAMMA*qTable[newState][action2] -qTable[currentState][action]
                );
            }
            iteration++;
        }
        showResult();
    }

}
