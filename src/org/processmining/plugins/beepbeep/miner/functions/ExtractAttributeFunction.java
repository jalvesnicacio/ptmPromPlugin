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
import org.processmining.plugins.beepbeep.models.Event;

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
