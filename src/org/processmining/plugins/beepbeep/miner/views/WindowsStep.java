package org.processmining.plugins.beepbeep.miner.views;

import javax.swing.JComponent;

import org.processmining.framework.util.ui.wizard.ProMWizardStep;
import org.processmining.plugins.beepbeep.miner.models.PTMSettingModel;

public class WindowsStep implements ProMWizardStep<PTMSettingModel> {
	
	private String title = "";
	private PTMSettingModel model;	
	
		
	public String getTitle() {
		return "Step 2: Time Window Configuration";
	}
	


	public PTMSettingModel apply(PTMSettingModel model, JComponent component) {
		if(component instanceof WindowsPanel) {
			WindowsPanel panel =  (WindowsPanel) component;
			int pastWindow = panel.getPastWindow();
			int presentWindow = panel.getPresentWindow();
			model.setPastWindow(pastWindow);
			model.setPresentWindow(presentWindow);
		}
		return model;
	}

	public boolean canApply(PTMSettingModel model, JComponent component) {
		if(component instanceof WindowsPanel) {
			WindowsPanel panel = (WindowsPanel) component;
			if(model.getTrendReference().getDataMiningPattern().toString() == "SELF_CORRELATED") {
				if(!(panel.getPastWindow() == 0)) {
					return true;
				}
			}else if(!(panel.getPresentWindow() == 0)){
				return true;
			}
		}
		return false;
	}

	public JComponent getComponent(PTMSettingModel model) {
		return new WindowsPanel(model.getTrendReference());
	}
	
}
