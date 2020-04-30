package org.processmining.plugins.beepbeep.miner;

import java.util.ArrayList;
import java.util.Map;
import java.util.TreeMap;

import org.deckfour.xes.model.XEvent;
import org.processmining.plugins.beepbeep.BeepBeepResult;

public class MapResult extends BeepBeepResult
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
		if (m_results.size() != 0)
		{
			i = m_results.lastKey() + 1;
		}
		else
		{
			i = 1;
		}
		m_results.put(i, entry);
	}

	@Override
	public String toHTMLString(boolean includeHTMLTags)
	{
		StringBuilder buffer = new StringBuilder();
		if (includeHTMLTags)
		{
			buffer.append("<html>");
		}
		buffer.append("<table border='1'>");
		buffer.append("<tr><th colspan='3'>Results</th></tr>");
		buffer.append("<tr><th>Deviation Result</th><th>Event</th><th>Computing Trend</th></tr>");

		for (Map.Entry<Integer,ResultEntry> m : m_results.entrySet())
		{
			ResultEntry rentry = m.getValue();
			Object result = rentry.getM_result();
			ArrayList<XEvent> events = rentry.getM_eventsOfEntry();
			buffer.append("<tr><td>");
			buffer.append(result.toString());
			buffer.append("</td></tr>");
			for (XEvent evt : events)
			{
				buffer.append("<tr><td>");
				buffer.append(evt.toString());
				buffer.append("</td></tr>");
			}

		}
		buffer.append("</table>");
		if (includeHTMLTags)
		{
			buffer.append("</html>");
		}
		return buffer.toString();
	}

	/**
	 * A class to associate each TrendDistance output with the input windows in the
	 * Window processor that runs Beta.
	 * 
	 * @author jalves
	 *
	 */
	public static class ResultEntry
	{
		private Object m_result;
		private ArrayList<XEvent> m_eventsOfEntry = new ArrayList<XEvent>();

		public ResultEntry(Object result)
		{
			this.m_result = result;
		}

		public Object getM_result()
		{
			return m_result;
		}

		public void setM_result(Object m_result)
		{
			this.m_result = m_result;
		}

		public ArrayList<XEvent> getM_eventsOfEntry()
		{
			return m_eventsOfEntry;
		}

		public void setM_eventsOfEntry(ArrayList<XEvent> m_eventsOfEntry)
		{
			this.m_eventsOfEntry = m_eventsOfEntry;
		}

	}
}
