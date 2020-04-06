package org.processmining.plugins.beepbeep.miner.models.functions;

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