package org.processmining.plugins.beepbeep.miner.processors;

import static ca.uqac.lif.cep.Connector.BOTTOM;
import static ca.uqac.lif.cep.Connector.INPUT;
import static ca.uqac.lif.cep.Connector.LEFT;
import static ca.uqac.lif.cep.Connector.OUTPUT;
import static ca.uqac.lif.cep.Connector.RIGHT;
import static ca.uqac.lif.cep.Connector.TOP;

import org.processmining.plugins.beepbeep.miner.functions.AttributeToNumber;

import ca.uqac.lif.cep.Connector;
import ca.uqac.lif.cep.GroupProcessor;
import ca.uqac.lif.cep.Processor;
import ca.uqac.lif.cep.functions.ApplyFunction;
import ca.uqac.lif.cep.functions.Cumulate;
import ca.uqac.lif.cep.functions.CumulativeFunction;
import ca.uqac.lif.cep.functions.TurnInto;
import ca.uqac.lif.cep.tmf.Fork;
import ca.uqac.lif.cep.util.Numbers;



public class BetaRunningAverage extends GroupProcessor implements TrendProcessor {
	
	public BetaRunningAverage() {
		super(1,1);
		
		//Treatment Attribute to Number:
		ApplyFunction treatment = new ApplyFunction(new AttributeToNumber());
		
		//Running Average Group Processor:
		GroupProcessor runningAverage = new GroupProcessor(1, 1);
		{
		    Fork fork = new Fork(2);
		    Cumulate sum_proc = new Cumulate(
		            new CumulativeFunction<Number>(Numbers.addition));
		    Connector.connect(fork, TOP, sum_proc, INPUT);
		    TurnInto ones = new TurnInto(1);
		    Connector.connect(fork, BOTTOM, ones, INPUT);
		    Cumulate counter = new Cumulate(
		            new CumulativeFunction<Number>(Numbers.addition));
		    Connector.connect(ones, OUTPUT, counter, INPUT);
		    ApplyFunction division = new ApplyFunction(Numbers.division);
		    Connector.connect(sum_proc, OUTPUT, division, LEFT);
		    Connector.connect(counter, OUTPUT, division, RIGHT);
		    runningAverage.addProcessors(fork, sum_proc, ones, counter, division);
		    runningAverage.associateInput(0, fork, 0);
		    runningAverage.associateOutput(0, division, 0);
		}
		
	    Connector.connect(treatment,runningAverage);
	    addProcessors(treatment,runningAverage);
	    associateInput(0, treatment, 0);
	    associateOutput(0, runningAverage, 0);
	}

	public Processor instantiate() {
		return this;
	}

}
