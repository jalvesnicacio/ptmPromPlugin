package org.processmining.plugins.beepbeep.miner.views;

import javax.swing.JComponent;

import org.processmining.framework.util.ui.wizard.ProMWizardStep;
import org.processmining.plugins.beepbeep.miner.models.PTMSettingModel;

public class SummaryStep implements ProMWizardStep<PTMSettingModel> {
	
	//private String title = "";

	public PTMSettingModel apply(PTMSettingModel model, JComponent component) {
		if(component instanceof SummaryPanel) {
			SummaryPanel panel =  (SummaryPanel) component;
		}
		return model;
	}

	public boolean canApply(PTMSettingModel model, JComponent component) {
		return true;
	}

	public JComponent getComponent(PTMSettingModel model) {
		return new SummaryPanel(model);
	}

	public String getTitle() {
		return "Request Summary";
	}

}
