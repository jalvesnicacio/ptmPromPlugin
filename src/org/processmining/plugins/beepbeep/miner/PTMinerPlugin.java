package org.processmining.plugins.beepbeep.miner;

import java.io.IOException;
import java.util.Collection;
import java.util.List;
import java.util.Queue;

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
import org.processmining.plugins.beepbeep.miner.models.BeepbeepBPMModel;
import org.processmining.plugins.beepbeep.miner.models.PTMSettingModel;
import org.processmining.plugins.beepbeep.miner.models.PTMSettingModel.TrendReference;
import org.processmining.plugins.beepbeep.miner.models.Trace;
import org.processmining.plugins.beepbeep.miner.views.DistanceStep;
import org.processmining.plugins.beepbeep.miner.views.PatternStep;
import org.processmining.plugins.beepbeep.miner.views.SummaryStep;
import org.processmining.plugins.beepbeep.miner.views.ThresholdStep;
import org.processmining.plugins.beepbeep.miner.views.TrendStep;
import org.processmining.plugins.beepbeep.miner.views.WindowsStep;

import ca.uqac.lif.cep.Connector;
import ca.uqac.lif.cep.Processor;
import ca.uqac.lif.cep.Pushable;
import ca.uqac.lif.cep.functions.BinaryFunction;
import ca.uqac.lif.cep.functions.Function;
import ca.uqac.lif.cep.peg.TrendDistance;
import ca.uqac.lif.cep.tmf.QueueSink;
import ca.uqac.lif.cep.util.Numbers;


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
public class PTMinerPlugin {
	
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
		
		//
		BeepbeepBPMModel model = settingsModel.getBpmModel();
		List<Trace> traces = model.getTraceInstances();
		
		TrendReference pattern = settingsModel.getTrendReference();
		int window = settingsModel.getPresentWindow();
		Processor beta = settingsModel.executeTrendFunction();
		Function delta = Numbers.subtraction;
		Float d = settingsModel.getThresholdValue();
		BinaryFunction<Number, Number, Boolean> comp = settingsModel.getThresholdFunction();		
		
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
		 
		TrendDistance<TrendReference, TrendReference, Number> td		
		= new TrendDistance<TrendReference, TrendReference, Number>(
			      pattern, 									// Reference trend
			      window,							 		// Window width
			      beta, 									// beta-processor 
			      delta, 									// distance metric
			      d, 										// distance threshold
			      comp 										// comparison function
			      );
		QueueSink qs = new QueueSink();
		Queue<Object> queue = qs.getQueue();
		Connector.connect(td,qs);
		Pushable p = td.getPushableInput();
		
		for (Trace trace : traces) {
			p.push(trace);
			Object obj = queue.remove();
			System.out.println(obj);
		}
		
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
		1) como aplicar beta em um trace?
		2) como extrair pattern de TrendReference?
		
		Exemplos de tendências:
		1) Qual a duração de um trace?
		2) Quantas vezes uma atividade é executada dentro de um trace?
		3) Quanto custa um trace?
		
		as funções aplicadas em Beta são utilizadas sobre os eventos dentro do trace.
		
		*/
		
		//-----------------------------------------------
		
		
		/*
		 * Inform the progress bar when we're done.
		 */
		context.getProgress().setMaximum(log.size());
		
		/*
		 * Fill the model based on the direct succession as encountered in the
		 * log.
		 */
//		XEventClass fromEventClass = null, toEventClass = null;
//		for (XTrace trace : log) {
//			XEvent fromEvent = null;
//			for (XEvent toEvent : trace) {
//				fromEventClass = toEventClass;
//				toEventClass = info.getEventClasses().getClassOf(toEvent);
//				//Qual é a classe do evento?
//				//context.log("===> classe de toEvent: "+ toEventClass.toString(), MessageLevel.DEBUG);
//				if (fromEvent != null) {
//					model.addDirectSuccession(fromEventClass, toEventClass, 1); //<=====
//				}
//				fromEvent = toEvent;
//			}
			/*
			 * Advance the progress bar.
			 */
			//context.getProgress().inc();
		//}
		/*
		 * Return the model
		 */
		return model;
	}
	
}
