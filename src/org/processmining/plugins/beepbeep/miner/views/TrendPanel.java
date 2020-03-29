package org.processmining.plugins.beepbeep.miner.views;

import java.awt.Component;
import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.util.List;

import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.ButtonGroup;
import javax.swing.JPanel;
import javax.swing.JRadioButton;

import org.processmining.framework.util.ui.widgets.ProMComboBox;
import org.processmining.plugins.beepbeep.miner.models.PTMSettingModel;

public class TrendPanel extends BeepBeepView {
	
	
	private static final long serialVersionUID = 1L;
	private ProMComboBox<String> elementsCbBox;
	private JRadioButton directValueRBtn, otherRBtn;
	private JRadioButton runningAverageRBtn, vectorMomentsRBtn, distinctOccurrencesRBtn, valueDistributionRBtn, cumulativeSumRBtn;
	
	
	/**
	 * 
	 * @param title
	 * @throws IOException 
	 */
	public TrendPanel(PTMSettingModel ptmSettingModel){
		super("Trend",
				"/org/processmining/plugins/beepbeep/miner/views/icons/trendIcon.png", 
				"Trend Configuration");
		
		final JPanel trendElementOPtPanel = createPanel(null, false, BoxLayout.Y_AXIS);
		final List<String> elements = ptmSettingModel.getBpmModel().getEventAttributes();		
		
		/*--------------------------------------------------------------------------------------
		 * elementOptions:
		 *------------------------------------------------------------------------------------*/
		final JPanel elementOptions = createPanel(trendElementOPtPanel, false, BoxLayout.X_AXIS);
		final ButtonGroup elementOptionsBtnGp = new ButtonGroup();
		elementOptions.setAlignmentX(LEFT_ALIGNMENT);
		directValueRBtn = createRadioButton(elementOptions, elementOptionsBtnGp, "Direct Value ");
		directValueRBtn.setSelected(true);
		
		elementsCbBox = new ProMComboBox<String>(elements);
		otherRBtn = createRadioButton(elementOptions, elementOptionsBtnGp, "Other");
		elementOptions.add(Box.createHorizontalStrut(10));
		elementOptions.add(elementsCbBox);
		
		createLabel(trendElementOPtPanel, "Select the element of each event to use for computing the trend:", new Dimension(450,40),false,"left");
		
		elementsCbBox.addActionListener(new ActionListener() {
			
			public void actionPerformed(ActionEvent e) {
				otherRBtn.setSelected(true);
			}
		});
		
		/*---------------------------------------------------------------------------------------
		 * TrendChoicePanel
		 *----------------------------------------------------------------------------------------*/
		final JPanel trendChoicePanel = createPanel(null, false, BoxLayout.PAGE_AXIS);
		//trendChoicePanel.setAlignmentX(LEFT_ALIGNMENT);
		final ButtonGroup trendChoiceBtnGp = new ButtonGroup();
		
		//trendChoicePanel.setBorder(BorderFactory.createLineBorder(Color.RED, 2));
		
		final JPanel panel = createPanel(null, false, BoxLayout.Y_AXIS);
		//panel.setSize(500,300);
		//panel.setBorder(BorderFactory.createLineBorder(Color.BLACK, 1));
		panel.setAlignmentX(RIGHT_ALIGNMENT);
		
		
		runningAverageRBtn = createRadioButton(panel, trendChoiceBtnGp, "Running Average ");
		runningAverageRBtn.setSelected(true);
		createTxtPane(panel,"The average of stream over the entire window", new Dimension(450,30), true, false, Component.LEFT_ALIGNMENT);
		//runningAverageLabel.setBorder(BorderFactory.createLineBorder(Color.cyan, 1));
		
		vectorMomentsRBtn = createRadioButton(panel, trendChoiceBtnGp, "Vector of Moments ");
		createTxtPane(panel,"The average n first statistical moments over the entire window", new Dimension(450,30), true, false, Component.LEFT_ALIGNMENT);
				
		distinctOccurrencesRBtn = createRadioButton(panel, trendChoiceBtnGp, "Distinct Occurrences ");
		createTxtPane(panel,"The number of distinct values observed in the window", new Dimension(450,20), true, false, Component.LEFT_ALIGNMENT);
		
		valueDistributionRBtn = createRadioButton(panel, trendChoiceBtnGp, "Value Distribution ");
		createTxtPane(panel,"The distribuition of values observed in the window", new Dimension(450,20), true, false, Component.LEFT_ALIGNMENT);
		
		cumulativeSumRBtn = createRadioButton(panel, trendChoiceBtnGp, "Cumulative Sum ");
		createTxtPane(panel,"The sum of all values over the window", new Dimension(450,20), true, false, Component.LEFT_ALIGNMENT);
		
		
		createTxtPane(trendChoicePanel,"Select the trend to compute over the stream:", new Dimension(450,50), false, true, Component.RIGHT_ALIGNMENT);
		trendChoicePanel.add(Box.createRigidArea(new Dimension(0, 10)));
		trendChoicePanel.add(panel);
		
		//
		
		super.beepBeepPanel.addProperty("Element Options:", trendElementOPtPanel);
		super.beepBeepPanel.addProperty("Trend Options:", trendChoicePanel);
		super.beepBeepPanel.setAlignmentX(LEFT_ALIGNMENT);
				
	}
	
	/**
	 * 
	 * @return value of 
	 */
	public String getElementOption() {
		if (directValueRBtn.isSelected()) {
			return "directValue";
		}
		if(otherRBtn.isSelected()) {
			return (String) elementsCbBox.getSelectedItem();
		}
		
		return "";
	}
	
	public String getTrendChoice() {
		if(runningAverageRBtn.isSelected())
			return "runningAverage";
		if(vectorMomentsRBtn.isSelected())
			return "vectorMoment";
		if(distinctOccurrencesRBtn.isSelected())
			return "distinctOccurrences";
		if(valueDistributionRBtn.isSelected())
			return "valueDistribution";
		else {
			return "cumulativeSum";
		}
		
	}
	
}
