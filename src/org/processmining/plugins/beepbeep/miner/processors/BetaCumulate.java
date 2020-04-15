package org.processmining.plugins.beepbeep.miner.processors;

import org.processmining.plugins.beepbeep.miner.functions.AttributeToNumber;

import ca.uqac.lif.cep.Connector;
import ca.uqac.lif.cep.GroupProcessor;
import ca.uqac.lif.cep.Processor;
import ca.uqac.lif.cep.functions.ApplyFunction;
import ca.uqac.lif.cep.functions.Cumulate;
import ca.uqac.lif.cep.functions.CumulativeFunction;
import ca.uqac.lif.cep.util.Numbers;

public class BetaCumulate extends GroupProcessor implements TrendProcessor {
	
	public BetaCumulate() {
		super(1,1);
		
		ApplyFunction treatment = new ApplyFunction(new AttributeToNumber());
		Cumulate cumulate = new Cumulate(new CumulativeFunction<>(Numbers.addition));
		
		Connector.connect(treatment,cumulate);
		
		associateInput(0, treatment, 0);
		associateOutput(0, cumulate, 0);
		addProcessors(treatment,cumulate);
	}

	public Processor instantiate() {
		return this;
	}

}
