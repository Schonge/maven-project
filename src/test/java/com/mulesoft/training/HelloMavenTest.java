package com.mulesoft.training;

import static org.junit.Assert.*;

import org.junit.Rule;
import org.junit.Test;
import org.mule.tck.junit4.FunctionalTestCase;
import org.mule.tck.junit4.rule.DynamicPort;
import org.mule.api.MuleEvent;

public class HelloMavenTest extends FunctionalTestCase {
	// Use dynamic port at runtime
	@Rule
	public DynamicPort myPort = new DynamicPort("http.port");
	
    @Test
    public void mavenFlowReturnsHelloMaven() throws Exception {
        runFlowAndExpect("mavenFlow", "Hello Maven");
        System.out.println("\n\nTestcase-1 ==========> http port:" + myPort.getNumber() + "\n\n");
    }
    
    @Test
    public void retrieveFlightsAddsAppropriateHeader() throws Exception {
    	System.out.println("\n\nTestcase-2 ==========> http port:" + myPort.getNumber() + "\n\n");
        MuleEvent event = runFlow("retrieveFlights");
        String contentType = event.getMessage().getOutboundProperty("Content-Type");
        assertEquals("application/json", contentType);
    }
    
    @Override
    protected String getConfigFile() {
        return "maven-project.xml";
    }

}
