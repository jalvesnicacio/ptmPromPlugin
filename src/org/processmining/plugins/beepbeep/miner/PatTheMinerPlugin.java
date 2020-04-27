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
package org.processmining.plugins.beepbeep.miner;

import java.io.IOException;
import java.util.Collection;
import java.util.List;

import org.deckfour.xes.classification.XEventClassifier;
import org.deckfour.xes.info.XLogInfo;
import org.deckfour.xes.info.XLogInfoFactory;
import org.deckfour.xes.model.XLog;
import org.processmining.contexts.uitopia.UIPluginContext;
import org.processmining.contexts.uitopia.annotations.UITopiaVariant;
import org.processmining.framework.connections.ConnectionCannotBeObtained;
import org.processmining.framework.plugin.PluginContext;
import org.processmining.framework.plugin.annotations.Plugin;
import org.processmining.framework.plugin.annotations.PluginVariant;
import org.processmining.framework.util.ui.wizard.ListWizard;
import org.processmining.framework.util.ui.wizard.ProMWizardDisplay;
import org.processmining.plugins.beepbeep.BeepBeepMiningConnection;
import org.processmining.plugins.beepbeep.BeepBeepMiningParameters;
import org.processmining.plugins.beepbeep.BeepBeepPlugin;
import org.processmining.plugins.beepbeep.ProcessesPlugin;
import org.processmining.plugins.beepbeep.miner.functions.DeltaFunction;
import org.processmining.plugins.beepbeep.miner.models.BeepbeepBPMModel;
import org.processmining.plugins.beepbeep.miner.models.PTMSettingModel;
import org.processmining.plugins.beepbeep.miner.models.ReferenceTrend;
import org.processmining.plugins.beepbeep.miner.processors.Beta;
import org.processmining.plugins.beepbeep.miner.views.DistanceStep;
import org.processmining.plugins.beepbeep.miner.views.PatternStep;
import org.processmining.plugins.beepbeep.miner.views.SummaryStep;
import org.processmining.plugins.beepbeep.miner.views.ThresholdStep;
import org.processmining.plugins.beepbeep.miner.views.TrendStep;
import org.processmining.plugins.beepbeep.miner.views.WindowsStep;
import org.processmining.plugins.beepbeep.models.Trace;

import ca.uqac.lif.cep.Connector;
import ca.uqac.lif.cep.Processor;
import ca.uqac.lif.cep.Pullable;
import ca.uqac.lif.cep.functions.BinaryFunction;
import ca.uqac.lif.cep.functions.Function;
import ca.uqac.lif.cep.peg.TrendDistance;
import ca.uqac.lif.cep.tmf.QueueSource;
import ca.uqac.lif.cep.tmf.Window;


/**
 * Mining plug-in that mines an event log for a workshop model.
 * 
 * @author jalvesnicacio
 * 
 */

public class PatTheMinerPlugin extends BeepBeepPlugin {
	
	@Override
	public ProcessesPlugin createProcessPlugin()
	{
		return new PtmProcess();
	}
	
}
	

