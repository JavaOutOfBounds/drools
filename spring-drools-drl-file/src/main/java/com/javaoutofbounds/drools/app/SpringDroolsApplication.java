package com.javaoutofbounds.drools.app;

import org.drools.runtime.StatefulKnowledgeSession;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import com.javaoutofbounds.drools.drl.Subscribe;

public class SpringDroolsApplication {

	public static void main(String[] args) {
		//load application context file
		ClassPathXmlApplicationContext applicationContext = new ClassPathXmlApplicationContext(
				"application-context-drools.xml");
		applicationContext.start();
		// create the stateful session
		StatefulKnowledgeSession ksession1 = (StatefulKnowledgeSession) applicationContext.getBean("kStatefulSessionId");
		Subscribe phonePaySubscriber = new Subscribe("Amazon Prime", "PhonePay", false, "22-05-2020", "22-05-2021", 0);
		Subscribe googlePaySub = new Subscribe("Amazon Prime", "GooglePay", false, "22-05-2020", "22-05-2021", 0);
		Subscribe otherSub = new Subscribe("Amazon Prime", "TickToc", false, "22-05-2020", "22-05-2021", 0);
       //store facts into working memory
		ksession1.insert(phonePaySubscriber);
		ksession1.insert(googlePaySub);
		ksession1.insert(otherSub);

		ksession1.fireAllRules();//in Rule Engine pattern matching happen
		 //against production rule with facts from working memory

		System.out.println("Amazon offer sync with GooglePay  : "+googlePaySub.toString());
		System.out.println("Amazon offer sync with PhonePay  : "+phonePaySubscriber.toString());
		System.out.println("Amazon offer sync with TickToc  : "+otherSub.toString());

		applicationContext.stop();

	}

}
