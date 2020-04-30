/*
    A ProM plugin using BeepBeep palette for mining event traces
    Copyright (C) 2017-2019 Sylvain Hallé and friends
    
    This program is free software: you can redistribute it and/or modify
    it under the terms of the GNU Lesser General Public License as published
    by the Free Software Foundation, either version 3 of the License, or
    (at your option) any later version.
    
    This program is distributed in the hope that it will be useful,
    but WITHOUT ANY WARRANTY; without even the implied warranty of
    MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
    GNU Lesser General Public License for more details.
    
    You should have received a copy of the GNU Lesser General Public License
    along with this program.  If not, see <http://www.gnu.org/licenses/>.
 */
package org.processmining.plugins.beepbeep;

import org.deckfour.xes.model.XLog;
import org.processmining.framework.connections.impl.AbstractConnection;
import org.processmining.plugins.beepbeep.models.BeepBeepLogModel;

public class AbstractBeepBeepConnection<Paramenters> extends AbstractConnection {
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
	public AbstractBeepBeepConnection(XLog log, BeepBeepLogModel model, Paramenters parameters) {
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


