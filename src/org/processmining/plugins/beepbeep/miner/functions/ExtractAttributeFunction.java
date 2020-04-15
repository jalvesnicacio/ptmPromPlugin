package org.processmining.plugins.beepbeep.miner.functions;

import java.util.Set;

import org.processmining.plugins.beepbeep.miner.models.Attribute;
import org.processmining.plugins.beepbeep.miner.models.Event;

import ca.uqac.lif.cep.Context;
import ca.uqac.lif.cep.functions.Function;

public class ExtractAttributeFunction extends Function {

	@Override
	public Class<?> getOutputTypeFor(int i) {
		if (i == 0) {
			return Attribute.class;
		}
		return null;
	}
	
	@Override
	public int getOutputArity() {
		return 1;
	}
	
	@Override
	public void getInputTypesFor(Set<Class<?>> s, int i) {
		if(i == 0) {
			s.add(Event.class);
		}
		if(i == 1) {
			s.add(String.class);
		}
	}
	
	@Override
	public int getInputArity() {
		return 2;
	}
	
	@Override
	public void evaluate(Object[] inputs, Object[] outputs, Context arg2) {
		String attributeName = (String) inputs[1];
		Event evt = (Event) inputs[0];
		Attribute att = evt.getAttributeByName(attributeName);
		outputs[0] = att;
	}
	
	@Override
	public Function duplicate(boolean arg0) {
		return new ExtractAttributeFunction();
	}

}
