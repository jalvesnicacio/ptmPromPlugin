package org.processmining.plugins.beepbeep.helloworld;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;

import org.processmining.contexts.uitopia.annotations.UIExportPlugin;
import org.processmining.contexts.uitopia.annotations.UITopiaVariant;
import org.processmining.framework.plugin.PluginContext;
import org.processmining.framework.plugin.annotations.Plugin;
import org.processmining.framework.plugin.annotations.PluginVariant;

@Plugin(name = "Export Person",
		parameterLabels = { "Person", "File" },
		returnLabels = {},
		returnTypes = {})
@UIExportPlugin(description = "Person file", extension = "person")
public class ExportPersonPlugin {
	
	@UITopiaVariant(
			affiliation = "University of Quebec in Chicoutimi", 
            author = "Jalves Nicacio", 
            email = "jalves.nicacio@gmail.com")
	@PluginVariant(requiredParameterLabels = { 0, 1 })
	public void export(PluginContext context, Person person, File file) throws IOException{
		FileWriter writer = new FileWriter(file);
		PrintWriter pwriter = new PrintWriter(writer);
		pwriter.print(person.getName().getFirst());
		pwriter.print(' ');
		pwriter.println(person.getName().getLast());
		pwriter.close();
	}

}
