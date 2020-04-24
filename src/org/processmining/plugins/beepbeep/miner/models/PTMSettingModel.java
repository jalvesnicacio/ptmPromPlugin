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
package src.org.processmining.plugins.beepbeep.miner.models;

import src.org.processmining.plugins.beepbeep.miner.processors.BetaCumulate;
import src.org.processmining.plugins.beepbeep.miner.processors.BetaDistinctOccurences;
import src.org.processmining.plugins.beepbeep.miner.processors.BetaRunningAverage;
import src.org.processmining.plugins.beepbeep.miner.processors.BetaRunningMoments;
import src.org.processmining.plugins.beepbeep.miner.processors.BetaValueDistribution;
import src.org.processmining.plugins.beepbeep.miner.processors.TrendProcessor;

import ca.uqac.lif.cep.functions.BinaryFunction;
import ca.uqac.lif.cep.functions.Function;
import ca.uqac.lif.cep.util.Numbers;

public class PTMSettingModel {
	
	private BeepbeepBPMModel bpmModel;
	private ReferenceTrend trendReference;
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
	public ReferenceTrend getTrendReference() {
		return this.trendReference;
	}
	
	public void setTrendReference(ReferenceTrend trend) {
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
	
	public Function getDistanceFunction() {
		Function distanceFunction = null;
		switch (this.distance) {
			case "manhattanDistance" :
				distanceFunction = null;
				break;
			case "euclidianDistance" :
				distanceFunction = null;
				break;
			case "scalarDifference" :
				distanceFunction = Numbers.subtraction;
				break;
			case "ratio" :
			default :
				break;
		}
		return distanceFunction;
	}
	


}
