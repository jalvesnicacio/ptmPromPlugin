/*
 * A ProM plugin using BeepBeep
 * Copyright (C) 2020 Jalves Nicacio and friends
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
package org.processmining.plugins.beepbeep.miner;

/**
 * Class TrendReference
 * 
 * @author jalves
 *
 */
public class ReferenceTrend
{
	public enum DataMiningPattern
	{
		SELF_CORRELATED, PATTERN_BASED
	}

	private DataMiningPattern type = DataMiningPattern.SELF_CORRELATED;
	private Number value;
	private String trendAttributeName, trendChoice;

	public ReferenceTrend(String type, Number value)
	{
		if (type == "SELF_CORRELATED")
		{
			this.type = DataMiningPattern.SELF_CORRELATED;
		}
		else
		{
			this.type = DataMiningPattern.PATTERN_BASED;
		}
		this.value = value;
	}

	public Number getValue()
	{
		return this.value;
	}

	public String getAttributeName()
	{
		return trendAttributeName;
	}

	public void setAttributeName(String attributeName)
	{
		this.trendAttributeName = attributeName;
	}

	public String getTrendChoice()
	{
		return trendChoice;
	}

	public void setTrendChoice(String trendChoice)
	{
		this.trendChoice = trendChoice;
	}

	public DataMiningPattern getDataMiningPattern()
	{
		return type;
	}

	public void setDataMiningPattern(DataMiningPattern dataMiningPattern)
	{
		this.type = dataMiningPattern;
	}

	public Number getDataMiningPatternValue()
	{
		return value;
	}

}