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

import org.joda.time.DateTime;

import ca.uqac.lif.cep.functions.UnaryFunction;

/**
 * Parses a {@link DateTime} object from a string.
 */
public class ToDateTime extends UnaryFunction<String,DateTime>
{
	/**
	 * A single visible instance of the function.
	 */
	public static final transient ToDateTime instance = new ToDateTime();
	
	/**
	 * Creates a new instance of the function.
	 */
	protected ToDateTime()
	{
		super(String.class, DateTime.class);
	}
	
	@Override
	public DateTime getValue(String v)
	{
		return new DateTime(v);
	}
	
	@Override
	public ToDateTime duplicate(boolean with_state)
	{
		return this;
	}
}
