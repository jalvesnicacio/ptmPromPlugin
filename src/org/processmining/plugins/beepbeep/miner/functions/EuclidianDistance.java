package org.processmining.plugins.beepbeep.miner.functions;

import java.util.Set;

import org.processmining.plugins.beepbeep.miner.models.Point;

import ca.uqac.lif.cep.Context;
import ca.uqac.lif.cep.functions.Function;

public class EuclidianDistance extends Function {

	public Function duplicate(boolean arg0) {
		return this;
	}

	public void evaluate(Object[] inputs, Object[] outputs, Context arg2) {
		// TODO Auto-generated method stub
		Point p1 = (Point) inputs[0];
		Point p2 = (Point) inputs[1];
		Double distance = Math.sqrt(Math.pow((p2.x - p1.x), 2) + Math.pow((p2.y - p1.y),2));
		outputs[0] = distance;
	}

	public int getInputArity() {
		return 2;
	}

	public void getInputTypesFor(Set<Class<?>> s, int i) {
		if(i == 0) {
			s.add(Point.class);
		}
		if(i == 1) {
			s.add(Point.class);
		}

	}

	public int getOutputArity() {
		return 1;
	}

	public Class<?> getOutputTypeFor(int i) {
		return Double.class;
	}

}
