package org.processmining.plugins.beepbeep.miner;

import java.awt.Dimension;

import javax.swing.JList;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.ListSelectionModel;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;

import org.deckfour.xes.classification.XEventClassifier;
import org.deckfour.xes.model.XLog;

import com.fluxicon.slickerbox.colors.SlickerColors;
import com.fluxicon.slickerbox.factory.SlickerDecorator;
import com.fluxicon.slickerbox.factory.SlickerFactory;

import info.clearthought.layout.TableLayout;
import info.clearthought.layout.TableLayoutConstants;

public class BeepbeepMiningDialog extends JPanel{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 7639879370139576539L;

	public BeepbeepMiningDialog(XLog log, final BeepbeepMiningParameters parameters) {
		/*
		 * Get a layout containing a single column and two rows, where the top
		 * row height equals 30.
		 */
		double size[][] = { { TableLayoutConstants.FILL }, { 30, TableLayoutConstants.FILL } };
		setLayout(new TableLayout(size));

		/*
		 * Put a meaningful text in the top row. ---> Row 0
		 */
		add(SlickerFactory.instance().createLabel("<html><h2>Select mining parameters</h2>"), "0, 0");

		/*
		 * Put a list containing all available classifiers in the bottom row. --> Row 01
		 */
		Object classifiers[] = log.getClassifiers().toArray();

		final JList<?> classifierList = new javax.swing.JList(classifiers);
		
		classifierList.setName("Select classifier");
		classifierList.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		/*
		 * Create a selection listener on this list that changes the parameters
		 * accordingly.
		 */
		classifierList.addListSelectionListener(new ListSelectionListener() {
			public void valueChanged(ListSelectionEvent e) {
				parameters.setClassifier((XEventClassifier) classifierList.getSelectedValue());
			}
		});
		
		/*
		 * Tentativa de substituir por combobox:
		 */
		
//		final JComboBox<?> classifierCombo = new JComboBox(classifiers);
//		classifierCombo.setName("Select classifier");
//		classifierCombo.addItemListener(new ItemListener() {
//			
//			public void itemStateChanged(ItemEvent e) {
//				// TODO Auto-generated method stub
//				parameters.setClassifier((XEventClassifier) classifierCombo.getSelectedItem()); 
//			}
//		});
		
		/*
		 * 
		 */
		
		JScrollPane classifierScrollPane = new javax.swing.JScrollPane();
		
		SlickerDecorator.instance().decorate(classifierScrollPane, SlickerColors.COLOR_BG_3, SlickerColors.COLOR_FG,
				SlickerColors.COLOR_BG_1);
		
		classifierScrollPane.setPreferredSize(new Dimension(250, 300));
		classifierScrollPane.setViewportView(classifierList);
		add(classifierScrollPane, "0, 1");
	//	add(classifierCombo, "0,2");
	}

}
