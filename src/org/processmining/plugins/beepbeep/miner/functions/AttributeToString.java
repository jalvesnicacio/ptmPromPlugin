package org.processmining.plugins.beepbeep.miner.functions;

import java.util.Set;

import org.processmining.plugins.beepbeep.miner.models.Attribute;

import ca.uqac.lif.cep.Context;
import ca.uqac.lif.cep.functions.Function;

public class AttributeToString extends Function {

	public Function duplicate(boolean arg0) {
		return new AttributeToString();
	}

	public void evaluate(Object[] inputs, Object[] outputs, Context arg2) {
		Attribute att = (Attribute) inputs[0];
		String value = "";
		value =  att.getValue();
		outputs[0] = value;
	}

	public int getInputArity() {
		return 1;
	}

	public void getInputTypesFor(Set<Class<?>> s, int i) {
		if(i == 0) {
			s.add(Attribute.class);
		}

	}

	public int getOutputArity() {
		// TODO Auto-generated method stub
		return 1;
	}

	public Class<?> getOutputTypeFor(int i) {
		if (i == 0) {
			return String.class;
		}
		return null;
	}

}
