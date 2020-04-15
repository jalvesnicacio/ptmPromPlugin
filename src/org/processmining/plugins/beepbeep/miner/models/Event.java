package org.processmining.plugins.beepbeep.miner.models;

import java.util.Iterator;
import java.util.List;
import java.util.Map.Entry;

import org.deckfour.xes.model.XAttribute;
import org.deckfour.xes.model.XAttributeMap;
import org.deckfour.xes.model.XEvent;
import org.joda.time.DateTime;
import org.ujmp.core.collections.ArrayIndexList;

/**
	 * Class Event: represent a event.
	 * @author jalves
	 *
	 */
	public class Event{
		private XEvent xEvent;
		private List<Attribute> attributes = new ArrayIndexList<Attribute>();
		private TrendReference ComputingTrend;
		private Boolean deviation;

		
		/**
		 * Constructor
		 * creates the event and its attributes
		 * @param xEvent
		 */
		public Event(XEvent xEvent) {
			this.xEvent = xEvent;
			XAttributeMap xAttributesMap = xEvent.getAttributes();
			Iterator<Entry<String, XAttribute>> xAttIt = xAttributesMap.entrySet().iterator();
			while(xAttIt.hasNext()) {
				XAttribute xatt = xAttIt.next().getValue();
				Attribute att = new Attribute(xatt);
				attributes.add(att);
			}
		}
		
		
		/**
		 * toString
		 */
		public String toString() {
			StringBuilder info = new StringBuilder("");
			this.attributes.forEach(att->{
				info.append(att.toString());
			});
			return info.toString();
		}



		public XEvent getxEvent() {
			return xEvent;
		}

		public void setxEvent(XEvent event) {
			this.xEvent = event;
		}
		
		/**
		 * Get the timestamp of the event.
		 * @param field
		 * @return DateTime
		 */
		public DateTime getTimestamp(String field) {
			//Set<String> setAttributes =  event.getAttributes().keySet();
			XAttribute att = this.xEvent.getAttributes().get(field); //field = "time:timestamp";
			DateTime dt = new DateTime(att.toString());
			return dt;
		}


		public Attribute getAttributeByName(String attributeName) {
			for (Attribute att : attributes) {
				if(att.getKey() == attributeName) {
					return att;
				}
			}
			return null;
		}
		
		public void addResult(TrendReference trend, Boolean deviation) {
			this.ComputingTrend = trend;
			this.deviation = deviation;
		}
		
		public TrendReference getComputingTrend() {
			return this.ComputingTrend;
		}
		
		public Boolean getDeviation() {
			return this.deviation;
		}
		
		public void setComputingTrend(TrendReference trend) {
			this.ComputingTrend = trend;
		}
		
		public void setDeviation(Boolean dev) {
			this.deviation = dev;
		}
		
		
	}