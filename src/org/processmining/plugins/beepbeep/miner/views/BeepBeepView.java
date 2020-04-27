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

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Component;
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
import javax.swing.JComponent;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JRadioButton;
import javax.swing.JTextPane;
import javax.swing.text.SimpleAttributeSet;
import javax.swing.text.StyleConstants;

import org.processmining.framework.util.ui.widgets.ProMPropertiesPanel;
import org.processmining.framework.util.ui.widgets.WidgetColors;

import com.fluxicon.slickerbox.components.IconVerticalTabbedPane;
import com.fluxicon.slickerbox.factory.SlickerFactory;

public class BeepBeepView extends JPanel {
	private IconVerticalTabbedPane tabbedPane;
	protected ProMPropertiesPanel beepBeepPanel;
	
	public BeepBeepView(String tabName, String urlString, String title) {
		
		this.setBackground(new Color(40, 40, 40));
		this.setBorder(BorderFactory.createEmptyBorder());
		this.setLayout(new BorderLayout());
		
		URL url = getClass().getResource(urlString);
		BufferedImage icon = null;
		try {
			icon = ImageIO.read(new File(url.getFile()));
		} catch (IOException e) {
			e.printStackTrace();
		}
		this.beepBeepPanel = new ProMPropertiesPanel(title);
		
		this.tabbedPane = new IconVerticalTabbedPane(new Color(230, 230, 230, 210), new Color(20, 20, 20, 160));
		this.add(tabbedPane, BorderLayout.CENTER);
		tabbedPane.addTab(tabName, icon, beepBeepPanel);
		
	}
	
	//===========================================  Creation Methods ======================================
		/**
		 * 
		 * @param component
		 * @param buttonGroup
		 * @param label
		 * @return
		 */
		protected JRadioButton createRadioButton(final JPanel component, final ButtonGroup buttonGroup, final String label) {
			return createRadioButton(component, buttonGroup, label, true);
		}
		
		/**
		 * 
		 * @param component
		 * @param buttonGroup
		 * @param label
		 * @param leftAdjust
		 * @return
		 */
		protected JRadioButton createRadioButton(final JPanel component, final ButtonGroup buttonGroup, final String label,
				final boolean leftAdjust) {
			final JRadioButton button = SlickerFactory.instance().createRadioButton(label);
			button.setForeground(WidgetColors.TEXT_COLOR);
			button.setFont(button.getFont().deriveFont(Font.BOLD));
			button.setAlignmentX(Component.LEFT_ALIGNMENT);
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
		
		/**
		 * 
		 * @param component
		 * @param string
		 * @param dim
		 * @param leftAdjust
		 * @return
		 */
		protected JLabel createLabel(final JPanel component, final String string, Dimension dim, boolean leftAdjust, String alignment) {
			final JLabel label = new JLabel();
			label.setOpaque(false);
			label.setText(string);
			label.setForeground(WidgetColors.PROPERTIES_BACKGROUND);
			
			if (alignment == "left") {
				label.setAlignmentX(Component.LEFT_ALIGNMENT);

			}else {
				label.setAlignmentX(Component.RIGHT_ALIGNMENT);
			}
			
			label.setMaximumSize(dim);
			
			if(leftAdjust == true) {
				final JPanel lefty = new JPanel();
				lefty.setOpaque(false);
				lefty.setLayout(new BoxLayout(lefty, BoxLayout.X_AXIS));
				lefty.setMinimumSize(new Dimension(400, 50));
				lefty.add(Box.createHorizontalStrut(0));
				lefty.add(label);
				lefty.setAlignmentX(Component.RIGHT_ALIGNMENT);
				//lefty.setBorder(BorderFactory.createLineBorder(Color.RED, 1));
				component.add(lefty);
			}else{
				component.add(label);
			}
			return label;
		}
		
		/**
		 * 
		 * @param string
		 * @return
		 */
		protected JLabel createLabel(final String string) {
			final JLabel label = new JLabel(string);
			label.setForeground(WidgetColors.PROPERTIES_BACKGROUND);
			label.setFont(label.getFont().deriveFont(Font.BOLD, 10));
			return label;
		}
		
		/**
		 * 
		 * @param <T>
		 * @param c
		 * @return
		 */
		protected <T extends JComponent> T changeSize(final T c) {
			final Dimension minimumSize = c.getMinimumSize();
			if (minimumSize == null) {
				return c;
			}
			minimumSize.setSize(60, minimumSize.getHeight());
			c.setMinimumSize(minimumSize);
			return c;
		}
		
		@SuppressWarnings("deprecation")
		protected JTextPane createTxtPane(final JPanel component, final String string, Dimension dim, boolean leftAdjust, boolean title, float alignment) {
			final JTextPane label = new JTextPane();
			label.setOpaque(false);
			label.setText(string);
			label.setForeground(WidgetColors.PROPERTIES_BACKGROUND);
			label.setAlignmentX(alignment);
			label.setMinimumSize(dim);
			label.disable();
			if(title) {
				 SimpleAttributeSet attributeSet = new SimpleAttributeSet();  
			     StyleConstants.setBold(attributeSet, true);  
			     label.setCharacterAttributes(attributeSet, true);
			}
			
			if(leftAdjust == true) {
				final JPanel lefty = new JPanel();
				lefty.setOpaque(false);
				lefty.setLayout(new BoxLayout(lefty, BoxLayout.X_AXIS));
				lefty.add(Box.createHorizontalStrut(20));
				lefty.add(label);
				component.add(lefty);
			}else{
				component.add(label);
			}
			return label;
		}
		
		protected JPanel createPanel(JPanel parentComponent, Boolean opaque, int boxLayout) {
			JPanel panel = new JPanel();
			panel.setOpaque(opaque);
			panel.setLayout(new BoxLayout(panel, boxLayout));
			if(!(parentComponent == null)) {
				parentComponent.add(panel);
			}
			return panel;
	
		}
	
	
}
