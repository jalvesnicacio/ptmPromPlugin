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
package src.org.processmining.plugins.beepbeep.miner;

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
import src.org.processmining.plugins.beepbeep.miner.functions.DeltaFunction;
import src.org.processmining.plugins.beepbeep.miner.models.BeepbeepBPMModel;
import src.org.processmining.plugins.beepbeep.miner.models.PTMSettingModel;
import src.org.processmining.plugins.beepbeep.miner.models.ReferenceTrend;
import src.org.processmining.plugins.beepbeep.miner.models.Trace;
import src.org.processmining.plugins.beepbeep.miner.processors.Beta;
import src.org.processmining.plugins.beepbeep.miner.views.DistanceStep;
import src.org.processmining.plugins.beepbeep.miner.views.PatternStep;
import src.org.processmining.plugins.beepbeep.miner.views.SummaryStep;
import src.org.processmining.plugins.beepbeep.miner.views.ThresholdStep;
import src.org.processmining.plugins.beepbeep.miner.views.TrendStep;
import src.org.processmining.plugins.beepbeep.miner.views.WindowsStep;

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
@Plugin(name = "Mine Log with BeepBeep", 
		returnLabels = { "Beepbeep BPM Model" }, 
		returnTypes = { BeepbeepBPMModel.class }, 
		parameterLabels = {"Log", "Parameters" }, 
		userAccessible = true)
public class BeepBeepPlugin {
	
	/**
	 * ProM and Beepbeep integration. 
	 * @throws IOException 
	 */
	@UITopiaVariant(affiliation = "UQAC", author = "Jalves Nicacio", email = "jalves.nicacio@gmail.com")
	@PluginVariant(variantLabel = "Integration ProM - Beepbeep", requiredParameterLabels = {0})
	public BeepbeepBPMModel beepBeepWizard(UIPluginContext context, XLog log) throws IOException {
		
		BeepbeepBPMModel bpmModel = setParameters(context, log, new BeepbeepMiningParameters());
		PTMSettingModel settingsModel = new PTMSettingModel();
		settingsModel.setBpmModel(bpmModel);
		
		@SuppressWarnings("unchecked")
		ListWizard<PTMSettingModel> wizard = new ListWizard<PTMSettingModel>(
				new PatternStep(), 
				new WindowsStep(),
				new TrendStep(),
				new DistanceStep(),
				new ThresholdStep(),
				new SummaryStep());
		settingsModel = ProMWizardDisplay.show(context, wizard, settingsModel);
		
		if(settingsModel != null) {
			return mine(context, log, settingsModel);
		}
		return bpmModel;
	}
	
	/**
	 * Mining using given parameter values.
	 * 
	 * @param context
	 *            The given plug-in context.
	 * @param log
	 *            The given event log.
	 * @param parameters
	 *            The given parameter values.
	 * @return The beepbeep model mined from the given log using the given
	 *         parameter values.
	 *         
	 */
	
	public BeepbeepBPMModel setParameters(PluginContext context, XLog log, BeepbeepMiningParameters parameters) {
		Collection<BeepbeepMiningConnection> connections;
		
		/*
		 * Create XlogInfo based on log
		 */
		XLogInfo info = XLogInfoFactory.createLogInfo(log);
		
		/*
		 * Create a BpmModel based on info
		 */
		BeepbeepBPMModel bpmModel = new BeepbeepBPMModel(info);
		
		/*
		 * set classifiers on parameters
		 */
		for (XEventClassifier classifier : info.getEventClassifiers()) {
			parameters.setClassifier(classifier);
		}
		
		try {
			connections = context.getConnectionManager().getConnections(BeepbeepMiningConnection.class, context, log);
			
			for (BeepbeepMiningConnection connection : connections) {
				if (connection.getObjectWithRole(BeepbeepMiningConnection.LOG).equals(log)
						&& connection.getParameters().equals(parameters)) {
					return connection.getObjectWithRole(BeepbeepMiningConnection.MODEL);
				}
			}
		} catch (ConnectionCannotBeObtained e) {
		}

		context.addConnection(new BeepbeepMiningConnection(log, bpmModel, parameters));
		return bpmModel;
	}	

	
	
	/*
	 * The actual mining of an event log for a Beepbeep model given parameter
	 * values.
	 */
	private BeepbeepBPMModel mine(PluginContext context, XLog log, PTMSettingModel settingsModel) {
		
		/************************************************************************
		Create a TrendDistance Processor
		//<P> The type of the pattern | 
		//<Q> The type returned by the beta processor | 
		//<R> The type returned by the distance function
		
		public TrendDistance(java.lang.Object pattern, 
		   					 int n, 
		   					 ca.uqac.lif.cep.Processor beta, 
		   					 ca.uqac.lif.cep.functions.Function delta, 
		   					 java.lang.Object d, 
		   					 ca.uqac.lif.cep.functions.BinaryFunction comp);
		*************************************************************************/
		
		//Sources:
		BeepbeepBPMModel model = settingsModel.getBpmModel();
		List<Trace> traces = model.getTraceInstances();
		
		/**
		 * Set attributes of TrendDistance Processor:
		 * version 20.04: 
		 * 		- DeltaFunction does not yet work with ManhattanDistance and EuclidianDistance. 
		 * 			Both functions 
		 * 			must receive a set of points.
		 */
		ReferenceTrend pattern 	= 		settingsModel.getTrendReference(); 
		int window 				= 		settingsModel.getPresentWindow();
		Processor beta  		= 		new Beta(settingsModel.getElementTrendOption(), settingsModel.getTrendProcessor());
		Window windowProcessor = new Window(beta, window);
		Function delta 			= 		new DeltaFunction(settingsModel.getDistanceFunction());			
		Float d 				= 		settingsModel.getThresholdValue();
		BinaryFunction<Number, Number, Boolean> comp = settingsModel.getThresholdFunction();		
		
		//Create TrendDistance Processor
//		TrendDistance<ReferenceTrend, Number, Number> td		
//		= new TrendDistance<ReferenceTrend, Number, Number>(
//			      pattern, 									// Reference trend
//			      window,							 		// Window width
//			      beta, 									// beta-processor 
//			      delta, 									// distance metric
//			      d, 										// distance threshold
//			      comp 										// comparison function
//			      );
		
		TrendDistance<ReferenceTrend, Number, Number> td 
		= new TrendDistance<ReferenceTrend, Number, Number>(
				pattern, 			// Reference trend
				windowProcessor, 	// Window Processor
				delta, 				// distance metric
				d, 					// distance threshold
				comp				// comparison function
				);
		
		QueueSource qs = new QueueSource();
		qs.loop(false);
		
		Connector.connect(qs,td);
		Pullable p = td.getPullableOutput();
		windowProcessor.getEventTracker();
		
		for (Trace trc : traces) {
			Object[] events = trc.getEvents().toArray();
			qs.setEvents(events);
			context.getProgress().setMaximum(log.size()); //Prom
			int i = 0;
			
			while(p.hasNext()) {
				Boolean dvt = (Boolean) p.pull();
				System.out.println(dvt);
				if (dvt == false) {
				
					//2020-04-21: How can I save inputs and output from the WindowProcessor processor?
				}
				i++;
				context.getProgress().inc();
			}
			
//----------------------------------------------------------------------------------			
			
//			for (Event evt : trc.getEvents()) {
//				p.push(evt);
//				Boolean obj = (Boolean) queue.remove();
//				evt.addResult(pattern, obj); //*** Precisa trocar o pattern pelo resultado do Beta!!
//			}
		}
		
		
//		Trace trace = traces.get(0);
//		Object[] events = trace.getEvents().toArray();
//		p.push((Event)events[0]);
//		Object obj = queue.remove();
//		System.out.println(obj);
//		
		
		
		
		
		/**
		 * 
		 * Ex
		 * 
		 * new TrendDistance<TrendReference, TrendReference, Number>(
			      500, 									// Reference trend
			      2,							 		// Window width
			      runningAverage, 									// beta-processor 
			      manhattanDistance, 									// distance metric
			      550, 										// distance threshold
			      lessThan 										// comparison function
			      );
			      
			      
		
		Questions:
		1) como aplicar beta em um trace? ok
		2) como extrair pattern de TrendReference?ok
		
		Exemplos de tendências:
		1) Qual a duração de um trace?
		2) Quantas vezes uma atividade é executada dentro de um trace?
		3) Quanto custa um trace?
		
		as funções aplicadas em Beta são utilizadas sobre os eventos dentro do trace. ok
		
		*/
		
//-----------------------------------------------
		
		return model;
	}
	
}
