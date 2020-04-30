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

import org.deckfour.xes.model.XAttribute;

public class Attribute
{

	private String key;
	private String value;
	private Object className;

	public String getKey()
	{
		return key;
	}

	public void setKey(String key)
	{
		this.key = key;
	}

	public String getValue()
	{
		return value;
	}

	public void setValue(String value)
	{
		this.value = value;
	}

	public Attribute(XAttribute xatt)
	{
		this.key = xatt.getKey();
		this.value = xatt.toString();
		this.className = xatt.getClass();
	}

	public String toString()
	{
		return "\tAttribute [key: " + this.key + "\t | value: " + this.value + "]\n";
	}

	public Object getClassName()
	{
		return className;
	}

	public void setClassName(Object className)
	{
		this.className = className;
	}

}
