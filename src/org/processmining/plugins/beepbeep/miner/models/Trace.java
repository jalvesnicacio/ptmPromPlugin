package org.processmining.plugins.beepbeep.miner.models;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Map.Entry;

import org.deckfour.xes.model.XAttribute;
import org.deckfour.xes.model.XAttributeMap;
import org.deckfour.xes.model.XEvent;
import org.deckfour.xes.model.XTrace;
import org.processmining.plugins.beepbeep.miner.functions.TimeMinusFunction;
import org.processmining.plugins.beepbeep.miner.functions.TimestampFunction;

import ca.uqac.lif.cep.Connector;
import ca.uqac.lif.cep.Pullable;
import ca.uqac.lif.cep.functions.ApplyFunction;
import ca.uqac.lif.cep.functions.Cumulate;
import ca.uqac.lif.cep.functions.CumulativeFunction;
import ca.uqac.lif.cep.functions.Function;
import ca.uqac.lif.cep.tmf.QueueSource;
import ca.uqac.lif.cep.tmf.WindowFunction;
import ca.uqac.lif.cep.util.Numbers;

public class Trace {

	private XAttributeMap traceAttributes;
	private List<Event> events;
	
	/**
	 * Constructor
	 * @param trace
	 */
	public Trace(XTrace trace) {
		this.traceAttributes = trace.getAttributes();
		this.events = new ArrayList<Event>(); 
		
		for (XEvent xEvent : trace) {
			Event ev = new Event(xEvent);
			this.events.add(ev);
		}
	}
	
	public int getPeriodInDays(String fieldName) {
		
		//Output:
		Number d = 0;
		
		// Processor QS1:
		QueueSource source = new QueueSource(); 
		source.setEvents(events.toArray());
		source.loop(false);
		
		// Processor QS2:
		QueueSource field = new QueueSource().setEvents(fieldName);
		
		//Processor F1 and function f1:
		Function ts = new TimestampFunction();
		ApplyFunction extractTimestamp = new ApplyFunction(ts);
		
		//Processor W(2) and function f2
		Function timeMinus = new TimeMinusFunction();
		WindowFunction win = new WindowFunction(timeMinus);
		
		//Processor F2 and function f3
		Cumulate counterPeriod = new Cumulate(
				new CumulativeFunction<Number>(Numbers.addition));
		
		// -- Connections: --
		Connector.connect(source,0,extractTimestamp,0);
		Connector.connect(field,0,extractTimestamp,1);
		Connector.connect(extractTimestamp,win);
		Connector.connect(win, counterPeriod);
		
		
		// -- execution part --
		Pullable p = counterPeriod.getPullableOutput();
		while(p.hasNext()) {
			d = (Number) p.pull();
		}
		//System.out.println("Trace period: " + d + " hours");
		//System.out.println("Trace period in days: " + d.intValue() / 24);
		return d.intValue() / 24;
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
	 * getEvents
	 * @return set of Event
	 */
	public List<Event> getEvents(){
		return this.events;
	}
	
	/**
	 * toString
	 * @return the trace
	 */
	public String toString() {
		StringBuilder sb = new StringBuilder();
		sb.append(" ------ TRACE ------\n");
		sb.append(getStringOfXAttributeMap("\t", this.traceAttributes));
		this.events.forEach(evt->{
			sb.append("-- Event Info --"+"\n");
			sb.append(evt.toString());
			sb.append("----\n");
		});
		
		return sb.toString();
	}
	
	
	
	
}
