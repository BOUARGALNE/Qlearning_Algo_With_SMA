package com.Bouargalne.mas;

import jade.core.Agent;
import jade.core.behaviours.CyclicBehaviour;
import jade.domain.DFService;
import jade.domain.FIPAAgentManagement.DFAgentDescription;
import jade.domain.FIPAAgentManagement.ServiceDescription;
import jade.domain.FIPAException;
import jade.lang.acl.ACLMessage;
import static com.Bouargalne.mas.QLUtils.ACTIONS_SIZE;
import static com.Bouargalne.mas.QLUtils.GRID_SIZE;


public class MasterAgent extends Agent {
    protected void setup() {
        DFAgentDescription dfd = new DFAgentDescription();
        dfd.setName(getAID());
        ServiceDescription sd = new ServiceDescription();
        sd.setType("QLearning_SMA");
        sd.setName("masterAgent");
        dfd.addServices(sd);
        try {
            DFService.register(this, dfd);
        }catch (FIPAException e){
            throw new RuntimeException(e);
        }
        addBehaviour(new CyclicBehaviour() {
            @Override
            public void action() {
                ACLMessage receivedMessage = receive();
                double[][] qTable = new double[GRID_SIZE*GRID_SIZE][ACTIONS_SIZE];
                if (receivedMessage != null) {
                    //Receive the Q-TABLE from the agent
                    System.out.println(receivedMessage.getSender().getName());
                    String qTabletring=receivedMessage.getContent();
                    String[] IStates=qTabletring.split("\n");
                    //Remove null in the first row
                    IStates[0]=IStates[0].substring(4);
                    for (int i=0;i<IStates.length;i++){
                        String[] columns=IStates[i].split(",");
                        for (int j=0;j<columns.length;j++){
                            qTable[i][j]=Double.parseDouble(columns[j]);
                        }
                    }
                    //afficher qtable
                    for (int i=0;i<GRID_SIZE*GRID_SIZE;i++) {
                        for (int j = 0; j < ACTIONS_SIZE; j++) {
                            System.out.print(qTable[i][j] + " ");
                        }
                        System.out.println();
                    }

                } else {
                    block();
                }
            }
        });


    }

}