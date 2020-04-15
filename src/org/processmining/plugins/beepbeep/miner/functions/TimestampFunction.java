package org.processmining.plugins.beepbeep.miner.functions;

import java.util.Set;

import org.joda.time.DateTime;
import org.processmining.plugins.beepbeep.miner.models.Event;

import ca.uqac.lif.cep.Context;
import ca.uqac.lif.cep.functions.Function;

/**
	 * Class TimestampFunction
	 * @author jalves
	 *
	 */
	public class TimestampFunction extends Function{

		public Function duplicate(boolean arg0) {
			return new TimestampFunction();
		}

		public void evaluate(Object[] inputs, Object[] outputs, Context arg2) {
			Event event = (Event) inputs[0];
			String timestamp = (String) inputs[1];
			outputs[0] = event.getTimestamp(timestamp);
		}

		public int getInputArity() {
			return 2;
		}

		public void getInputTypesFor(Set<Class<?>> s, int i) {
			if (i == 0)
		        s.add(Event.class);
			if (i == 1)
				s.add(String.class);
		}

		public int getOutputArity() {
			return 1;
		}

		public Class<?> getOutputTypeFor(int i) {
			if (i == 0)
		        return DateTime.class;
			return null;
		}
		
	}