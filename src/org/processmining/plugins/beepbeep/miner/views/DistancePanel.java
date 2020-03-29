package org.processmining.plugins.beepbeep.miner.views;

import java.awt.Component;
import java.awt.Dimension;
import java.io.IOException;

import javax.swing.BoxLayout;
import javax.swing.ButtonGroup;
import javax.swing.JPanel;
import javax.swing.JRadioButton;

public class DistancePanel extends BeepBeepView {
	
	
	private static final long serialVersionUID = 1L;
	private JRadioButton manhattanDistanceRBtn, euclidianDistanceRBtn, scalarDifferenceRBtn, ratioRBtn;
	
	
	/**
	 * 
	 * @param title
	 * @throws IOException 
	 */
	public DistancePanel(){
		super("Distance",
				"/org/processmining/plugins/beepbeep/miner/views/icons/distanceIcon.png", 
				"Select the distance metric for comparing the present and the past trends");
		
		final JPanel distanceOptPanel = new JPanel();
		distanceOptPanel.setOpaque(false);
		distanceOptPanel.setLayout(new BoxLayout(distanceOptPanel, BoxLayout.PAGE_AXIS));
		distanceOptPanel.setAlignmentX(LEFT_ALIGNMENT);
		final ButtonGroup distanceOptBtnGp = new ButtonGroup();
		
		//Distance Options:
		
		manhattanDistanceRBtn = createRadioButton(distanceOptPanel, distanceOptBtnGp, "Manhattan Distance");
		createTxtPane(distanceOptPanel,"Sum of pairwise absolute differences in each dimension", new Dimension(200,30), true,false,Component.LEFT_ALIGNMENT);
		manhattanDistanceRBtn.setSelected(true);
		
		euclidianDistanceRBtn = createRadioButton(distanceOptPanel, distanceOptBtnGp, "Euclidian Distance");
		createTxtPane(distanceOptPanel,"Geometrical distance in n dimensions", new Dimension(200,30), true, false,Component.LEFT_ALIGNMENT );
		
		scalarDifferenceRBtn = createRadioButton(distanceOptPanel, distanceOptBtnGp, "Scalar Difference");
		createTxtPane(distanceOptPanel,"Plain subtraction of two numbers", new Dimension(200,30), true, false, Component.LEFT_ALIGNMENT);
		
		ratioRBtn = createRadioButton(distanceOptPanel, distanceOptBtnGp, "Ratio");
		createTxtPane(distanceOptPanel,"Plain division of two numbers", new Dimension(200,30), true, false, Component.LEFT_ALIGNMENT);
		
		super.beepBeepPanel.addProperty("Distance Options", distanceOptPanel);
		super.beepBeepPanel.setAlignmentX(LEFT_ALIGNMENT);
				
	}
	
	/**
	 * 
	 * @return value of 
	 */
	public String getDistance() {
		if (manhattanDistanceRBtn.isSelected()) {
			return "manhattanDistance";
		}
		if(euclidianDistanceRBtn.isSelected()) {
			return "euclidianDistance";
		}
		
		if(scalarDifferenceRBtn.isSelected()) {
			return "scalarDifference";
		}
		
		else {
			return "ratio";
		}
	}
	
	
		
	
}
