/*
 * A ProM plugin using BeepBeep palette for mining event traces Copyright (C)
 * 2017-2019 Sylvain Hallé and friends
 * 
 * This program is free software: you can redistribute it and/or modify it under
 * the terms of the GNU Lesser General Public License as published by the Free
 * Software Foundation, either version 3 of the License, or (at your option) any
 * later version.
 * 
 * This program is distributed in the hope that it will be useful, but WITHOUT
 * ANY WARRANTY; without even the implied warranty of MERCHANTABILITY or FITNESS
 * FOR A PARTICULAR PURPOSE. See the GNU Lesser General Public License for more
 * details.
 * 
 * You should have received a copy of the GNU Lesser General Public License
 * along with this program. If not, see <http://www.gnu.org/licenses/>.
 */
package org.processmining.plugins.beepbeep.models;

import java.util.Iterator;
import java.util.List;
import java.util.Map.Entry;

import org.deckfour.xes.model.XAttribute;
import org.deckfour.xes.model.XAttributeMap;
import org.deckfour.xes.model.XEvent;
import org.joda.time.DateTime;
import org.processmining.plugins.beepbeep.miner.models.ReferenceTrend;
import org.ujmp.core.collections.ArrayIndexList;

/**
 * Class Event: represent a event.
 * 
 * @author jalves
 *
 */
public class Event
{
	private XEvent xEvent;
	private List<Attribute> attributes = new ArrayIndexList<Attribute>();

	/**
	 * Constructor creates the event and its attributes
	 * 
	 * @param xEvent
	 */
	public Event(XEvent xEvent)
	{
		this.xEvent = xEvent;
		XAttributeMap xAttributesMap = xEvent.getAttributes();
		Iterator<Entry<String, XAttribute>> xAttIt = xAttributesMap.entrySet().iterator();
		while (xAttIt.hasNext())
		{
			XAttribute xatt = xAttIt.next().getValue();
			Attribute att = new Attribute(xatt);
			attributes.add(att);
		}
	}

	/**
	 * toString
	 */
	public String toString()
	{
		StringBuilder info = new StringBuilder("");
		this.attributes.forEach(att -> {
			info.append(att.toString());
		});
		return info.toString();
	}

	public XEvent getxEvent()
	{
		return xEvent;
	}

	public void setxEvent(XEvent event)
	{
		this.xEvent = event;
	}

	/**
	 * Get the timestamp of the event.
	 * 
	 * @param field
	 * @return DateTime
	 */
	public DateTime getTimestamp(String field)
	{
		// Set<String> setAttributes = event.getAttributes().keySet();
		XAttribute att = this.xEvent.getAttributes().get(field); // field = "time:timestamp";
		DateTime dt = new DateTime(att.toString());
		return dt;
	}

	public Attribute getAttributeByName(String attributeName)
	{
		for (Attribute att : attributes)
		{
			if (att.getKey() == attributeName)
			{
				return att;
			}
		}
		return null;
	}

}