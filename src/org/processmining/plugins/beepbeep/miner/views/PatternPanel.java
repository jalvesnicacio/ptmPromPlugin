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
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;

import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.ButtonGroup;
import javax.swing.JPanel;
import javax.swing.JRadioButton;
import javax.swing.JTextPane;

import org.processmining.framework.util.ui.widgets.ProMTextField;

public class PatternPanel extends BeepBeepView {
	
	
	private static final long serialVersionUID = 1L;
	private JRadioButton selfCorrelat, patternBased;
	private ProMTextField patternValue;
	
	
	/**
	 * 
	 * @param title
	 * @throws IOException 
	 */
	public PatternPanel(){
		super("Pattern", 
				"/org/processmining/plugins/beepbeep/miner/views/icons/patternIcon.png", 
				"Select data mining pattern you wish to instantiate.");
				
		JPanel patternOptPanel = createPanel(null, false, BoxLayout.Y_AXIS);
		final ButtonGroup miningPatternsBtnGp = new ButtonGroup();
		
		//adicionar um radiobutton:
		this.selfCorrelat = createRadioButton(patternOptPanel, miningPatternsBtnGp, "Self-correlated trend distance ");
		this.selfCorrelat.setSelected(true);
		JTextPane txp1 = createTxtPane(patternOptPanel, "Evaluates the similarity of a trend computed on the present with the same trend computed over the past window.", 
									   new Dimension(350,50), true, false, LEFT_ALIGNMENT);
		
		//create a auxiliar panel to second option and textfield:
		JPanel auxPanel = createPanel(patternOptPanel, false, BoxLayout.X_AXIS);
		this.patternBased = createRadioButton(auxPanel, miningPatternsBtnGp, "Pattern-based trend distance ");
		auxPanel.add(Box.createHorizontalStrut(5));
		this.patternValue = changeSize(new ProMTextField(""));
		auxPanel.add(patternValue);
		
		//add a text for patternBased option:
		JTextPane lb2 = createTxtPane(patternOptPanel, "Evaluates the similarity of a trend computed on the present with a reference trend provided externaly.", 
									  new Dimension(350,50), true, false, LEFT_ALIGNMENT);
		
		patternValue.addActionListener(new ActionListener() {
			
			public void actionPerformed(ActionEvent e) {
				patternBased.setSelected(true);
			}
		});
						
		//patternOptPanel.setBorder(BorderFactory.createLineBorder(Color.BLACK, 1));
		super.beepBeepPanel.addProperty("Data mining patterns", patternOptPanel);
		
	}
	
	/**
	 * 
	 * @return value of 
	 */
	public String getPattern() {
		try {
			if(selfCorrelat.isSelected())
				return "SELF_CORRELATED";
			else
				return "PATTERN_BASED";
			
		} catch (Exception e) {
			return "SELF_CORRELATED";
		}
	}
	
	/**
	 * 
	 * @return pattern value, if trend is pattern based.
	 */
	public Number getPatternValue() {
		String value = this.patternValue.getText();
		return value.isEmpty()? 0 : Integer.parseInt(value);
	}
	
}
