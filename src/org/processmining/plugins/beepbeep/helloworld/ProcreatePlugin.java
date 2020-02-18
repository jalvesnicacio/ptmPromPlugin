package org.processmining.plugins.beepbeep.helloworld;

import org.deckfour.uitopia.api.event.TaskListener.InteractionResult;
import org.processmining.contexts.uitopia.UIPluginContext;
import org.processmining.contexts.uitopia.annotations.UITopiaVariant;
import org.processmining.framework.plugin.PluginContext;
import org.processmining.framework.plugin.annotations.Plugin;
import org.processmining.framework.plugin.annotations.PluginVariant;
import org.processmining.framework.plugin.events.Logger.MessageLevel;
import org.processmining.framework.util.ui.widgets.ProMPropertiesPanel;
import org.processmining.framework.util.ui.widgets.ProMTextField;

@Plugin(name = "Procreate",
parameterLabels = { "Father", "Mother", "Procreation Configuration" },
returnLabels = { "Child" },
returnTypes = { Person.class })

public class ProcreatePlugin {
	@UITopiaVariant(
			affiliation = "University of Quebec in Chicoutimi", 
            author = "Jalves Nicacio", 
            email = "jalves.nicacio@gmail.com",
            uiLabel = UITopiaVariant.USEPLUGIN)
	@PluginVariant(requiredParameterLabels = { 0, 1, 2 })
	public static Person procreate(final PluginContext context, final Person father, final Person mother, final ProcreationConfiguration config) {
		context.log("Creating nwe Person", MessageLevel.NORMAL);
		Person child = new Person();
		child.setAge(0);
		if(config == null) {
			context.log("No configuration given", MessageLevel.ERROR);
			return null;
		}
		try {
			child.setName(new Name(config.getName(), father.getName().getLast()));
			context.log("New Person set up!", MessageLevel.DEBUG);
		} catch (Exception e) {
			context.log(e);
			return null;
		}
		context.log("About to successfully return", MessageLevel.TEST);
		return child;
	}
	
	
	@UITopiaVariant(
			affiliation = "University of Quebec in Chicoutimi", 
            author = "Jalves Nicacio", 
            email = "jalves.nicacio@gmail.com",
            uiLabel = UITopiaVariant.USEPLUGIN)
	@PluginVariant(requiredParameterLabels = { 0, 1 })
	public static Person procreate(final UIPluginContext context, final Person father, final Person mother) {
		// Classe ProcreationConfiguration precisa que seja passada uma string no construtor...
		context.getProgress().setIndeterminate(true);
		ProcreationConfiguration config = new ProcreationConfiguration("Child");
		config = populate(context, config);
		if (config == null) {
			context.getFutureResult(0).cancel(true);
			return null;
		}
		return procreate(context, father, mother, config);
	}
	
	/*
	 * Método populate, deve fazer o que exatamente?? Exibe uma tela com informações de configuração de procriação.
	 */
	public static ProcreationConfiguration populate(UIPluginContext context, ProcreationConfiguration config) {
		ProMPropertiesPanel panel = new ProMPropertiesPanel("Configure Procreation");
	    ProMTextField name = panel.addTextField("Name", config.getName());
	    final InteractionResult interactionResult = context.showConfiguration("Setup Procreation", panel);
	    if (interactionResult == InteractionResult.FINISHED ||
	        interactionResult == InteractionResult.CONTINUE ||
	        interactionResult == InteractionResult.NEXT) {
	      config.setName(name.getText());
	      return config;
	    }
	    return null;
		
	}

	
}
