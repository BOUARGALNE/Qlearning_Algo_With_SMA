package com.Bouargalne.mas;


import jade.core.ProfileImpl;
import jade.core.Runtime;
import jade.wrapper.AgentContainer;
import jade.wrapper.StaleProxyException;

import static com.Bouargalne.mas.QLUtils.NUMBER_OF_AGENTS;

public class SimpleContainer {
    public static void main(String[] args) throws StaleProxyException {
        Runtime runtime = Runtime.instance();
        ProfileImpl profile = new ProfileImpl();
        profile.setParameter(ProfileImpl.MAIN_HOST, "localhost");
        AgentContainer agentContainer = runtime.createAgentContainer(profile);
        agentContainer.createNewAgent("masterAgent", MasterAgent.class.getName(), new Object[]{}).start();
        for (int i = 0; i < NUMBER_OF_AGENTS; i++) {
            try {
                agentContainer.createNewAgent("agent" + i, QlearningAgent.class.getName(), new Object[]{}).start();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }

    }
}
