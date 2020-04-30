/*
 * A ProM plugin using BeepBeep palette for mining event traces
 * Copyright (C) 2020  Sylvain Hallé and friends
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
package ca.uqac.lif.cep.xes;

import java.util.Queue;

import org.deckfour.xes.model.XEvent;
import org.deckfour.xes.model.XTrace;

import ca.uqac.lif.cep.Processor;
import ca.uqac.lif.cep.tmf.Source;

/**
 * Converts an XTrace into a BeepBeep {@link Source} of
 * {@link XEvent}s.
 */
public class XTraceSource extends Source
{
	/**
	 * The trace to extract events from.
	 */
	/*@ non_null @*/ protected XTrace m_trace;
	
	/**
	 * The index of the next event to fetch
	 */
	protected int m_index;
	
	/**
	 * Creates a new XTraceSource.
	 * @param trace The trace to extract events from
	 */
	public XTraceSource(/*@ non_null @*/ XTrace trace)
	{
		super(1);
		m_trace = trace;
		m_index = 0;
	}
	
	@Override
	protected boolean compute(Object[] in, Queue<Object[]> out)
	{
		if (m_index >= m_trace.size())
		{
			return false;
		}
		XEvent e = m_trace.get(m_index++);
		out.add(new Object[] {e});
		return true;
	}
	
	@Override
	public void reset()
	{
		m_index = 0;
	}

	@Override
	public Processor duplicate(boolean with_state)
	{
		XTraceSource xts = new XTraceSource(m_trace);
		if (with_state)
		{
			xts.m_index = m_index;
		}
		return xts; 
	}
	
	@Override
	public Class<?> getOutputType(int index)
	{
		if (index == 0)
		{
			return XEvent.class;
		}
		return null;
	}
}
