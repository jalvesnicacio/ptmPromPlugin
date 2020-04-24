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

import java.text.NumberFormat;
import java.text.ParseException;
import java.util.Set;

import src.org.processmining.plugins.beepbeep.miner.models.Attribute;

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
