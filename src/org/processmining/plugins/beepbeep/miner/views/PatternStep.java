package org.processmining.plugins.beepbeep.miner.views;

import javax.swing.JComponent;

import org.processmining.framework.util.ui.wizard.ProMWizardStep;
import org.processmining.plugins.beepbeep.miner.models.PTMSettingModel;
import org.processmining.plugins.beepbeep.miner.models.ReferenceTrend;

public class PatternStep implements ProMWizardStep<PTMSettingModel> {
	
	private String title = "";
	private PTMSettingModel model;	
	
		
	public String getTitle() {
		return "Step 1: Data Mining Pattern Configuration";
	}
	


	public PTMSettingModel apply(PTMSettingModel model, JComponent component) {
		if(component instanceof PatternPanel) {
			PatternPanel ps =  (PatternPanel) component;
			String pattern = ps.getPattern();
			model.setTrendReference(new ReferenceTrend(pattern, ps.getPatternValue()));
		}
		return model;
	}

	public boolean canApply(PTMSettingModel model, JComponent component) {
		if(component instanceof PatternPanel) {
			PatternPanel ps = (PatternPanel) component;
			if(!ps.getPattern().isEmpty()) {
				return true;
			}
		}
		return false;
	}

	public JComponent getComponent(PTMSettingModel model) {
		return new PatternPanel();
	}
	
}
