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
import org.processmining.contexts.uitopia.annotations.UITopiaVariant;
import org.processmining.framework.plugin.annotations.Plugin;
import org.processmining.framework.plugin.annotations.PluginVariant;
import org.processmining.plugins.beepbeep.miner.PatTheMinerPlugin;

public class Main
{
	@Plugin(name = "Mine Log with BeepBeep",
			returnLabels = { "Beepbeep BPM Model" },
			returnTypes = {	BeepBeepResult.class }, 
			parameterLabels = { "Log" }, 
			userAccessible = true)

	@UITopiaVariant(affiliation = "UQAC",
		author = "Laboratoire d'Informatique Formelle (LIF)", 
		email = "shalle@uqac.ca")
	
	@PluginVariant(variantLabel = "Integration ProM - Beepbeep", 
		requiredParameterLabels = { 0 })
	
	public static BeepBeepResult main(UIPluginContext context, XLog log)
	{
		BeepBeepPlugin beepbeepPlugin = new PatTheMinerPlugin(log);
		return beepbeepPlugin.execute(context, log);
	}
}
