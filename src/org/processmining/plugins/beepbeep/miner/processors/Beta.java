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
package org.processmining.plugins.beepbeep.miner.processors;

import org.processmining.plugins.beepbeep.miner.functions.ExtractAttributeFunction;

import ca.uqac.lif.cep.Connector;
import ca.uqac.lif.cep.GroupProcessor;
import ca.uqac.lif.cep.Processor;
import ca.uqac.lif.cep.functions.ApplyFunction;
import ca.uqac.lif.cep.functions.Constant;
import ca.uqac.lif.cep.functions.FunctionTree;
import ca.uqac.lif.cep.functions.StreamVariable;

public class Beta extends GroupProcessor {
	
	public Beta(String attributeName, TrendProcessor betaFunctionName) {
		super(1,1);
		
		/*
		 * Processor #1 - It takes the event and extracts the attribute chosen by the user. 
		 */
		ExtractAttributeFunction ef = new ExtractAttributeFunction();
		ApplyFunction gama = new ApplyFunction(new FunctionTree(ef,
				new StreamVariable(0),
				new Constant(attributeName)));
		
		/*
		 * Processor #2
		 */
		Processor theta = betaFunctionName.instantiate();
		
		Connector.connect(gama, theta);
		
		associateInput(0, gama, 0);
		associateOutput(0, theta, 0);
		addProcessors(gama,theta);
		
	}

}
