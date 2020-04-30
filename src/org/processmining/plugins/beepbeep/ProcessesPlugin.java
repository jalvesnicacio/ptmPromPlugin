package org.processmining.plugins.beepbeep;

import org.processmining.contexts.uitopia.UIPluginContext;

public interface ProcessesPlugin
{
	public BeepBeepResult process(UIPluginContext context, BeepBeepLogModel logModel);

	public void executeWizard(UIPluginContext context, BeepBeepLogModel logModel);
}
