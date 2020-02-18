package org.processmining.plugins.beepbeep.miner;

import org.deckfour.xes.model.XLog;
import org.processmining.models.beepbeep.BeepbeepModel;
import org.processmining.models.beepbeep.connections.AbstractBeepbeepModelConnection;

public class BeepbeepMiningConnection extends AbstractBeepbeepModelConnection<BeepbeepMiningParameters>{

	/**
	 * Creates the connection between the log, model, and parameters.
	 * @param log The given event log.
	 * @param model The given workshop model.
	 * @param parameters The given conversion parameters.
	 */
	public BeepbeepMiningConnection(XLog log, BeepbeepModel model, BeepbeepMiningParameters parameters) {
		super(log, model, parameters);
	}

}
