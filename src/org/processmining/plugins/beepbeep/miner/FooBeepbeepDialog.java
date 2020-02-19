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
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JRadioButton;
import javax.swing.JTextPane;
import javax.swing.table.DefaultTableModel;

import org.processmining.framework.util.ui.widgets.ProMHeaderPanel;
import org.processmining.framework.util.ui.widgets.ProMPropertiesPanel;
import org.processmining.framework.util.ui.widgets.ProMTextField;
import org.processmining.framework.util.ui.widgets.WidgetColors;
import org.processmining.framework.util.ui.widgets.WidgetImages;

import com.fluxicon.slickerbox.components.IconVerticalTabbedPane;
import com.fluxicon.slickerbox.components.RoundedPanel;
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
	
	/*
	 *  ---| Panels:
	 */
	
	private ProMPropertiesPanel patternPanel;
	private RoundedPanel inputStreamPanel;
	private RoundedPanel logPanel;
	private ProMHeaderPanel windowsPanel;

	private ProMHeaderPanel trendPanel;

	private ProMHeaderPanel distancePanel;

	private ProMHeaderPanel thresholdPanel;

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

		/*
		 * Pattern Panel
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
		JTextPane tp1 = createTextPane("Evaluates the similarity of a trend computed on the present with the same trend computed over the past window.");
		customFrame.add(tp1);
		patternOptPanel.add(customFrame);
		
		final JPanel customFrame2 = new JPanel();
		customFrame2.setOpaque(false);
		customFrame2.setLayout(new BoxLayout(customFrame2, BoxLayout.Y_AXIS));
		customFrame2.setAlignmentX(RIGHT_ALIGNMENT);
		createRadioButton(customFrame2, miningPatternsBtnGp, "Pattern-based trend distance ");
		JTextPane tp2 = createTextPane("Evaluates the similarity of a trend computed on the present with a reference trend provided externaly.");
		customFrame2.add(tp2);
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
		 * Insert InputStreamPanel
		 */
		this.inputStreamPanel = new ProMHeaderPanel("Select the input stream to use as the source.");
		tabbedPane.addTab("Input Stream", WidgetImages.summaryIcon, inputStreamPanel);
		
		/*
		 * -----------------------------------------------------
		 */
		
		/*
		 * Insert WindowsPanel
		 */
		
		URL windowsUrl = getClass().getResource("/org/processmining/plugins/beepbeep/miner/icons/windowsIcon.png");
		BufferedImage windowsIcon = ImageIO.read(new File(windowsUrl.getFile()));
		this.windowsPanel = new ProMHeaderPanel("Select the time windows for the evaluation of the pattern.");
		tabbedPane.addTab("Windows", windowsIcon, windowsPanel);
		
		/*
		 * -----------------------------------------------------
		 */
		
		/*
		 * Insert trendPanel
		 */
		URL trendUrl = getClass().getResource("/org/processmining/plugins/beepbeep/miner/icons/trendIcon.png");
		BufferedImage trendIcon = ImageIO.read(new File(trendUrl.getFile()));
		this.trendPanel = new ProMHeaderPanel("Trend.");
		tabbedPane.addTab("Trend", trendIcon, trendPanel);
		
		/*
		 * -----------------------------------------------------
		 */
		
		/*
		 * Insert distancePanel
		 */
		URL distanceUrl = getClass().getResource("/org/processmining/plugins/beepbeep/miner/icons/distanceIcon.png");
		BufferedImage distanceIcon = ImageIO.read(new File(distanceUrl.getFile()));
		this.distancePanel = new ProMHeaderPanel("Distance");
		tabbedPane.addTab("Trend", distanceIcon, distancePanel);
		
		/*
		 * -----------------------------------------------------
		 */
		
		/*
		 * Insert thresholdPanel
		 */
		URL thresholdUrl = getClass().getResource("/org/processmining/plugins/beepbeep/miner/icons/thresholdIcon.png");
		BufferedImage thresholdIcon = ImageIO.read(new File(thresholdUrl.getFile()));
		this.thresholdPanel = new ProMHeaderPanel("Threshold");
		tabbedPane.addTab("Trend", thresholdIcon, thresholdPanel);
		
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
		
		

		this.logPanel = new ProMHeaderPanel("Log");
		
		
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
	
	protected JLabel createLabel(final String string) {
		final JLabel label = new JLabel(string);
		
		label.setForeground(WidgetColors.PROPERTIES_BACKGROUND);
		label.setAlignmentX(RIGHT_ALIGNMENT);
		label.setMinimumSize(new Dimension(450,40));
		
		
		//label.setFont(label.getFont().deriveFont(Font.BOLD, 10));
		//label.setBorder(BorderFactory.createLineBorder(Color.BLUE, 5));
		
		return label;
	}
	
	protected JTextPane createTextPane(final String string) {
		final JTextPane textPane = new JTextPane();
		textPane.setOpaque(false);
		textPane.setText(string);
		textPane.setForeground(WidgetColors.PROPERTIES_BACKGROUND);
		textPane.setAlignmentX(RIGHT_ALIGNMENT);
		textPane.setMinimumSize(new Dimension(450,40));
		
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
