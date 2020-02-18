package org.processmining.plugins.beepbeep.helloworld;

import java.io.InputStream;

import org.processmining.contexts.uitopia.annotations.UIImportPlugin;
import org.processmining.framework.abstractplugins.AbstractImportPlugin;
import org.processmining.framework.plugin.PluginContext;
import org.processmining.framework.plugin.annotations.Plugin;

@Plugin(name = "Import Person",
		parameterLabels = {"Filename"},
		returnLabels = {"Person"},
		returnTypes = {Person.class})
@UIImportPlugin(description = "Person", extensions = { "person" })
public class PersonImportPlugin extends AbstractImportPlugin {
	
	@Override
	protected Person importFromStream(PluginContext context, InputStream input, String filename, long fileSizeInBytes) throws Exception {
		// TODO Auto-generated method stub
		
		try {
			context.getFutureResult(0).setLabel("Person imported from " + filename);
			
		} catch (final Throwable e) {
			// Don't care if this fails
		}
		Person result = new Person();
		// Fill in object from input (to do...)
		return result;
	}

}
