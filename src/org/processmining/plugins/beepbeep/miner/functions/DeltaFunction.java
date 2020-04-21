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
