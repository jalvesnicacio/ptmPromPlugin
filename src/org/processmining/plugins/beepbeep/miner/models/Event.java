package org.processmining.plugins.beepbeep.miner.models;

import org.deckfour.xes.model.XAttribute;
import org.deckfour.xes.model.XEvent;
import org.joda.time.DateTime;

/**
	 * Class Event: represent a event.
	 * @author jalves
	 *
	 */
	public class Event{
		private XEvent xEvent;

		public Event(XEvent xEvent) {
			this.xEvent = xEvent;
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
		
		
	}