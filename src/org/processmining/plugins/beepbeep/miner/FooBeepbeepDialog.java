package org.processmining.plugins.beepbeep.miner;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.net.URL;

import javax.imageio.ImageIO;
import javax.swing.BorderFactory;
import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.ButtonGroup;
import javax.swing.DefaultListModel;
import javax.swing.JCheckBox;
import javax.swing.JComponent;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JRadioButton;
import javax.swing.JTextPane;
import javax.swing.table.DefaultTableModel;

import org.processmining.framework.util.ui.widgets.ProMPropertiesPanel;
import org.processmining.framework.util.ui.widgets.ProMTextField;
import org.processmining.framework.util.ui.widgets.WidgetColors;

import com.fluxicon.slickerbox.components.IconVerticalTabbedPane;
import com.fluxicon.slickerbox.factory.SlickerFactory;

public class FooBeepbeepDialog extends JPanel {

	/**
	 * SerialVersionUID
	 */
	private static final long serialVersionUID = 1L;
	
	/* 
	 * Fields (components)
	 */
	
	private ProMTextField port;
	private JCheckBox running;
	private DefaultListModel providers;
	private DefaultTableModel sessions;
	private IconVerticalTabbedPane tabbedPane;
	private ProMTextField pastHour;
	private ProMTextField pastMinute;
	private ProMTextField pastSecond;
	private ProMTextField presentHour;
	private ProMTextField presentMinute;
	private ProMTextField presentSecond;
	
	/*
	 *  ---| Panels:
	 */
	
	private ProMPropertiesPanel patternPanel;
	//private RoundedPanel inputStreamPanel;
	//private RoundedPanel logPanel;
	private ProMPropertiesPanel windowsPanel;
	private ProMPropertiesPanel trendPanel;
	private ProMPropertiesPanel distancePanel;
	private ProMPropertiesPanel thresholdPanel;
	public FooBeepbeepDialog() throws IOException {
		
		/* 
		 * GUI Configurations:
		 */
		setBackground(new Color(40, 40, 40));
		setBorder(BorderFactory.createEmptyBorder());
		setLayout(new BorderLayout());
		
		/*
		 * Insert tabbedpane 
		 */
		this.tabbedPane = new IconVerticalTabbedPane(new Color(230, 230, 230, 210), new Color(20, 20, 20, 160));
		add(tabbedPane, BorderLayout.CENTER);

		/* ========================================
		 * PANEL 1:
		 * Pattern Panel
		 * ==========================================
		 */
		URL url = getClass().getResource("/org/processmining/plugins/beepbeep/miner/icons/patternIcon.png");
		BufferedImage patternIcon = ImageIO.read(new File(url.getFile()));
		this.patternPanel = new ProMPropertiesPanel("Select data mining pattern you wish to instantiate.");
		//tabbedPane.addTab("Pattern", WidgetImages.inspectorIcon, patternPanel);
		tabbedPane.addTab("Pattern", patternIcon, patternPanel);
		
		/*
		 *  ---| inserted into Pattern Panel:
		 */	
		
		final JPanel patternOptPanel = new JPanel();
		patternOptPanel.setOpaque(false);
		patternOptPanel.setLayout(new BoxLayout(patternOptPanel, BoxLayout.PAGE_AXIS));
		final ButtonGroup miningPatternsBtnGp = new ButtonGroup();
		
		/*
		 * RadioButtons:	
		 */
		
		final JPanel customFrame = new JPanel();
		customFrame.setOpaque(false);
		customFrame.setLayout(new BoxLayout(customFrame, BoxLayout.Y_AXIS));
		customFrame.setAlignmentX(RIGHT_ALIGNMENT);
		JRadioButton selfCorrelat = createRadioButton(customFrame, miningPatternsBtnGp, "Self-correlated trend distance ");
		selfCorrelat.setSelected(true);
		JTextPane tp1 = createTextPane(customFrame,"Evaluates the similarity of a trend computed on the present with the same trend computed over the past window.", new Dimension(450,50),true);
		//customFrame.add(tp1);
		patternOptPanel.add(customFrame);
		
		final JPanel customFrame2 = new JPanel();
		customFrame2.setOpaque(false);
		customFrame2.setLayout(new BoxLayout(customFrame2, BoxLayout.Y_AXIS));
		customFrame2.setAlignmentX(RIGHT_ALIGNMENT);
		createRadioButton(customFrame2, miningPatternsBtnGp, "Pattern-based trend distance ");
		JTextPane tp2 = createTextPane(customFrame2, "Evaluates the similarity of a trend computed on the present with a reference trend provided externaly.", new Dimension(450,50), true);
		//customFrame2.add(tp2);
		patternOptPanel.add(customFrame2);
		
		
		patternPanel.addProperty("Data mining patterns", patternOptPanel);
		
		
		//final JLabel label = SlickerFactory.instance().createLabel("<html><h3>Select data mining pattern you wish to instantiate:</h3>");
		//patternPanel.add(label,0);
		//port = patternPanel.addTextField("Port");
		//running = patternPanel.addCheckBox("Running", true);
		//providers = new DefaultListModel();
		//patternPanel.addProperty("Providers", new ProMList("Providers", providers));
		
		/*
		 * ----------------------------------------------------------------------------
		 */
		
		
		
		
		/*
		 * Insert WindowsPanel
		 */
		
		URL windowsUrl = getClass().getResource("/org/processmining/plugins/beepbeep/miner/icons/windowsIcon.png");
		BufferedImage windowsIcon = ImageIO.read(new File(windowsUrl.getFile()));
		this.windowsPanel = new ProMPropertiesPanel("Select the time windows for the evaluation of the pattern.");
		tabbedPane.addTab("Windows", windowsIcon, windowsPanel);
				
		pastHour = changeSize(new ProMTextField("1"));
		pastMinute = changeSize(new ProMTextField("0"));
		pastSecond = changeSize(new ProMTextField("0"));
		
		presentHour = changeSize(new ProMTextField("1"));
		presentMinute = changeSize(new ProMTextField("0"));
		presentSecond = changeSize(new ProMTextField("0"));
		
		/*
		 * pastWindowPanel contains pastWindowTxtPane + line1Panel:
		 */
		final JPanel pastWindowPanel = new JPanel();
		pastWindowPanel.setLayout(new BoxLayout(pastWindowPanel, BoxLayout.Y_AXIS));
		pastWindowPanel.setOpaque(false);
		pastWindowPanel.setSize(200, 100);
		JTextPane pastWindowTxtPane = createTextPane(pastWindowPanel,"Select the past time window for the evaluation of the pattern:", new Dimension(300,40), false);
		//pastWindowPanel.add(pastWindowTxtPane);

		
		final JPanel line1 = new JPanel();
		line1.setLayout(new BoxLayout(line1, BoxLayout.X_AXIS));
		line1.setOpaque(false);
		
		line1.add(pastHour);
		line1.add(createLabel(":"));
		line1.add(pastMinute);
		line1.add(createLabel(":"));
		line1.add(pastSecond);
		
		pastWindowPanel.add(line1);
		
		/*
		 * presentWindowPanel contains presentWindowTxtPane + line2 Panel:
		 */
		
		final JPanel presentWindowPanel = new JPanel();
		presentWindowPanel.setLayout(new BoxLayout(presentWindowPanel, BoxLayout.Y_AXIS));
		presentWindowPanel.setOpaque(false);
		JTextPane presentWindowTxtPane = createTextPane(presentWindowPanel,"Select the present time window for the evaluation of the pattern:", new Dimension(300,40), false);
		//presentWindowPanel.add(presentWindowTxtPane);
		
		final JPanel line2 = new JPanel();
		line2.setLayout(new BoxLayout(line2, BoxLayout.X_AXIS));
		line2.setOpaque(false);
		
		line2.add(presentHour);
		line2.add(createLabel(":"));
		line2.add(presentMinute);
		line2.add(createLabel(":"));
		line2.add(presentSecond);
		
		presentWindowPanel.add(line2);
		
		/*
		 * Add pastWindowPanel and PresentWindowPanel to windowsPanel:
		 */
		
		windowsPanel.addProperty("Past Window (m)", pastWindowPanel);
		windowsPanel.addProperty("Present Window (n)", presentWindowPanel);
		
		/*
		 * -----------------------------------------------------------------------------------------------
		 */
		
		/*
		 * Insert trendPanel
		 */
		URL trendUrl = getClass().getResource("/org/processmining/plugins/beepbeep/miner/icons/trendIcon.png");
		BufferedImage trendIcon = ImageIO.read(new File(trendUrl.getFile()));
		this.trendPanel = new ProMPropertiesPanel("Trend Configuration");
		tabbedPane.addTab("Trend", trendIcon, trendPanel);
		
		
		final JPanel trendOptPanel = new JPanel();
		trendOptPanel.setOpaque(false);
		trendOptPanel.setLayout(new BoxLayout(trendOptPanel, BoxLayout.PAGE_AXIS));
		trendOptPanel.setAlignmentX(LEFT_ALIGNMENT);
				
		
		/*
		 * ElementChoicePanel:
		 */
		final JPanel elementChoice = new JPanel();
		final ButtonGroup elementChoiceBtnGp = new ButtonGroup();
		elementChoice.setOpaque(false);
		elementChoice.setLayout(new BoxLayout(elementChoice, BoxLayout.X_AXIS));
		elementChoice.setAlignmentX(RIGHT_ALIGNMENT);
		
		//elementChoice.setBorder(BorderFactory.createLineBorder(Color.RED, 2));
		
		JTextPane elementChoiceTxtPane = createTextPane(trendOptPanel, "Select the element of each event to use for computing the trend:", new Dimension(300,40),false);
		
		JRadioButton directValueRBtn = createRadioButton(elementChoice, elementChoiceBtnGp, "Direct Value ");
		directValueRBtn.setSelected(true);
		JRadioButton sourceRBtn = createRadioButton(elementChoice, elementChoiceBtnGp, "Source");
		JRadioButton sizeRBtn = createRadioButton(elementChoice, elementChoiceBtnGp, "Size");
		JRadioButton destinationRBtn = createRadioButton(elementChoice, elementChoiceBtnGp, "Destination");
		JRadioButton otherRBtn = createRadioButton(elementChoice, elementChoiceBtnGp, "Other");
		elementChoice.add(Box.createHorizontalStrut(10));
		ProMTextField otherText =   changeSize(new ProMTextField());
		elementChoice.add(otherText);
		
		
		//trendOptPanel.add(elementChoiceTxtPane);
		trendOptPanel.add(elementChoice);
		
		/*
		 * TrendChoicePanel
		 */
		
		final JPanel trendChoicePanel = new JPanel();
		final ButtonGroup trendChoiceBtnGp = new ButtonGroup();
		trendChoicePanel.setOpaque(false);
		trendChoicePanel.setLayout(new BoxLayout(trendChoicePanel, BoxLayout.PAGE_AXIS));
		trendChoicePanel.setAlignmentX(LEFT_ALIGNMENT);
		
		JTextPane trendChoiceTxtPane = createTextPane(trendChoicePanel,"Select the trend to compute over the stream:", new Dimension(300,40), false);
		trendChoicePanel.add(trendChoiceTxtPane);
		
		
		
		JRadioButton runningAverageRBtn = createRadioButton(trendChoicePanel, trendChoiceBtnGp, "Running Average ");
		JTextPane runningAverageTxtPane = createTextPane(trendChoicePanel,"The average of stream over the entire window", new Dimension(200,30), true);
		runningAverageRBtn.setSelected(true);
		
		JRadioButton vectorMomentsRBtn = createRadioButton(trendChoicePanel, trendChoiceBtnGp, "Vector of Moments ");
		JTextPane vectorMomentsTxtPane = createTextPane(trendChoicePanel,"The average n first statistical moments over the entire window", new Dimension(200,20), true);
		
		JRadioButton distinctOccurrencesRBtn = createRadioButton(trendChoicePanel, trendChoiceBtnGp, "Distinct Occurrences ");
		JTextPane distinctOccurrencesTxtPane = createTextPane(trendChoicePanel,"The number of distinct values observed in the window", new Dimension(200,20), true);
		
		JRadioButton valueDistributionRBtn = createRadioButton(trendChoicePanel, trendChoiceBtnGp, "Value Distribution ");
		JTextPane valueDistributionTxtPane = createTextPane(trendChoicePanel,"The distribuition of values observed in the window", new Dimension(200,20), true);
		
		JRadioButton cumulativeSumRBtn = createRadioButton(trendChoicePanel, trendChoiceBtnGp, "Cumulative Sum ");
		JTextPane cumulativeSumTxtPane = createTextPane(trendChoicePanel,"The sum of all values over the window", new Dimension(200,20), true);
			
		
		trendPanel.addProperty("Element Options:", trendOptPanel);
		trendPanel.addProperty("Trend Options:", trendChoicePanel);
		
		/*
		 * ----------------------------------------------------------------------------------------------------------------------
		 */
		
		/*
		 * Insert distancePanel
		 */
		URL distanceUrl = getClass().getResource("/org/processmining/plugins/beepbeep/miner/icons/distanceIcon.png");
		BufferedImage distanceIcon = ImageIO.read(new File(distanceUrl.getFile()));
		this.distancePanel = new ProMPropertiesPanel("Select the distance metric for comparing the present and the past trends");
		tabbedPane.addTab("Distance", distanceIcon, distancePanel);
		
		final JPanel distanceOptPanel = new JPanel();
		final ButtonGroup distanceOptBtnGp = new ButtonGroup();
		distanceOptPanel.setOpaque(false);
		distanceOptPanel.setLayout(new BoxLayout(distanceOptPanel, BoxLayout.PAGE_AXIS));
		distanceOptPanel.setAlignmentX(LEFT_ALIGNMENT);
		
		//Distance Options:
		
		JRadioButton manhattanDistanceRBtn = createRadioButton(distanceOptPanel, distanceOptBtnGp, "Manhattan Distance");
		JTextPane manhattanDistanceTxtPane = createTextPane(distanceOptPanel,"Sum of pairwise absolute differences in each dimension", new Dimension(200,30), true);
		manhattanDistanceRBtn.setSelected(true);
		
		JRadioButton euclidianDistanceRBtn = createRadioButton(distanceOptPanel, distanceOptBtnGp, "Euclidian Distance");
		JTextPane euclidianDistanceTxtPane = createTextPane(distanceOptPanel,"Geometrical distance in n dimensions", new Dimension(200,30), true);
		
		JRadioButton scalarDifferenceRBtn = createRadioButton(distanceOptPanel, distanceOptBtnGp, "Scalar Difference");
		JTextPane scalarDifferenceTxtPane = createTextPane(distanceOptPanel,"Plain subtraction of two numbers", new Dimension(200,30), true);
		
		JRadioButton ratioRBtn = createRadioButton(distanceOptPanel, distanceOptBtnGp, "Ratio");
		JTextPane ratioTxtPane = createTextPane(distanceOptPanel,"Plain division of two numbers", new Dimension(200,30), true);
		
		distancePanel.addProperty("Distance Options", distanceOptPanel);
		
		
		
		
		/*
		 * -----------------------------------------------------
		 */
		
		/*
		 * Insert thresholdPanel
		 */
		URL thresholdUrl = getClass().getResource("/org/processmining/plugins/beepbeep/miner/icons/thresholdIcon.png");
		BufferedImage thresholdIcon = ImageIO.read(new File(thresholdUrl.getFile()));
		this.thresholdPanel = new ProMPropertiesPanel("Threshold");
		tabbedPane.addTab("Trend", thresholdIcon, thresholdPanel);
		
		final JPanel thresholdOptPanel = new JPanel();
		final ButtonGroup thresholdOptBtnGp = new ButtonGroup();
		thresholdOptPanel.setOpaque(false);
		thresholdOptPanel.setLayout(new BoxLayout(thresholdOptPanel, BoxLayout.PAGE_AXIS));
		thresholdOptPanel.setAlignmentX(LEFT_ALIGNMENT);
		
		JTextPane thresholdOptTxtPane = createTextPane(thresholdOptPanel,"Trigger an alarm when the distance becomes", new Dimension(200,30), false);
		
		JRadioButton smallerThanRBtn = createRadioButton(thresholdOptPanel, thresholdOptBtnGp, "Smaller than");
		JRadioButton largerThanRBtn = createRadioButton(thresholdOptPanel, thresholdOptBtnGp, "Larger than");
		largerThanRBtn.setSelected(true);
		
		ProMTextField thresholdValueTxtField = new ProMTextField("0.5");
		
		thresholdPanel.addProperty("Threshold Options", thresholdOptPanel);
		thresholdPanel.addProperty("the following threshold:", thresholdValueTxtField);

		
		
		
		
		/*
		 * -----------------------------------------------------
		 */
		
		
		
		
		/*
		 * Exemplo de Tabela: ==================================================
		 */
//		sessions = new DefaultTableModel(new Object[] { "Session ID", "Providers" }, 0) {
//			private static final long serialVersionUID = 1L;
//
//			@Override
//			public boolean isCellEditable(final int rowIndex, final int mColIndex) {
//				return false;
//			}
//		};
//		
//		final ProMTable table = new ProMTable(sessions);
//		
//		
//		inputStreamPanel.add(table);
//		
//		table.setPreferredWidth(0, 150);
//		table.setPreferredWidth(1, 450);
		/*
		 * =========================================================================
		 */
		
		

		//this.logPanel = new ProMHeaderPanel("Log");
		
		
		/*
		 * Exemplo de addActionListener: =============================
		 */
//		running.addActionListener(new ActionListener() {
//
//			@Override
//			public void actionPerformed(final ActionEvent e) {
//				if (running.isSelected()) {
//					executor.execute(new Runnable() {
//						@Override
//						public void run() {
//							try {
//								try {
//									service.setPort(Integer.parseInt(port.getText()));
//								} catch (final NumberFormatException e) {
//									service.setPort(OSServicePlugin.DEFAULT_SETUP_PORT);
//								}
//								service.start(executor);
//							} catch (final IOException e) {
//							}
//						}
//					});
//				} else {
//					service.stop();
//				}
//			}
//		});
		/*
		 * ===================================================
		 */
//
//		service.addObserver(this);
//		update(service, null);
		
		
	
	}
	
	protected <T extends JComponent> T changeSize(final T c) {
		final Dimension minimumSize = c.getMinimumSize();
		if (minimumSize == null) {
			return c;
		}
		minimumSize.setSize(60, minimumSize.getHeight());
		c.setMinimumSize(minimumSize);
		return c;
	}
	
	protected JLabel createLabel(final String string) {
		final JLabel label = new JLabel(string);
		label.setForeground(WidgetColors.PROPERTIES_BACKGROUND);
		label.setFont(label.getFont().deriveFont(Font.BOLD, 10));
		return label;
	}
	
	protected JTextPane createTextPane(final JPanel component, final String string, Dimension dim, boolean leftAdjust) {
		final JTextPane textPane = new JTextPane();
		textPane.setOpaque(false);
		textPane.setText(string);
		textPane.setForeground(WidgetColors.PROPERTIES_BACKGROUND);
		textPane.setAlignmentX(RIGHT_ALIGNMENT);
		//textPane.setMinimumSize(new Dimension(450,40));
		textPane.setMinimumSize(dim);
		
		if(leftAdjust == true) {
			final JPanel lefty = new JPanel();
			lefty.setOpaque(false);
			lefty.setLayout(new BoxLayout(lefty, BoxLayout.X_AXIS));
			lefty.add(Box.createHorizontalStrut(22));
			lefty.add(textPane);
			component.add(lefty);
		}else {
			component.add(textPane);
		}
		return textPane;
	}

	protected JRadioButton createRadioButton(final JPanel component, final ButtonGroup buttonGroup, final String label) {
		return createRadioButton(component, buttonGroup, label, true);
	}

	protected JRadioButton createRadioButton(final JPanel component, final ButtonGroup buttonGroup, final String label,
			final boolean leftAdjust) {
		final JRadioButton button = SlickerFactory.instance().createRadioButton(label);
		button.setForeground(WidgetColors.TEXT_COLOR);
		button.setFont(button.getFont().deriveFont(Font.BOLD));
		button.setAlignmentX(LEFT_ALIGNMENT);
		buttonGroup.add(button);
		if (leftAdjust) {
			final JPanel lefty = new JPanel();
			lefty.setOpaque(false);
			lefty.setLayout(new BoxLayout(lefty, BoxLayout.X_AXIS));
			lefty.add(button);
			lefty.add(Box.createHorizontalGlue());
			component.add(lefty);
		} else {
			component.add(button);
		}
		return button;
	}
	

}
