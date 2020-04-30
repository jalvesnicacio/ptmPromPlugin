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

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Set;

import org.deckfour.xes.info.XLogInfo;
import org.deckfour.xes.model.XAttributeMap;
import org.deckfour.xes.model.XEvent;
import org.deckfour.xes.model.XLog;
import org.deckfour.xes.model.XTrace;

public class BeepBeepLogModel
{

	/**
	 * The list of XTraces to be handled by the plugin
	 */
	protected List<XTrace> traceInstances;

	public BeepBeepLogModel(XLogInfo info)
	{
		XLog log = info.getLog();
		traceInstances = new ArrayList<XTrace>(log.size());
		traceInstances.addAll(log);
	}

	public List<XTrace> getTraceInstances()
	{
		return this.traceInstances;
	}

	public List<String> getAllKeys()
	{
		List<String> elements = new ArrayList<String>();
		Iterator<XEvent> evit = getTraceInstances().get(0).iterator();
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

}
