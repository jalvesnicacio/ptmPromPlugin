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

import org.joda.time.DateTime;
import org.joda.time.Hours;

import ca.uqac.lif.cep.Context;
import ca.uqac.lif.cep.functions.Function;

/**
	 * this class calculates the duration of an event
	 * @author jalves
	 *
	 */
	public class TimeMinusFunction extends Function{

		public Function duplicate(boolean arg0) {
			return new TimeMinusFunction();
		}
		
		/**
		 * returns an integer representing the numbers of hours
		 */
		public void evaluate(Object[] inputs, Object[] outputs, Context arg2) {
			DateTime start, end;
			start = (DateTime) inputs[0];
			end = (DateTime) inputs[1];
			outputs[0] = Hours.hoursBetween(start, end).getHours();
		}

		public int getInputArity() {
			return 2;
		}

		public void getInputTypesFor(Set<Class<?>> s, int i) {
			if (i == 0 || i == 1)
		        s.add(DateTime.class);
		}

		public int getOutputArity() {
			return 1;
		}

		public Class<?> getOutputTypeFor(int i) {
			if (i == 0)
		        return Hours.class;
			return null;
		}
		
	}