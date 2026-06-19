package org.processmining.plugins.beepbeep.miner;


import java.util.Map;
import java.util.TreeMap;
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
		buffer.append("<table border='1' cellpadding='4' cellspacing='0' width='100%'>");

	    // Title row
		buffer.append("<tr bgcolor='#2c3e50'>");
	    buffer.append("<th colspan='5'><font color='#ffffff'>Trend Deviation Results</font></th>");
	    buffer.append("</tr>");

	    // Header row
	    buffer.append("<tr bgcolor='#dddddd'>");
	    buffer.append("<th>Trace</th>");
	    buffer.append("<th>Reference Trend</th>");
	    buffer.append("<th>Computed Trend</th>");
	    buffer.append("<th>Distance</th>");
	    buffer.append("<th>Deviation</th>");
	    buffer.append("</tr>");

	    // Data rows
	    boolean shade = false;
		for (Map.Entry<Integer,ResultEntry> m : m_results.entrySet())
		{
			ResultEntry rentry = m.getValue();

	        // Alternate row background for readability
	        buffer.append(shade ? "<tr bgcolor='#f5f5f5'>" : "<tr>");
	        shade = !shade;

	        buffer.append("<td align='center'>").append(rentry.getTraceIndex()).append("</td>");
	        buffer.append("<td>").append(rentry.getReferenceTrend()).append("</td>");
	        buffer.append("<td>").append(rentry.getComputedTrend()).append("</td>");

	        // Distance: may be null in pattern-based mode
	        Double dist = rentry.getDistance();
	        buffer.append("<td align='right'>");
	        buffer.append(dist != null ? String.format("%.2f", dist) : "&mdash;");
	        buffer.append("</td>");

	        // Deviation flag, highlighted
	        buffer.append("<td align='center'><font color='#c0392b'><b>");
	        buffer.append(rentry.isDeviation());
	        buffer.append("</b></font></td>");

	        buffer.append("</tr>");

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
	 * @author Jalves Nicacio
	 *
	 */
	public static class ResultEntry
	{
		
		private int traceIndex;
	    private String referenceTrend;
	    private String computedTrend;
	    private Double distance;     // null no pattern-based
	    private boolean deviation;

		
		
	    public ResultEntry(int traceIndex, String referenceTrend, String computedTrend, Double distance, boolean deviation)
		{
			 this.traceIndex = traceIndex;
			 this.referenceTrend = referenceTrend;
			 this.computedTrend = computedTrend;
			 this.distance = distance;
			 this.deviation = deviation;
		}

		public int getTraceIndex() {
			return traceIndex;
		}
		
		public void setTraceIndex(int traceIndex)
	    {
	        this.traceIndex = traceIndex;
	    }

		public String getReferenceTrend() {
			return referenceTrend;
		}
		
		public void setReferenceTrend(String referenceTrend)
	    {
	        this.referenceTrend = referenceTrend;
	    }

		public String getComputedTrend() {
			return computedTrend;
		}
		
		public void setComputedTrend(String computedTrend)
	    {
	        this.computedTrend = computedTrend;
	    }

		public Double getDistance() {
			return distance;
		}
		
		public void setDistance(Double distance)
	    {
	        this.distance = distance;
	    }

		public boolean isDeviation() {
			return deviation;
		}
		
		public void setDeviation(boolean deviation)
	    {
	        this.deviation = deviation;
	    }

	}
}
