package org.processmining.plugins.beepbeep.miner.views;

import javax.swing.JComponent;

import org.processmining.framework.util.ui.wizard.ProMWizardStep;
import org.processmining.plugins.beepbeep.miner.models.PTMSettingModel;

public class TrendStep implements ProMWizardStep<PTMSettingModel> {
	
	private String title = "";
	private PTMSettingModel model;	
	
		
	public String getTitle() {
		return "Step 3: Trend Configuration";
	}
	


	public PTMSettingModel apply(PTMSettingModel model, JComponent component) {
		if(component instanceof TrendPanel) {
			TrendPanel panel =  (TrendPanel) component;
			String elementTrendOption = panel.getElementOption();
			String trendChoice = panel.getTrendChoice();
			model.setElementTrendOption(elementTrendOption);
			model.setTrendChoice(trendChoice);
		}
		return model;
	}

	public boolean canApply(PTMSettingModel model, JComponent component) {
		if(component instanceof TrendPanel) {
			TrendPanel panel = (TrendPanel) component;
			if(!panel.getElementOption().isEmpty() && !panel.getTrendChoice().isEmpty()) {
				return true;
			}
		}
		return false;
	}

	public JComponent getComponent(PTMSettingModel model) {
		return new TrendPanel(model);
	}
	
}
