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
 * 
 * @author jalves
 *
 */

public class BeepbeepBPMModel implements HTMLToString
{
	private List<XTrace> traceInstances = new ArrayList<XTrace>();

	public BeepbeepBPMModel(XLogInfo info)
	{
		traceInstances.addAll(info.getLog());
	}

	public List<XTrace> getTraceInstances()
	{
		return this.traceInstances;
	}

	public List<String> getAllKeys()
	{
		List<String> elements = new ArrayList<String>();
		Iterator<XEvent> evit = traceInstances.get(0).iterator();
		if (evit.hasNext())
		{
			XEvent event = evit.next();
			XAttributeMap attributes = event.getAttributes();
			Set<String> keys = attributes.keySet();
			keys.forEach((String name) -> {
				elements.add(name);
			});
		}
		return elements;
	}

	protected void print()
	{
		for (XTrace ti : traceInstances)
		{
			System.out.println(ti.toString());
		}
	}

	public String toHTMLString(boolean includeHTMLTags)
	{
		return null;

		/*
		 * Head line:
		 * 
		 * for (XEventClass eventClass : getEventClasses()) { buffer.append("<th>" +
		 * eventClass.getId() + "</th>"); }
		 */

		/*
		 * for (XEventClass fromEventClass : getEventClasses()) { buffer.append("<tr>");
		 * buffer.append("<td>" + fromEventClass.getId() + "</td>"); for (XEventClass
		 * toEventClass : getEventClasses()) { buffer.append("<td>" +
		 * getDirectSuccession(fromEventClass, toEventClass) + "</td>"); }
		 * buffer.append("</tr>"); }
		 */

	}

}
