package ca.uqac.lif.prom.cep.ptm;

import org.processmining.framework.plugin.annotations.Plugin;

import ca.uqac.lif.prom.cep.BeepBeepPlugin;
import ca.uqac.lif.prom.cep.ptm.models.BeepbeepBPMModel;

@Plugin(name = "Mine Log with BeepBeep", 
		returnLabels = { "Beepbeep BPM Model" }, 
		returnTypes = { BeepbeepBPMModel.class }, 
		parameterLabels = {"Log", "Parameters" }, 
		userAccessible = true)
public class PatTheMinerPlugin extends BeepBeepPlugin
{

}
