/*
 * A ProM plugin using BeepBeep palette for mining event traces Copyright (C)
 * 2017-2019 Sylvain Hallé and friends
 * 
 * This program is free software: you can redistribute it and/or modify it under
 * the terms of the GNU Lesser General Public License as published by the Free
 * Software Foundation, either version 3 of the License, or (at your option) any
 * later version.
 * 
 * This program is distributed in the hope that it will be useful, but WITHOUT
 * ANY WARRANTY; without even the implied warranty of MERCHANTABILITY or FITNESS
 * FOR A PARTICULAR PURPOSE. See the GNU Lesser General Public License for more
 * details.
 * 
 * You should have received a copy of the GNU Lesser General Public License
 * along with this program. If not, see <http://www.gnu.org/licenses/>.
 */
package org.processmining.plugins.beepbeep.miner.views;

import javax.swing.JComponent;

import org.processmining.framework.util.ui.wizard.ProMWizardStep;
import org.processmining.plugins.beepbeep.miner.models.PTMSettingModel;

public class SummaryStep implements ProMWizardStep<PTMSettingModel>
{

	// protected String title = "";

	public PTMSettingModel apply(PTMSettingModel model, JComponent component)
	{
		if (component instanceof SummaryPanel)
		{
			SummaryPanel panel = (SummaryPanel) component;
		}
		return model;
	}

	public boolean canApply(PTMSettingModel model, JComponent component)
	{
		return true;
	}

	public JComponent getComponent(PTMSettingModel model)
	{
		return new SummaryPanel(model);
	}

	public String getTitle()
	{
		return "Request Summary";
	}

}
