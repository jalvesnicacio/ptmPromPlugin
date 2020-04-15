package org.processmining.plugins.beepbeep.miner.models;

import org.processmining.plugins.beepbeep.miner.processors.BetaCumulate;
import org.processmining.plugins.beepbeep.miner.processors.BetaDistinctOccurences;
import org.processmining.plugins.beepbeep.miner.processors.BetaRunningAverage;
import org.processmining.plugins.beepbeep.miner.processors.BetaRunningMoments;
import org.processmining.plugins.beepbeep.miner.processors.BetaValueDistribution;
import org.processmining.plugins.beepbeep.miner.processors.TrendProcessor;

import ca.uqac.lif.cep.functions.BinaryFunction;
import ca.uqac.lif.cep.util.Numbers;

public class PTMSettingModel {
	
	private BeepbeepBPMModel bpmModel;
	private TrendReference trendReference;
	private int pastWindow, presentWindow;
	private String distance, thresholdOption;
	float thresholdValue;
	
	
	public String toString() {
		//return super.toString();
		String s = "PTM Model: [dataMiningPattern = "+this.trendReference.getDataMiningPattern()+" | ";
		s = s + "pastWindow = " + this.getPastWindow() + " presentWindow = " + this.getPresentWindow();
		s = s + "]\n";
		return s;
	}
	
	//bpmModel:
	public void setBpmModel(BeepbeepBPMModel bpmModel) {
		this.bpmModel = bpmModel;
	}
	
	public BeepbeepBPMModel getBpmModel(){
		return this.bpmModel;
	}
	
	//Trend Reference:
	public TrendReference getTrendReference() {
		return this.trendReference;
	}
	
	public void setTrendReference(TrendReference trend) {
		this.trendReference = trend;
	}
	
	//Past and Present Windows
	public void setPastWindow(int pastwindow) {
		this.pastWindow = pastwindow;
	}

	public void setPresentWindow(int presentWindow) {
		this.presentWindow = presentWindow;
	}
	
	public int getPastWindow() {
		return this.pastWindow;
	}
	public int getPresentWindow() {
		return this.presentWindow;
	}
	
	
	//Trend Option
	public void setElementTrendOption(String elementTrendOption) {
		this.trendReference.setAttributeName(elementTrendOption); 	
	}

	public void setTrendChoice(String trendChoice) {
		this.trendReference.setTrendChoice(trendChoice); 
	}
	
	public String getElementTrendOption() {
		return this.trendReference.getAttributeName();
	}
	public String getTrendChoice() {
		return this.trendReference.getTrendChoice();
	}
	
	
	
	// Distance function
	public void setDistance(String distance) {
		this.distance = distance;
	}
	public String getDistance() {
		return this.distance;
	}

	//Threshold
	public void setThresholdOption(String thresholdOption) {
		this.thresholdOption = thresholdOption;
	}
	public void setThresholdValue(float thresholdValue) {
		this.thresholdValue = thresholdValue;		
	}
	public String getThresholdOption() {
		return this.thresholdOption;
	}
	public float getThresholdValue() {
		return this.thresholdValue;
	}
	public BinaryFunction<Number, Number, Boolean> getThresholdFunction() {
		//Numbers.isGreaterOrEqual 	
		if (this.thresholdOption == "Smaller than") {
			return Numbers.isLessThan;
		}else {
			return Numbers.isGreaterOrEqual;
		}
	}
	
	
	
	public TrendProcessor getTrendProcessor() {
		TrendProcessor tp = null;
		switch (this.trendReference.getTrendChoice()) {
			case "cumulativeSum" :
				tp = new BetaCumulate();
				break;
			case "runningAverage" :
				tp = new BetaRunningAverage();
				break;
			case "vectorMoment" :
				tp = new BetaRunningMoments(1); //falta passar argumento...
				break;
			case "distinctOccurrences" :
				tp = new BetaDistinctOccurences(); // Falta terminar
				break;
			case "valueDistribution" :
				tp = new BetaValueDistribution();
				break;			
			default :
				break;
		}
		return tp;
	}


}
