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
package src.org.processmining.plugins.beepbeep.miner.functions;

import java.util.Set;

import org.apache.commons.math3.geometry.Point;

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
		p2.
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
