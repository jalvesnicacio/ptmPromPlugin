package org.processmining.plugins.beepbeep.miner.connections;

import org.deckfour.xes.model.XLog;
import org.processmining.framework.connections.impl.AbstractConnection;
import org.processmining.plugins.beepbeep.miner.models.BeepbeepBPMModel;

public class AbstractBeepbeepModelConnection<Paramenters> extends AbstractConnection {
	/**
	 * Label for the log end of the connection.
	 */
	public final static String LOG = "Log";
	/**
	 * Label for the model end of the connection.
	 */
	public final static String MODEL = "Model";

	/**
	 * The parameters used to mine the model from the log.
	 */
	private Paramenters parameters;

	/**
	 * Creates a connection from an event log to a model, where the
	 * model is mined from the log using the given parameters.
	 * 
	 * @param log
	 *            The event log.
	 * @param model
	 *            The mined workshop model.
	 * @param parameters
	 *            The parameters used to mine the model from the log.
	 */
	public AbstractBeepbeepModelConnection(XLog log, BeepbeepBPMModel model, Paramenters parameters) {
		super("Beepbeep model for log");
		put(LOG, log);
		put(MODEL, model);
		this.parameters = parameters;
	}

	/**
	 * Gets the parameters used to mine the workshop model from the event log.
	 * 
	 * @return The parameters used to derive the workshop model from the event
	 *         log.
	 */
	public Paramenters getParameters() {
		return parameters;
	}

}


