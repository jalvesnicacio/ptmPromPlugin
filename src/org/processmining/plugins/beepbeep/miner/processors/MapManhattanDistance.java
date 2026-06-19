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

import java.util.Map;
import java.util.TreeSet;

import org.apache.commons.math3.ml.distance.ManhattanDistance;
import org.apache.commons.math3.ml.clustering.DoublePoint;
import ca.uqac.lif.cep.functions.BinaryFunction;

/**
 * Computes the Manhattan distance between two symbol distributions represented
 * as Map<Object, Number> (e.g. produced by valueDistribution / SymbolDistribution).
 *
 * Both maps are projected onto the *union* of their keys so the resulting vectors
 * are aligned position-by-position (a missing key counts as 0). Values are then
 * normalized into proportions so that windows are compared as distributions rather
 * than raw counts.
 */

public class MapManhattanDistance extends BinaryFunction<Map, Map, Number>
{
	public static final MapManhattanDistance instance = new MapManhattanDistance();
	private ManhattanDistance manhattan = new ManhattanDistance();
	private Map lastComputed;   // present window
	private Map lastReference;  // past window

	public MapManhattanDistance()
	{
		super(Map.class, Map.class, Number.class);
	}
	
	public Map getLastReference() { return lastReference; }
	public Map getLastComputed()  { return lastComputed; }

	@Override
	@SuppressWarnings("unchecked")
	public Number getValue(Map map1, Map map2)
	{
		// --- DEBUG (temporary) ---
	    System.out.println("=== MapManhattanDistance INPUT ===");
	    System.out.println("map1 type: " + (map1 != null ? map1.getClass().getName() : "null"));
	    System.out.println("map1 value: " + map1);
	    System.out.println("map2 type: " + (map2 != null ? map2.getClass().getName() : "null"));
	    System.out.println("map2 value: " + map2);
	    System.out.println("===================================");
	    // --- end DEBUG ---
	    this.lastComputed = map1;    // in0 = present (trimmed branch)
	    this.lastReference = map2;   // in1 = past (reference)
		
		// Build the shared, ordered key space (union of both maps' keys)
		TreeSet<String> keys = new TreeSet<String>();
        for (Object k : map1.keySet())
        {
            keys.add(k.toString());
        }
        for (Object k : map2.keySet())
        {
            keys.add(k.toString());
        }
        // Project each map onto the shared key space, normalizing to proportions
        double[] v1 = toAlignedVector(map1, keys);
        double[] v2 = toAlignedVector(map2, keys);
        
        double distance = manhattan.compute(v1, v2);
        
        // --- DEBUG (temporary) ---
        System.out.println("MapManhattanDistance OUTPUT: distance=" + distance);
        // --- end DEBUG --

        return distance;
		
	}
	
	/**
     * Turns a distribution map into a vector aligned to the given ordered key set.
     * Missing keys contribute 0; values are normalized so the vector sums to 1.
     */
    private double[] toAlignedVector(Map map, TreeSet<String> keys)
    {
        // Total over the map's own values, for normalization
        double total = 0;
        for (Object value : map.values())
        {
            total += ((Number) value).doubleValue();
        }

        double[] vector = new double[keys.size()];
        int i = 0;
        for (String key : keys)
        {
            double raw = 0;
            // Look up by string key (maps may use String or other key types)
            for (Object mapKey : map.keySet())
            {
                if (mapKey.toString().equals(key))
                {
                    raw = ((Number) map.get(mapKey)).doubleValue();
                    break;
                }
            }
            vector[i] = total > 0 ? raw / total : 0.0;
            i++;
        }
        return vector;
    }
	
	
	
	
}