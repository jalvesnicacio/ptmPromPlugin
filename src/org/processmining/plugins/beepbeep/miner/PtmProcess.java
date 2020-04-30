package org.processmining.plugins.beepbeep.miner;

import java.util.List;

import org.deckfour.xes.model.XTrace;
import org.processmining.contexts.uitopia.UIPluginContext;
import org.processmining.framework.util.ui.wizard.ListWizard;
import org.processmining.framework.util.ui.wizard.ProMWizardDisplay;
import org.processmining.plugins.beepbeep.BeepBeepLogModel;
import org.processmining.plugins.beepbeep.BeepBeepResult;
import org.processmining.plugins.beepbeep.ProcessesPlugin;
import org.processmining.plugins.beepbeep.ResultEntry;
import org.processmining.plugins.beepbeep.miner.functions.DeltaFunction;
import org.processmining.plugins.beepbeep.miner.models.PTMSettingModel;
import org.processmining.plugins.beepbeep.miner.models.ReferenceTrend;
import org.processmining.plugins.beepbeep.miner.processors.Beta;
import org.processmining.plugins.beepbeep.miner.views.DistanceStep;
import org.processmining.plugins.beepbeep.miner.views.PatternStep;
import org.processmining.plugins.beepbeep.miner.views.SummaryStep;
import org.processmining.plugins.beepbeep.miner.views.ThresholdStep;
import org.processmining.plugins.beepbeep.miner.views.TrendStep;
import org.processmining.plugins.beepbeep.miner.views.WindowsStep;

import ca.uqac.lif.cep.Connector;
import ca.uqac.lif.cep.Processor;
import ca.uqac.lif.cep.Pullable;
import ca.uqac.lif.cep.functions.BinaryFunction;
import ca.uqac.lif.cep.functions.Function;
import ca.uqac.lif.cep.peg.TrendDistance;
import ca.uqac.lif.cep.tmf.Window;
import ca.uqac.lif.cep.xes.XTraceSource;

public class PtmProcess implements ProcessesPlugin
{

	private PTMSettingModel settingsModel;

	@Override
	public BeepBeepResult process(UIPluginContext context, BeepBeepLogModel logModel)
	{

		// BeepBeepLogModel model = this.settingsModel.getLogModel();
		List<XTrace> traces = logModel.getTraceInstances();
		BeepBeepResult result = new BeepBeepResult();

		/**
		 * Set attributes of TrendDistance Processor: version 20.04: - DeltaFunction
		 * does not yet work with ManhattanDistance and EuclidianDistance. Both
		 * functions must receive a set of points.
		 */
		ReferenceTrend pattern = settingsModel.getTrendReference();
		int window = settingsModel.getPresentWindow();
		Processor beta = new Beta(settingsModel.getElementTrendOption(),
				settingsModel.getTrendProcessor());
		Window windowProcessor = new Window(beta, window);
		Function delta = new DeltaFunction(settingsModel.getDistanceFunction());
		Float d = settingsModel.getThresholdValue();
		BinaryFunction<Number, Number, Boolean> comp = settingsModel.getThresholdFunction();

		// Create TrendDistance Processor
		// TrendDistance<ReferenceTrend, Number, Number> td
		// = new TrendDistance<ReferenceTrend, Number, Number>(
		// pattern, // Reference trend
		// window, // Window width
		// beta, // beta-processor
		// delta, // distance metric
		// d, // distance threshold
		// comp // comparison function
		// );

		TrendDistance<ReferenceTrend, Number, Number> td = new TrendDistance<ReferenceTrend, Number, Number>(
				pattern, // Reference trend
				windowProcessor, // Window Processor
				delta, // distance metric
				d, // distance threshold
				comp // comparison function
				);

		for (XTrace trc : traces)
		{
			XTraceSource qs = new XTraceSource(trc);
			Connector.connect(qs, td);
			Pullable p = td.getPullableOutput();
			// context.getProgress().setMaximum(log.size()); //Prom
			int i = 0;

			while (p.hasNext())
			{
				Boolean dvt = (Boolean) p.pull();
				System.out.println("result " + i + ": " + dvt);
				if (dvt == false)
				{
					ResultEntry entry = new ResultEntry(dvt);
					result.put(entry);

					// 2020-04-21: How can I save inputs and output from the WindowProcessor
					// processor?
				}
				i++;
				// context.getProgress().inc();
			}
		}

		return result;
	}

	@Override
	public void executeWizard(UIPluginContext context, BeepBeepLogModel logModel)
	{
		this.settingsModel = new PTMSettingModel();
		this.settingsModel.setLogModel(logModel);

		@SuppressWarnings("unchecked")
		ListWizard<PTMSettingModel> wizard = new ListWizard<PTMSettingModel>(new PatternStep(),
				new WindowsStep(), new TrendStep(), new DistanceStep(), new ThresholdStep(),
				new SummaryStep());
		this.settingsModel = ProMWizardDisplay.show(context, wizard, this.settingsModel);

	}

}
