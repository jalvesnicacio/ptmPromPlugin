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

import javax.swing.JPanel;
import javax.swing.JTextPane;

import org.processmining.framework.util.ui.widgets.ProMPropertiesPanel;
import org.processmining.plugins.beepbeep.miner.models.PTMSettingModel;

public class SummaryPanel extends BeepBeepView
{
	// protected PTMSettingModel model;

	protected static final long serialVersionUID = 1L;

	public SummaryPanel(PTMSettingModel model)
	{
		super("Summary", "/org/processmining/plugins/beepbeep/miner/views/icons/trendIcon.png",
				"To summarize, you requested the following data mining operation:");
		ProMPropertiesPanel proMPanel = super.beepBeepPanel;
		String pattern, trend, distance, threshold,
				windows = "Present Window: " + model.getPresentWindow();

		// Pattern and Windows:
		String patternKind = model.getTrendReference().getDataMiningPattern().toString();

		if (patternKind == "SELF_CORRELATED")
		{
			pattern = "Trend reference based on autocorrelated value";
			windows += " Past Window: " + model.getPastWindow();
		}
		else
		{
			pattern = "Trend reference based on externally supplied value: "
					+ model.getTrendReference().getDataMiningPatternValue();
		}
		JTextPane patternTxtPane = createTxtPane(new JPanel(), pattern, new Dimension(450, 50), false,
				true, Component.RIGHT_ALIGNMENT);
		JTextPane windowsTxtPane = createTxtPane(new JPanel(), windows, new Dimension(450, 50), false,
				true, Component.RIGHT_ALIGNMENT);

		// Trend:
		String trendChoice = model.getTrendChoice();
		String trendElOption = model.getElementTrendOption();
		switch (trendChoice)
		{
		case "runningAverage":
			trendChoice = "running average";
			break;
		case "vectorMoment":
			trendChoice = "vector of moment";
			break;
		case "distinctOccurrences":
			trendChoice = "distinct occurrences";
			break;
		case "valueDistribution":
			trendChoice = "value distribution";
			break;
		default:
			trendChoice = "cumulative sum";
			break;
		}
		switch (trendElOption)
		{
		case "directValue":
			trendElOption = "direct value";
			break;

		default:
			break;
		}
		trend = "Compute the " + trendChoice + " for element " + trendElOption;
		JTextPane trendTxtPane = createTxtPane(new JPanel(), trend, new Dimension(450, 50), false, true,
				Component.RIGHT_ALIGNMENT);

		// Distance
		distance = model.getDistance();
		switch (distance)
		{
		case "manhattanDistance":
			distance = "Manhattan distance function";
			break;
		case "euclidianDistance":
			distance = "Euclidian distance function";
			break;
		case "scalarDifference":
			distance = "Scalar difference function";
			break;
		default:
			distance = "ratio function";
			break;
		}
		JTextPane distanceTxtPane = createTxtPane(new JPanel(), distance, new Dimension(450, 50), false,
				true, Component.RIGHT_ALIGNMENT);

		// Threshold:
		String thresholdOption = model.getThresholdOption();
		switch (thresholdOption)
		{
		case "smallerThan":
			thresholdOption = "smaller than";
			break;

		default:
			thresholdOption = "larger than";
			break;
		}
		threshold = "Threshold is " + thresholdOption + " of " + model.getThresholdValue() + ".";
		JTextPane thresholdTxtPane = createTxtPane(new JPanel(), threshold, new Dimension(450, 50),
				false, true, Component.RIGHT_ALIGNMENT);

		proMPanel.addProperty("Pattern", patternTxtPane);
		proMPanel.addProperty("Window", windowsTxtPane);
		proMPanel.addProperty("Trend Computing", trendTxtPane);
		proMPanel.addProperty("Distance Function", distanceTxtPane);
		proMPanel.addProperty("Threshold", thresholdTxtPane);

	}

}
