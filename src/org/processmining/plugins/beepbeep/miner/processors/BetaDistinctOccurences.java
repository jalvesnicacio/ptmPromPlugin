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
package org.processmining.plugins.beepbeep.miner.processors;


import ca.uqac.lif.cep.GroupProcessor;
import ca.uqac.lif.cep.functions.ApplyFunction;
import ca.uqac.lif.cep.tmf.Passthrough;

import ca.uqac.lif.cep.Connector;

public class BetaDistinctOccurences extends GroupProcessor
{
	public BetaDistinctOccurences()
	{
		super(1, 1);
		// Passthrough to receive attribute value (string)
        Passthrough treatment = new Passthrough();
        
        // SymbolDistribution counts occurrences of each symbol.
        // Result: Map<Object, Number> example: {NEW=3, FIN=2, RELEASE=1}
        SymbolDistribution symbolDistribution = new SymbolDistribution();
        
        // Maps.Size extracts map size = number of distinct symbols
        ApplyFunction size = new ApplyFunction(MapSize.instance);
        
        Connector.connect(treatment, symbolDistribution);
        Connector.connect(symbolDistribution, size);
        
        addProcessors(treatment, symbolDistribution, size);
        associateInput(0, treatment, 0);
        associateOutput(0, size, 0);
	}

}
