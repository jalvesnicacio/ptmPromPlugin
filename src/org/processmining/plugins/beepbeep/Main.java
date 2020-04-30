package org.processmining.plugins.beepbeep;

import java.io.IOException;

import org.deckfour.xes.model.XLog;
import org.processmining.contexts.uitopia.UIPluginContext;
import org.processmining.contexts.uitopia.annotations.UITopiaVariant;
import org.processmining.framework.plugin.annotations.Plugin;
import org.processmining.framework.plugin.annotations.PluginVariant;
import org.processmining.plugins.beepbeep.miner.PatTheMinerPlugin;
import org.processmining.plugins.beepbeep.models.BeepBeepResult;

public class Main
{
	@Plugin(name = "Mine Log with BeepBeep", returnLabels = { "Beepbeep BPM Model" }, returnTypes = {
			BeepBeepResult.class }, parameterLabels = { "Log" }, userAccessible = true)

	@UITopiaVariant(affiliation = "UQAC", author = "Laboratoire d'Informatique Formelle (LIF)", email = "shalle@uqac.ca")
	@PluginVariant(variantLabel = "Integration ProM - Beepbeep", requiredParameterLabels = { 0 })
	public static BeepBeepResult main(UIPluginContext context, XLog log) throws IOException
	{
		BeepBeepPlugin beepbeepPlugin = new PatTheMinerPlugin();
		return beepbeepPlugin.execute(context, log);
	}

}
