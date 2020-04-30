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
package org.processmining.plugins.beepbeep;

import java.util.ArrayList;

import org.deckfour.xes.model.XEvent;

/**
 * A class to associate each TrendDistance output with the input windows in the
 * Window processor that runs Beta.
 * 
 * @author jalves
 *
 */
public class ResultEntry
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
