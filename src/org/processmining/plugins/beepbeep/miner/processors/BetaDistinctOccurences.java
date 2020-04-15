package org.processmining.plugins.beepbeep.miner.processors;

import ca.uqac.lif.cep.GroupProcessor;
import ca.uqac.lif.cep.Processor;

public class BetaDistinctOccurences extends GroupProcessor implements TrendProcessor {
	
	public BetaDistinctOccurences() {
		super(1,1);
	}

	public Processor instantiate() {
		return this;
	}

}
