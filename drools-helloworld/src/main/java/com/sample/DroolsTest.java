package com.sample;

import org.kie.api.KieServices;
import org.kie.api.runtime.KieContainer;
import org.kie.api.runtime.KieSession;

/**
 * This is a example of stateful session.
 */
public class DroolsTest {

    public static final void main(String[] args) {
        try {
            // load up the knowledge base
	        KieServices ks = KieServices.Factory.get();
	        //Create the KieContainer which read all files from class path
    	    KieContainer kContainer = ks.getKieClasspathContainer();
    	    
    	    //After compiles all the DRL files found on the class path and put the result of this compilation, a KieModule, in the KieContainer
    	    // If there are no error then go ahead to create the Knowledge Session either Stateful or Stateless base on your scenario
        	KieSession kSession = kContainer.newKieSession("ksession-rules");//created the stateful session
            Message message = new Message();
            message.setMessage("Hello World");
            message.setStatus(Message.HELLO);
            kSession.insert(message);
            kSession.fireAllRules();
        } catch (Throwable t) {
            t.printStackTrace();
        }
    }

    public static class Message {

        public static final int HELLO = 0;
        public static final int GOODBYE = 1;

        private String message;

        private int status;

        public String getMessage() {
            return this.message;
        }

        public void setMessage(String message) {
            this.message = message;
        }

        public int getStatus() {
            return this.status;
        }

        public void setStatus(int status) {
            this.status = status;
        }

    }

}
