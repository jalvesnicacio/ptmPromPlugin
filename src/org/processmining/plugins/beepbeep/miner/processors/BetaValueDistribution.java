/*
    A ProM plugin using BeepBeep palette for mining event traces
    Copyright (C) 2017-2019 Sylvain Hallé and friends
    
    This program is free software: you can redistribute it and/or modify
    it under the terms of the GNU Lesser General Public License as published
    by the Free Software Foundation, either version 3 of the License, or
    (at your option) any later version.
    
    This program is distributed in the hope that it will be useful,
    but WITHOUT ANY WARRANTY; without even the implied warranty of
    MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
    GNU Lesser General Public License for more details.
    
    You should have received a copy of the GNU Lesser General Public License
    along with this program.  If not, see <http://www.gnu.org/licenses/>.
 */
package src.org.processmining.plugins.beepbeep.miner.processors;

import src.org.processmining.plugins.beepbeep.miner.functions.AttributeToString;

import ca.uqac.lif.cep.Connector;
import ca.uqac.lif.cep.GroupProcessor;
import ca.uqac.lif.cep.Processor;
import ca.uqac.lif.cep.functions.ApplyFunction;

/**
 * Transforms the attribute value into a string and calculates the number of repetitions of this value
 * @author jalves
 *
 */

public class BetaValueDistribution extends GroupProcessor implements TrendProcessor {
	
	public BetaValueDistribution() {
		super(1,1);
		
		ApplyFunction treatment = new ApplyFunction(new AttributeToString());
		SymbolDistribution symbolDistribution = new SymbolDistribution();
		
		Connector.connect(treatment,symbolDistribution);
		
		addProcessors(treatment,symbolDistribution);
		associateInput(0, treatment, 0);
		associateOutput(0, symbolDistribution, 0);
		
	}

	public Processor instantiate() {
		return this;
	}

}
