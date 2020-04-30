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

import java.io.IOException;
import java.util.Collection;

import org.deckfour.xes.classification.XEventClassifier;
import org.deckfour.xes.info.XLogInfo;
import org.deckfour.xes.info.XLogInfoFactory;
import org.deckfour.xes.model.XLog;
import org.processmining.contexts.uitopia.UIPluginContext;
import org.processmining.framework.connections.ConnectionCannotBeObtained;

public abstract class BeepBeepPlugin
{
	private BeepBeepLogModel m_logModel;

	public abstract ProcessesPlugin createProcessPlugin();

	/**
	 * 
	 * @param context
	 * @param log
	 * @return
	 * @throws IOException
	 */
	public BeepBeepResult execute(UIPluginContext context, XLog log) throws IOException
	{
		this.m_logModel = setParameters(context, log, new BeepBeepMiningParameters());
		ProcessesPlugin plugin = this.createProcessPlugin();
		plugin.executeWizard(context, this.m_logModel);
		BeepBeepResult result = plugin.process(context, this.m_logModel);
		// System.out.println(result.toHTMLString(true));
		return result;
	}

	/**
	 * SetParameters Create XlogInfo based on log, Create a LogModel based on info,
	 * and set classifiers on parameters.
	 * 
	 * @param context
	 * @param log
	 * @param parameters
	 * @return
	 */
	private BeepBeepLogModel setParameters(UIPluginContext context, XLog log,
			BeepBeepMiningParameters parameters)
	{
		Collection<BeepBeepMiningConnection> connections;
		XLogInfo info = XLogInfoFactory.createLogInfo(log);
		BeepBeepLogModel logModel = new BeepBeepLogModel(info);
		for (XEventClassifier classifier : info.getEventClassifiers())
		{
			parameters.setClassifier(classifier);
		}
		try
		{
			connections = context.getConnectionManager().getConnections(BeepBeepMiningConnection.class,
					context, log);

			for (BeepBeepMiningConnection connection : connections)
			{
				if (connection.getObjectWithRole(BeepBeepMiningConnection.LOG).equals(log)
						&& connection.getParameters().equals(parameters))
				{
					return connection.getObjectWithRole(BeepBeepMiningConnection.MODEL);
				}
			}
		}
		catch (ConnectionCannotBeObtained e)
		{
		}

		context.addConnection(new BeepBeepMiningConnection(log, logModel, parameters));
		return logModel;
	}
}
