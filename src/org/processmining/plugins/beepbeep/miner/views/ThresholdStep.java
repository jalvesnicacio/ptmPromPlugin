package org.processmining.plugins.beepbeep.miner.views;

import javax.swing.JComponent;

import org.processmining.framework.util.ui.wizard.ProMWizardStep;
import org.processmining.plugins.beepbeep.miner.models.PTMSettingModel;

public class ThresholdStep implements ProMWizardStep<PTMSettingModel> {
	
	private String title = "";
	private PTMSettingModel model;	
	
		
	public String getTitle() {
		return "Step 5: Threshold Value Configuration";
	}
	


	public PTMSettingModel apply(PTMSettingModel model, JComponent component) {
		if(component instanceof ThresholdPanel) {
			ThresholdPanel panel =  (ThresholdPanel) component;
			String thresholdOption = panel.getThresholdOption();
			float thresholdValue = panel.getThresholdValue();
			model.setThresholdOption(thresholdOption);
			model.setThresholdValue(thresholdValue);
		}
		return model;
	}

	public boolean canApply(PTMSettingModel model, JComponent component) {
		if(component instanceof ThresholdPanel) {
			ThresholdPanel panel = (ThresholdPanel) component;
			if(!panel.getThresholdOption().isEmpty() && panel.getThresholdValue() > 0.0) {
				return true;
			}
		}
		return false;
	}

	public JComponent getComponent(PTMSettingModel model) {
		return new ThresholdPanel();
	}
	
}
