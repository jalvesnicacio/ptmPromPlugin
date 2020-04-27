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

import org.processmining.plugins.beepbeep.models.Attribute;

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
