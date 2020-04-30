package org.processmining.plugins.beepbeep;

import org.deckfour.xes.model.XLog;
import org.processmining.contexts.uitopia.UIPluginContext;
import org.processmining.plugins.beepbeep.models.BeepBeepLogModel;
import org.processmining.plugins.beepbeep.models.BeepBeepResult;

public interface ProcessesPlugin
{
	public BeepBeepResult process(UIPluginContext context, BeepBeepLogModel logModel);

	public void executeWizard(UIPluginContext context, BeepBeepLogModel logModel);
}
