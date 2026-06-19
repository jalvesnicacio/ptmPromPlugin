/*
 * A ProM plugin using BeepBeep
 * Copyright (C) 2020 Sylvain Hallé and friends
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

package org.processmining.plugins.beepbeep.miner.processors;

import ca.uqac.lif.cep.functions.UnaryFunction;
import java.util.List;
import java.util.Map;
import java.util.ArrayList;
import java.util.TreeMap;

import org.apache.commons.math3.ml.clustering.DoublePoint;


/**
 * Converts a Map<Object, Number> (frequency distribution) into a DoublePoint[]
 * for use with distance metrics like Manhattan or Euclidean.
 * 
 * Keys are sorted alphabetically to ensure consistent ordering across comparisons.
 * Values are normalized to proportions (sum = 1.0).
 * 
 * @author jalvesnicacio
 */

public class MapToDistributionVector extends UnaryFunction<Map, DoublePoint> {
	
	public static final MapToDistributionVector instance = new MapToDistributionVector();

	public MapToDistributionVector()
	{
		super(Map.class, DoublePoint.class);
	}

	@Override
	public DoublePoint getValue(Map mapInput)
	{
		if (mapInput == null || mapInput.isEmpty())
		{
			return new DoublePoint(new double[]{});
		}

		// Sort keys alphabetically for consistent ordering
		TreeMap<String, Number> sortedMap = new TreeMap<>();
		for (Object key : mapInput.keySet())
		{
			sortedMap.put(key.toString(), (Number) mapInput.get(key));
		}

		// Calculate total for normalization
		double total = 0;
		for (Number value : sortedMap.values())
		{
			total += value.doubleValue();
		}

		// Convert to normalized double array
		List<Double> vectorList = new ArrayList<>();
		for (Number value : sortedMap.values())
		{
			double normalized = total > 0 ? value.doubleValue() / total : 0.0;
			vectorList.add(normalized);
		}

		// Convert list to array and wrap in DoublePoint
		double[] vector = new double[vectorList.size()];
		for (int i = 0; i < vectorList.size(); i++)
		{
			vector[i] = vectorList.get(i);
		}

		return new DoublePoint(vector);
	}

}
