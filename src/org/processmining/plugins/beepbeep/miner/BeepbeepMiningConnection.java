package org.processmining.plugins.beepbeep.miner;

import org.deckfour.xes.model.XLog;
import org.processmining.plugins.beepbeep.miner.connections.AbstractBeepbeepModelConnection;
import org.processmining.plugins.beepbeep.miner.models.BeepbeepBPMModel;

public class BeepbeepMiningConnection extends AbstractBeepbeepModelConnection<BeepbeepMiningParameters>{

	/**
	 * Creates the connection between the log, model, and parameters.
	 * @param log The given event log.
	 * @param model The given workshop model.
	 * @param parameters The given conversion parameters.
	 */
	public BeepbeepMiningConnection(XLog log, BeepbeepBPMModel model, BeepbeepMiningParameters parameters) {
		super(log, model, parameters);
	}

}
