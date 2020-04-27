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
package org.processmining.plugins.beepbeep.miner.functions;
import java.util.Set;

import org.processmining.plugins.beepbeep.miner.models.ReferenceTrend;

import ca.uqac.lif.cep.Context;
import ca.uqac.lif.cep.functions.Function;

public class DeltaFunction extends Function {
	Function deltaOpt;
	
	public DeltaFunction(Function d) {
		this.deltaOpt = d;
	}

	@Override
	public Function duplicate(boolean arg0) {
		return new DeltaFunction(this.deltaOpt);
	}

	@Override
	public void evaluate(Object[] inputs, Object[] outputs, Context arg2) {
		ReferenceTrend tr = (ReferenceTrend) inputs[0];
		Number pattern = tr.getValue();
		inputs[0] = pattern;
		deltaOpt.evaluate(inputs, outputs);
	}

	@Override
	public int getInputArity() {
		return 2;
	}

	@Override
	public void getInputTypesFor(Set<Class<?>> s, int i) {
		// TODO Auto-generated method stub
		if(i == 0) {
			s.add(ReferenceTrend.class);
		}
		if (i == 1) {
			s.add(Object.class);
		}
	}

	@Override
	public int getOutputArity() {
		return 1;
	}

	@Override
	public Class<?> getOutputTypeFor(int arg0) {
		return Number.class;
	}

}
