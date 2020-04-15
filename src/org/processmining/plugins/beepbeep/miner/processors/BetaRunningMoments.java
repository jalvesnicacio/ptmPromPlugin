package org.processmining.plugins.beepbeep.miner.processors;

import org.processmining.plugins.beepbeep.miner.functions.AttributeToNumber;

import ca.uqac.lif.cep.Connector;
import ca.uqac.lif.cep.GroupProcessor;
import ca.uqac.lif.cep.Processor;
import ca.uqac.lif.cep.functions.ApplyFunction;
import ca.uqac.lif.cep.peg.ml.RunningMoments;

public class BetaRunningMoments extends GroupProcessor implements TrendProcessor {
	
	public BetaRunningMoments(int num_moments) {
		super(1,1);
		//Treatment Attribute to Number:
		ApplyFunction treatment = new ApplyFunction(new AttributeToNumber());
		
		//Vector of moments processor:
		RunningMoments runningMoments = new RunningMoments(num_moments);
		Connector.connect(treatment,runningMoments);
		
		addProcessors(treatment, runningMoments);
	    associateInput(0, treatment, 0);
	    associateOutput(0, runningMoments, 0);
				
	}

	public Processor instantiate() {
		return this;
	}

}
