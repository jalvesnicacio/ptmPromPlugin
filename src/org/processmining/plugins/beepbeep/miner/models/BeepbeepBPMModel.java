package org.processmining.plugins.beepbeep.miner.models;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Set;

import org.deckfour.xes.info.XLogInfo;
import org.deckfour.xes.model.XAttributeMap;
import org.deckfour.xes.model.XEvent;
import org.deckfour.xes.model.XTrace;
import org.processmining.framework.util.HTMLToString;

/**
 * OBS.: É possível utilizar as funções compute, computeifAbsent e conputeifPresent nos atributos de XEvent e XTrace. 
 * 		Essas funções utilizam o recurso de BiFunction para implementação de funções genéricas, para qualquer tipo de 
 * 		variável e objetos.
 * @author jalves
 *
 */

public class BeepbeepBPMModel implements HTMLToString{
	private List<Trace> traceInstances = new ArrayList<Trace>();
	
	

	public BeepbeepBPMModel(XLogInfo info) {
		for (XTrace trace : info.getLog()) {
			Trace ti = new Trace(trace);
			this.traceInstances.add(ti);
		}
	}
	
	public List<Trace> getTraceInstances(){
		return this.traceInstances;
	}

	public List<String>getEventAttributes(){
		List<String> elements = new ArrayList<String>();
		List<Event> events = getTraceInstances().get(0).getEvents();
		Iterator<Event> evit = events.iterator();
		if(evit.hasNext()) {
			int i = 0;
			XEvent event = evit.next().getxEvent();
			XAttributeMap attributes = event.getAttributes();
			Set<String> keys = attributes.keySet();
			keys.forEach((String name) -> {
				elements.add(name);
			});
		}
		return elements;
	}

	private void print() {
		for (Trace ti : traceInstances) {
			System.out.println("======= TRACE ======");
			System.out.println(ti.getStringOfXEvents(ti.getEvents()));
			System.out.println("====================");
		}
		
	}


	public String toHTMLString(boolean includeHTMLTags) {
		StringBuffer buffer = new StringBuffer();
		if (includeHTMLTags) {
			buffer.append("<html>");
		}
		buffer.append("<table>");
		buffer.append("<tr>");
		buffer.append("<td>Mining results goes here... come soon</td>");
		
		/*
		 * Head line:
		 
		for (XEventClass eventClass : getEventClasses()) {
			buffer.append("<th>" + eventClass.getId() + "</th>");
		}
		*/
		
		buffer.append("</tr>");
		
		/*
		for (XEventClass fromEventClass : getEventClasses()) {
			buffer.append("<tr>");
			buffer.append("<td>" + fromEventClass.getId() + "</td>");
			for (XEventClass toEventClass : getEventClasses()) {
				buffer.append("<td>" + getDirectSuccession(fromEventClass, toEventClass) + "</td>");
			}
			buffer.append("</tr>");
		}
		*/
		
		buffer.append("</table>");
		if (includeHTMLTags) {
			buffer.append("</html>");
		}
		return buffer.toString();
	}
	
		 

}
