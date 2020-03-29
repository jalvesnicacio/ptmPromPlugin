package org.processmining.plugins.beepbeep.miner.models;

import java.util.HashMap;
import java.util.Iterator;
import java.util.Map.Entry;

import org.deckfour.xes.model.XAttribute;
import org.deckfour.xes.model.XAttributeMap;
import org.deckfour.xes.model.XEvent;
import org.deckfour.xes.model.XTrace;

public class TraceInstance {

	private XAttributeMap traceAttributes;
	private HashMap<Integer, XEvent>  events;
	
	public TraceInstance(XTrace trace) {
		this.traceAttributes = trace.getAttributes();
		this.events = new HashMap<Integer, XEvent>();
		
		for (XEvent event : trace) {
			this.events.put(event.hashCode(), event);
		}
	}
	

	/**
	 * Creates a string with information about all Trace attributes
	 * @param tab
	 * @param xattmap
	 * @return
	 */
	
	public StringBuilder getStringOfXAttributeMap(String tab, XAttributeMap xattmap){
			Iterator<Entry<String, XAttribute>> atit = xattmap.entrySet().iterator();
			StringBuilder info = new StringBuilder("");
			while(atit.hasNext()){
				Entry<String, XAttribute> xae = atit.next();
				XAttribute xa = xae.getValue();
				info.append(tab+"[Attributes=> "+ "Key: \t"+xa.getKey()+" | value: \t"+xa+"]\n");
			}
			return info;
	}
	
	/**
	 * Creates a string with information about all elements of event
	 * @param <T>
	 * @param events
	 * @return
	 */
	public <T extends XEvent> StringBuilder getStringOfXEvents(HashMap<Integer, T> events){
		Iterator<Entry<Integer, T>> evit = events.entrySet().iterator();
		StringBuilder info = new StringBuilder("");
		
		while(evit.hasNext()){
			Entry<Integer, T> xee = evit.next();
			XEvent xe = xee.getValue();
			
			info.append("-- Event Info --"+"\n");
			info.append("XEvent URI: \t"+xee.getKey()+"\n");

			//handling event's attributes
			XAttributeMap xattmap = xe.getAttributes();
			info.append(getStringOfXAttributeMap("\t", xattmap));
			//END OF handling event's attributes
			info.append("----\n");
		}
		
		return 	info;
	}
	
	public HashMap<Integer, XEvent> getEvents(){
		return this.events;
	}
	
	public StringBuilder printTraceAttributes() {
		StringBuilder sb = new StringBuilder();
		sb = getStringOfXAttributeMap("\t", this.traceAttributes);
		return sb;
	}
}
