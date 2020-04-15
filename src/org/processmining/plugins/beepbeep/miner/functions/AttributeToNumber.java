package org.processmining.plugins.beepbeep.miner.functions;

import java.text.NumberFormat;
import java.text.ParseException;
import java.util.Set;

import org.processmining.plugins.beepbeep.miner.models.Attribute;

import ca.uqac.lif.cep.Context;
import ca.uqac.lif.cep.functions.Function;

public class AttributeToNumber extends Function {

	public Function duplicate(boolean arg0) {
		return new AttributeToNumber();
	}

	public void evaluate(Object[] inputs, Object[] outputs, Context arg2) {
		// TODO Auto-generated method stub
		Attribute att = (Attribute) inputs[0];
		Number value = 0;
		try {
			value =  NumberFormat.getInstance().parse(att.getValue());
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
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
		return 1;
	}

	public Class<?> getOutputTypeFor(int i) {
		if (i == 0) {
			return Number.class;
		}
		return null;
	}

}
