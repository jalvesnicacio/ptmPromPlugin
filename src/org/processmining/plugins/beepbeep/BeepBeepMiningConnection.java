/*
 * A ProM plugin using BeepBeep palette for mining event traces Copyright (C)
 * 2017-2019 Sylvain Hallé and friends
 * 
 * This program is free software: you can redistribute it and/or modify it under
 * the terms of the GNU Lesser General Public License as published by the Free
 * Software Foundation, either version 3 of the License, or (at your option) any
 * later version.
 * 
 * This program is distributed in the hope that it will be useful, but WITHOUT
 * ANY WARRANTY; without even the implied warranty of MERCHANTABILITY or FITNESS
 * FOR A PARTICULAR PURPOSE. See the GNU Lesser General Public License for more
 * details.
 * 
 * You should have received a copy of the GNU Lesser General Public License
 * along with this program. If not, see <http://www.gnu.org/licenses/>.
 */
package org.processmining.plugins.beepbeep;

import org.deckfour.xes.model.XLog;

public class BeepBeepMiningConnection
		extends AbstractBeepBeepConnection<BeepBeepMiningParameters>
{

	/**
	 * Creates the connection between the log, model, and parameters.
	 * 
	 * @param log
	 *          The given event log.
	 * @param model
	 *          The given workshop model.
	 * @param parameters
	 *          The given conversion parameters.
	 */
	public BeepBeepMiningConnection(XLog log, BeepBeepLogModel model,
			BeepBeepMiningParameters parameters)
	{
		super(log, model, parameters);
	}

}
