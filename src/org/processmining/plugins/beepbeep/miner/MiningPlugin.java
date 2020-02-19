package org.processmining.plugins.beepbeep.miner;

import java.io.IOException;
import java.util.Collection;

import org.deckfour.uitopia.api.event.TaskListener.InteractionResult;
import org.deckfour.xes.classification.XEventClass;
import org.deckfour.xes.info.XLogInfo;
import org.deckfour.xes.info.XLogInfoFactory;
import org.deckfour.xes.model.XEvent;
import org.deckfour.xes.model.XLog;
import org.deckfour.xes.model.XTrace;
import org.processmining.contexts.uitopia.UIPluginContext;
import org.processmining.contexts.uitopia.annotations.UITopiaVariant;
import org.processmining.framework.connections.ConnectionCannotBeObtained;
import org.processmining.framework.plugin.PluginContext;
import org.processmining.framework.plugin.annotations.Plugin;
import org.processmining.framework.plugin.annotations.PluginVariant;
import org.processmining.framework.plugin.events.Logger.MessageLevel;
import org.processmining.models.beepbeep.BeepbeepModel;

import ca.uqac.lif.cep.tmf.QueueSource;

/**
 * Mining plug-in that mines an event log for a workshop model.
 * 
 * @author jalvesnicacio
 * 
 */
@Plugin(name = "Mine a Beepbeep event Model", 
		returnLabels = { "Beepbeep Model" }, 
		returnTypes = { BeepbeepModel.class }, 
		parameterLabels = {"Log", "Parameters" }, 
		userAccessible = true)
public class MiningPlugin {
	
	/**
	 * Beepbeep integration test - 3
	 * @throws IOException 
	 */
	@UITopiaVariant(affiliation = "UQAC", author = "Jalves Nicacio", email = "jalves.nicacio@gmail.com")
	@PluginVariant(variantLabel = "Integration test of Beepbeep", requiredParameterLabels = {})
	public String beepBeepTest(UIPluginContext context) throws IOException {
		QueueSource q = new QueueSource();
		q.setEvents("foo");
		
		/*
		 * GUI test
		 */
		BeepbeepMiningParameters parameters = new BeepbeepMiningParameters();
		FooBeepbeepDialog dialog = new FooBeepbeepDialog();
		InteractionResult result = context.showWizard("BeepBeep Data Mining Wizard Plugin", true, true, dialog);
		if (result != InteractionResult.FINISHED) {
			return null;
		}
		//return mineParameters(context, log, parameters);
		
		return (String) q.getPullableOutput().pull();
	}

	/**
	 * Mining using default parameter values.
	 * 
	 * @param context
	 *            The given plug-in context.
	 * @param log
	 *            The given event log.
	 * @return The beepbeep model mined from the given log using the default
	 *         parameter values.
	 */
	@UITopiaVariant(affiliation = "UQAC", author = "Jalves Nicacio", email = "jalves.nicacio@gmail.com")
	@PluginVariant(variantLabel = "Mine a Beepbeep Model, default", requiredParameterLabels = { 0 })
	public BeepbeepModel mineDefault(PluginContext context, XLog log) {
        context.log("Iniciando o plug-in Beepbeep: metodo mineDefault basico", MessageLevel.DEBUG);
		return mineParameters(context, log, new BeepbeepMiningParameters());
	}
	
	/**
	 * Mining using user-provided parameter values.
	 * 
	 * @param context
	 *            The given GUI-aware plug-in context.
	 * @param log
	 *            The given event log.
	 * @return The beepbeep model mined from the given log using the
	 *         user-provided parameter values.
	 */
	@UITopiaVariant(affiliation = "UQAC", author = "Jalves Nicacio", email = "jalves.nicacio@gmail.com")
	@PluginVariant(variantLabel = "Mine a Beepbeep Model, dialog", requiredParameterLabels = { 0 })
	public BeepbeepModel mineDefault(UIPluginContext context, XLog log) {
		BeepbeepMiningParameters parameters = new BeepbeepMiningParameters();
		BeepbeepMiningDialog dialog = new BeepbeepMiningDialog(log, parameters);
		InteractionResult result = context.showWizard("Test of GUI for PatTheMiner Palette Plugin", true, true, dialog);
		if (result != InteractionResult.FINISHED) {
			return null;
		}
		return mineParameters(context, log, parameters);
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
	 */
	@UITopiaVariant(affiliation = "UQAC", author = "Jalves Nicacio", email = "jalves.nicacio@gmail.com")
	@PluginVariant(variantLabel = "Mine a Beepbeep Model, parameterized", requiredParameterLabels = { 0, 1 })
	public BeepbeepModel mineParameters(PluginContext context, XLog log, BeepbeepMiningParameters parameters) {
		Collection<BeepbeepMiningConnection> connections;
		try {
			//try get the connections. Wich connections?
			connections = context.getConnectionManager().getConnections(BeepbeepMiningConnection.class, context, log);
			
			for (BeepbeepMiningConnection connection : connections) {
				if (connection.getObjectWithRole(BeepbeepMiningConnection.LOG).equals(log)
						&& connection.getParameters().equals(parameters)) {
					return connection.getObjectWithRole(BeepbeepMiningConnection.MODEL);
				}
			}
		} catch (ConnectionCannotBeObtained e) {
		}
		BeepbeepModel model = mine(context, log, parameters);
		context.addConnection(new BeepbeepMiningConnection(log, model, parameters));
		return model;
	}
	
	/*
	 * The actual mining of an event log for a beepbeep model given parameter
	 * values.
	 */
	private BeepbeepModel mine(PluginContext context, XLog log, BeepbeepMiningParameters parameters) {
		
		/*
		 * Create event classes based on the given classifier
		 */
		XLogInfo info = XLogInfoFactory.createLogInfo(log, parameters.getClassifier());
		
		/*
		 * Create an empty model.
		 */
		BeepbeepModel model = new BeepbeepModel(info.getEventClasses());
		/*
		 * Inform the progress bar when we're done.
		 */
		context.getProgress().setMaximum(log.size());
		/*
		 * Fill the model based on the direct succession as encountered in the
		 * log.
		 */
		XEventClass fromEventClass = null, toEventClass = null;
		for (XTrace trace : log) {
			XEvent fromEvent = null;
			for (XEvent toEvent : trace) {
				fromEventClass = toEventClass;
				toEventClass = info.getEventClasses().getClassOf(toEvent);
				//Qual é a classe do evento?
				//context.log("===> classe de toEvent: "+ toEventClass.toString(), MessageLevel.DEBUG);
				if (fromEvent != null) {
					model.addDirectSuccession(fromEventClass, toEventClass, 1);
				}
				fromEvent = toEvent;
			}
			/*
			 * Advance the progress bar.
			 */
			context.getProgress().inc();
		}
		/*
		 * Return the model
		 */
		return model;
	}
}
