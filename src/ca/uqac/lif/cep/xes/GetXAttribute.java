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

import org.deckfour.xes.model.XAttributeMap;
import org.deckfour.xes.model.XEvent;

import ca.uqac.lif.cep.functions.UnaryFunction;

/**
 * Extracts an XAttribute from an XEvent.
 */
public class GetXAttribute extends UnaryFunction<XEvent,String>
{
	/**
	 * The name of the attribute to extract
	 */
	/* @ non_null @ */ protected String m_name;
	
	/**
	 * Creates a new instance of the function.
	 * @param name The name of the attribute to extract
	 */
	public GetXAttribute(String name)
	{
		super(XEvent.class, String.class);
		m_name = name;
	}

	@Override
	public String getValue(XEvent e)
	{
		XAttributeMap map = e.getAttributes();
		if (map.containsKey(m_name))
		{
			return map.get(m_name).toString();
		}
		return null;
	}

	@Override
	public GetXAttribute duplicate(boolean with_state)
	{
		return this;
	}
}
