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

import ca.uqac.lif.cep.Connector;
import ca.uqac.lif.cep.GroupProcessor;
import ca.uqac.lif.cep.Processor;
import ca.uqac.lif.cep.functions.ApplyFunction;
import ca.uqac.lif.cep.peg.ml.RunningMoments;
import ca.uqac.lif.cep.util.Numbers;

public class BetaRunningMoments extends GroupProcessor implements TrendProcessor
{

	public BetaRunningMoments(int num_moments)
	{
		super(1, 1);
		// Treatment Attribute to Number:
		ApplyFunction treatment = new ApplyFunction(Numbers.numberCast);

		// Vector of moments processor:
		RunningMoments runningMoments = new RunningMoments(num_moments);
		Connector.connect(treatment, runningMoments);

		addProcessors(treatment, runningMoments);
		associateInput(0, treatment, 0);
		associateOutput(0, runningMoments, 0);

	}

	public Processor instantiate()
	{
		return this;
	}

}
