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
		XEvent e = m_trace.get(m_index++);
		if (e == null)
		{
			return false;
		}
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
