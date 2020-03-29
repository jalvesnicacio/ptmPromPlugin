package org.processmining.plugins.beepbeep.miner;

import java.io.IOException;
import java.util.Collection;

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
import org.processmining.plugins.beepbeep.miner.views.DistanceStep;
import org.processmining.plugins.beepbeep.miner.views.PatternStep;
import org.processmining.plugins.beepbeep.miner.views.SummaryStep;
import org.processmining.plugins.beepbeep.miner.views.ThresholdStep;
import org.processmining.plugins.beepbeep.miner.views.TrendStep;
import org.processmining.plugins.beepbeep.miner.views.WindowsStep;


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
		
		BeepbeepBPMModel model = settingsModel.getBpmModel();
		
		TrendReference pattern = settingsModel.getTrendReference();
		
		//Create a TrendDistance Processor
		//<P> The type of the pattern | 
		//<Q> The type returned by the beta processor | 
		//<R> The type returned by the distance function
		
		/*
		 
		 
		TrendDistance<TrendReference, TrendReference, Number> td		
		= new TrendDistance<TrendReference, TrendReference, Number>(
			      pattern, 									// Reference trend
			      settingsModel.getPresentWindow(), 		// Window width
			      settingsModel.executeTrendOption(), 		// beta-processor 
			      Numbers.subtraction, 						// distance metric
			      0, 										// distance threshold
			      Numbers.isGreaterOrEqual 					// comparison function
			      );
		
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
