package org.processmining.plugins.beepbeep.miner.processors;

import org.processmining.plugins.beepbeep.miner.functions.AttributeToString;

import ca.uqac.lif.cep.Connector;
import ca.uqac.lif.cep.GroupProcessor;
import ca.uqac.lif.cep.Processor;
import ca.uqac.lif.cep.functions.ApplyFunction;

public class BetaValueDistribution extends GroupProcessor implements TrendProcessor {
	
	public BetaValueDistribution() {
		super(1,1);
		
		ApplyFunction treatment = new ApplyFunction(new AttributeToString());
		SymbolDistribution symbolDistribution = new SymbolDistribution();
		
		Connector.connect(treatment,symbolDistribution);
		
		addProcessors(treatment,symbolDistribution);
		associateInput(0, treatment, 0);
		associateOutput(0, symbolDistribution, 0);
		
	}

	public Processor instantiate() {
		return this;
	}

}
