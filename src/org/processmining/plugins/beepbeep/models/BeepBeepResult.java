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
import java.util.Map;
import java.util.TreeMap;

import org.processmining.framework.util.HTMLToString;

public class BeepBeepResult implements HTMLToString
{
	
	private TreeMap<Integer, ResultEntry> m_results = new TreeMap<Integer, ResultEntry>();
	
	

	public TreeMap<Integer, ResultEntry> getM_results()
	{
		return m_results;
	}



	public void setM_results(TreeMap<Integer, ResultEntry> m_results)
	{
		this.m_results = m_results;
	}
	
	public void put(ResultEntry entry)
	{
		int i = 0;
		if(m_results.size() != 0) {
			i = m_results.lastKey() + 1;
		} else {
			i = 1;
		}
		m_results.put(i, entry);
	}



	@Override
	public String toHTMLString(boolean includeHTMLTags)
	{
		StringBuffer buffer = new StringBuffer();
		if (includeHTMLTags) {
			buffer.append("<html>");
		}
		buffer.append("<table border='1'>");
		buffer.append("<tr><th colspan='3'>Results</th></tr>");
		buffer.append("<tr><th>Deviation Result</th><th>Event</th><th>Computing Trend</th></tr>");
		
		for(Map.Entry m:m_results.entrySet()){  
			ResultEntry rentry = (ResultEntry)m.getValue();
			Object result = rentry.getM_result();
			ArrayList<Event> events = rentry.getM_eventsOfEntry();
			buffer.append("<tr><td>");buffer.append(result.toString());buffer.append("</td></tr>");
			for(Event evt: events) {
				buffer.append("<tr><td>");buffer.append(evt.toString());buffer.append("</td></tr>");
			}
			
     } 
		buffer.append("</table>");
		if (includeHTMLTags) {
			buffer.append("</html>");
		}
		return buffer.toString();
	}

	
}
