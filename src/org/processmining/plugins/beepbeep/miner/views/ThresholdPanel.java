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

import java.awt.Component;
import java.awt.Dimension;
import java.io.IOException;

import javax.swing.BoxLayout;
import javax.swing.ButtonGroup;
import javax.swing.JPanel;
import javax.swing.JRadioButton;

import org.processmining.framework.util.ui.widgets.ProMTextField;

public class ThresholdPanel extends BeepBeepView
{

	private static final long serialVersionUID = 1L;
	private JRadioButton smallerThanRBtn, largerThanRBtn;
	private ProMTextField thresholdValueTxtField;

	/**
	 * 
	 * @param title
	 * @throws IOException
	 */
	public ThresholdPanel()
	{
		super("Threshold", "/org/processmining/plugins/beepbeep/miner/views/icons/thresholdIcon.png",
				"Threshold");

		final JPanel thresholdOptPanel = new JPanel();
		thresholdOptPanel.setOpaque(false);
		thresholdOptPanel.setLayout(new BoxLayout(thresholdOptPanel, BoxLayout.PAGE_AXIS));
		thresholdOptPanel.setAlignmentX(LEFT_ALIGNMENT);
		final ButtonGroup thresholdOptBtnGp = new ButtonGroup();

		createTxtPane(thresholdOptPanel, "Trigger an alarm when the distance becomes",
				new Dimension(200, 30), false, false, Component.LEFT_ALIGNMENT);

		smallerThanRBtn = createRadioButton(thresholdOptPanel, thresholdOptBtnGp, "Smaller than");
		largerThanRBtn = createRadioButton(thresholdOptPanel, thresholdOptBtnGp, "Larger than");
		largerThanRBtn.setSelected(true);

		thresholdValueTxtField = new ProMTextField("0.5");

		super.beepBeepPanel.addProperty("Threshold Options", thresholdOptPanel);
		super.beepBeepPanel.addProperty("the following threshold:", thresholdValueTxtField);
		super.beepBeepPanel.setAlignmentX(LEFT_ALIGNMENT);

	}

	/**
	 * 
	 * @return value of
	 */
	public String getThresholdOption()
	{
		if (smallerThanRBtn.isSelected())
		{
			return "smallerThan";
		}

		else
		{
			return "largerThan";
		}
	}

	public float getThresholdValue()
	{
		return Float.parseFloat(thresholdValueTxtField.getText());
	}

}
