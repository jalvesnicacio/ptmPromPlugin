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
package org.processmining.plugins.beepbeep.miner.processors;

import org.processmining.plugins.beepbeep.miner.functions.AttributeToNumber;

import ca.uqac.lif.cep.Connector;
import ca.uqac.lif.cep.GroupProcessor;
import ca.uqac.lif.cep.Processor;
import ca.uqac.lif.cep.functions.ApplyFunction;
import ca.uqac.lif.cep.functions.Cumulate;
import ca.uqac.lif.cep.functions.CumulativeFunction;
import ca.uqac.lif.cep.util.Numbers;

public class BetaCumulate extends GroupProcessor implements TrendProcessor
{

	public BetaCumulate()
	{
		super(1, 1);

		ApplyFunction treatment = new ApplyFunction(new AttributeToNumber());
		Cumulate cumulate = new Cumulate(new CumulativeFunction<>(Numbers.addition));

		Connector.connect(treatment, cumulate);

		associateInput(0, treatment, 0);
		associateOutput(0, cumulate, 0);
		addProcessors(treatment, cumulate);
	}

	public Processor instantiate()
	{
		return this;
	}

}
