package org.processmining.plugins.beepbeep.miner.views;

import javax.swing.JComponent;

import org.processmining.framework.util.ui.wizard.ProMWizardStep;
import org.processmining.plugins.beepbeep.miner.models.PTMSettingModel;

public class DistanceStep implements ProMWizardStep<PTMSettingModel> {
	
	private String title = "";
	private PTMSettingModel model;	
	
		
	public String getTitle() {
		return "Step 4: Distance Algorithm Configuration";
	}
	


	public PTMSettingModel apply(PTMSettingModel model, JComponent component) {
		if(component instanceof DistancePanel) {
			DistancePanel panel =  (DistancePanel) component;
			String distance = panel.getDistance();
			model.setDistance(distance);
		}
		return model;
	}

	public boolean canApply(PTMSettingModel model, JComponent component) {
		if(component instanceof DistancePanel) {
			DistancePanel panel = (DistancePanel) component;
			if(!panel.getDistance().isEmpty()) {
				return true;
			}
		}
		return false;
	}

	public JComponent getComponent(PTMSettingModel model) {
		return new DistancePanel();
	}
	
}
