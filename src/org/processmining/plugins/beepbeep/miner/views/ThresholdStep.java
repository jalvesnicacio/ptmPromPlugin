/*
    A ProM plugin using BeepBeep palette for mining event traces
    Copyright (C) 2017-2019 Sylvain Hallé and friends
    
    This program is free software: you can redistribute it and/or modify
    it under the terms of the GNU Lesser General Public License as published
    by the Free Software Foundation, either version 3 of the License, or
    (at your option) any later version.
    
    This program is distributed in the hope that it will be useful,
    but WITHOUT ANY WARRANTY; without even the implied warranty of
    MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
    GNU Lesser General Public License for more details.
    
    You should have received a copy of the GNU Lesser General Public License
    along with this program.  If not, see <http://www.gnu.org/licenses/>.
 */
package src.org.processmining.plugins.beepbeep.miner.views;

import javax.swing.JComponent;

import org.processmining.framework.util.ui.wizard.ProMWizardStep;
import src.org.processmining.plugins.beepbeep.miner.models.PTMSettingModel;

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
