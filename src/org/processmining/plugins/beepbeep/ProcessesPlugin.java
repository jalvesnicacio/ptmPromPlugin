package org.processmining.plugins.beepbeep;

import org.deckfour.xes.model.XLog;
import org.processmining.contexts.uitopia.UIPluginContext;

public interface ProcessesPlugin
{
	public BeepBeepResult process(UIPluginContext context, XLog log);

	public void executeWizard(UIPluginContext context, XLog log);
}
