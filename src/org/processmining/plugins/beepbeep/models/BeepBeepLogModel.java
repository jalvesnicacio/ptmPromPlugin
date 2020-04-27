/*
    A ProM plugin using BeepBeep palette for mining event traces
    Copyright (C) 2017-2019 Sylvain Hallé and friends
    
    This program is free software: you can redistribute it and/or modify
    it under the terms of the GNU Lesser General Public License as published
    by the Free Software Foundation, either version 3 of the License, or
    (at your option) any later version.
    
    This program is distributed in the hope that it will be useful,
    but WITHOUT ANY WARRANTY; without even the implied warranty of
    MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
    GNU Lesser General Public License for more details.
    
    You should have received a copy of the GNU Lesser General Public License
    along with this program.  If not, see <http://www.gnu.org/licenses/>.
 */
package org.processmining.plugins.beepbeep.models;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Set;

import org.deckfour.xes.info.XLogInfo;
import org.deckfour.xes.model.XAttributeMap;
import org.deckfour.xes.model.XEvent;
import org.deckfour.xes.model.XTrace;

public class BeepBeepLogModel
{
	private List<Trace> traceInstances = new ArrayList<Trace>();

	public BeepBeepLogModel(XLogInfo info)
	{
		for (XTrace trace : info.getLog()) {
			Trace ti = new Trace(trace);
			this.traceInstances.add(ti);
		}
	}
	
	public List<Trace> getTraceInstances(){
		return this.traceInstances;
	}

	public List<String>getAllKeys(){
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


}
