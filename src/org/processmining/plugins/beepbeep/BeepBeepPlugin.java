/*
 * A ProM plugin using BeepBeep
 * Copyright (C) 2020 Jalves Nicacio and friends
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
import org.processmining.contexts.uitopia.UIPluginContext;

public abstract class BeepBeepPlugin
{
	/**
	 * The log instance on which the plugin is asked to run.
	 */
	/*@ non_null @*/ protected XLog m_log;
	
	/**
	 * Runs the plugin on a log.
	 * @param log The log
	 * @return The result from the execution of the plugin
	 */
	public abstract BeepBeepResult process(XLog log);
	
	/**
	 * Runs a GUI-based wizard to setup parameters prior to the execution of
	 * the plugin.
	 * @param context The UI context to which GUI elements (panels, etc.)
	 * are to be added
	 */
	public abstract void executeWizard(UIPluginContext context);
	
	/**
	 * Creates a new instance of the BeepBeep plugin.
	 * @param log The log instance on which the plugin is asked to run
	 */
	public BeepBeepPlugin(/*@ non_null @*/ XLog log)
	{
		super();
		m_log = log;
	}
	
	/**
	 * Gets the log instance on which the plugin is asked to run.
	 * @return The log instance
	 */
	/*@ non_null @*/ public XLog getLog()
	{
		return m_log;
	}

	/**
	 * 
	 * @param context
	 * @param log
	 * @return
	 */
	public BeepBeepResult execute(UIPluginContext context, XLog log)
	{
		executeWizard(context);
		return process(log);
	}
}
