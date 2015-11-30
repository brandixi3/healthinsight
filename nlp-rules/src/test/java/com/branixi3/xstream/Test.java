package com.branixi3.xstream;

import org.drools.core.command.runtime.BatchExecutionCommandImpl;
import org.drools.core.command.runtime.rule.FireAllRulesCommand;
import org.drools.core.command.runtime.rule.InsertObjectCommand;
import org.kie.api.command.BatchExecutionCommand;

import com.brandixi3.i3labs.nlp.model.rule.Demographics;
import com.brandixi3.i3labs.nlp.model.rule.RuleEvent;
import com.google.gson.Gson;
import com.thoughtworks.xstream.XStream;

public class Test {
	
	
	
	public static void main(String[] args) {
        XStream xStream = new XStream();
        RuleEvent event = new RuleEvent();
        
        Demographics demo = new Demographics();
        demo.setFirstName("Dushman");
        event.setDemographics(demo);
        
        
        
 
    	//Gson gson = new Gson();
    	
    	
    	System.out.println(xStream.toXML(event));
    	
    	
    	/*BatchExecutionCommandImpl command = new BatchExecutionCommandImpl();
    	command.setLookup("ksession1");
    	InsertObjectCommand insertObjectCommand = new InsertObjectCommand(event);
    	FireAllRulesCommand fireAllRulesCommand = new FireAllRulesCommand();
    	command.getCommands().add(insertObjectCommand);
    	command.getCommands().add(fireAllRulesCommand);
    	
    	System.out.println(gson.toJsonTree(command));
    	
    	//System.out.println("----------------------------------");
    	
    	System.out.println(xStream.toXML(command));
        */
        
        
        
    }
	

}
