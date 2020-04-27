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
package org.processmining.plugins.beepbeep.miner.views;

import java.awt.Dimension;
import java.io.IOException;

import javax.swing.BoxLayout;
import javax.swing.JLabel;
import javax.swing.JPanel;

import org.processmining.framework.util.ui.widgets.ProMTextField;
import org.processmining.plugins.beepbeep.miner.models.ReferenceTrend;
import org.processmining.plugins.beepbeep.miner.models.ReferenceTrend.DataMiningPattern;

public class WindowsPanel extends BeepBeepView {
	
	
	private static final long serialVersionUID = 1L;
	private ProMTextField pastWindow, presentWindow;
	
	
	/**
	 * 
	 * @param title
	 * @throws IOException 
	 */
	public WindowsPanel(ReferenceTrend trend){
		super("Windows",
				"/org/processmining/plugins/beepbeep/miner/views/icons/windowsIcon.png", 
				"Select the time windows for the evaluation of the pattern.");
				
		pastWindow = changeSize(new ProMTextField(""));
		presentWindow = changeSize(new ProMTextField(""));
		
		if(trend.getDataMiningPattern() == DataMiningPattern.PATTERN_BASED) {
			pastWindow.setEditable(false);
		}
		
		
		/*
		 * pastWindowPanel contains pastWindowTxtPane + pastWindow Field:
		 */
		final JPanel pastWindowPanel = createPanel(null, false, BoxLayout.Y_AXIS);
		pastWindowPanel.setAlignmentX(LEFT_ALIGNMENT);
		JLabel pastWindowLabel = createLabel(pastWindowPanel,"Select the past window for the evaluation of the pattern:", new Dimension(340,40), false, "rigth");
		pastWindowPanel.add(pastWindow);
		
		/*
		 * presentWindowPanel contains presentWindowTxtPane + line2 Panel:
		 */
		
		final JPanel presentWindowPanel = createPanel(null, false, BoxLayout.Y_AXIS);
		presentWindowPanel.setAlignmentX(LEFT_ALIGNMENT);
		JLabel presentWindowLabel = createLabel(presentWindowPanel,"Select the present window for the evaluation of the pattern:", new Dimension(340,40), false, "rigth");
		//presentWindowTxtPane.setBorder(BorderFactory.createLineBorder(Color.BLUE, 2));
		presentWindowPanel.add(presentWindow);
		
		
		
		/*
		 * Add pastWindowPanel and PresentWindowPanel to windowsPanel:
		 */
		
		super.beepBeepPanel.addProperty("Present Window (n)", presentWindowPanel);
		super.beepBeepPanel.addProperty("Past Window (m)", pastWindowPanel);
		
	}
	
	/**
	 * 
	 * @return value of pastWindow
	 */
	public int getPastWindow() {
		String pastWindow = this.pastWindow.getText();
		return pastWindow.isEmpty()? 0 : Integer.parseInt(pastWindow);		
	}
	
	/**
	 * 
	 * @return value of PresentWindow
	 */
	public int getPresentWindow() {
		String presentWindow = this.presentWindow.getText();
		return presentWindow.isEmpty()? 0 : Integer.parseInt(presentWindow);
	}
	
}
