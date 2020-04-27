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
package org.processmining.plugins.beepbeep.miner.models;

import org.processmining.plugins.beepbeep.miner.processors.BetaCumulate;
import org.processmining.plugins.beepbeep.miner.processors.BetaDistinctOccurences;
import org.processmining.plugins.beepbeep.miner.processors.BetaRunningAverage;
import org.processmining.plugins.beepbeep.miner.processors.BetaRunningMoments;
import org.processmining.plugins.beepbeep.miner.processors.BetaValueDistribution;
import org.processmining.plugins.beepbeep.miner.processors.TrendProcessor;
import org.processmining.plugins.beepbeep.models.BeepBeepLogModel;

import ca.uqac.lif.cep.functions.BinaryFunction;
import ca.uqac.lif.cep.functions.Function;
import ca.uqac.lif.cep.util.Numbers;

public class PTMSettingModel {
	
	private BeepBeepLogModel m_logModel;
	private ReferenceTrend m_trendReference;
	private int m_pastWindow, m_presentWindow;
	private String m_distance, m_thresholdOption;
	float thresholdValue;
	
	
	public String toString() {
		//return super.toString();
		String s = "PTM Model: [dataMiningPattern = "+this.m_trendReference.getDataMiningPattern()+" | ";
		s = s + "pastWindow = " + this.getPastWindow() + " presentWindow = " + this.getPresentWindow();
		s = s + "]\n";
		return s;
	}
	
	//bpmModel:
	public void setLogModel(BeepBeepLogModel logModel) {
		this.m_logModel = logModel;
	}
	
	public BeepBeepLogModel getLogModel(){
		return this.m_logModel;
	}
	
	//Trend Reference:
	public ReferenceTrend getTrendReference() {
		return this.m_trendReference;
	}
	
	public void setTrendReference(ReferenceTrend trend) {
		this.m_trendReference = trend;
	}
	
	//Past and Present Windows
	public void setPastWindow(int pastwindow) {
		this.m_pastWindow = pastwindow;
	}

	public void setPresentWindow(int presentWindow) {
		this.m_presentWindow = presentWindow;
	}
	
	public int getPastWindow() {
		return this.m_pastWindow;
	}
	public int getPresentWindow() {
		return this.m_presentWindow;
	}
	
	
	//Trend Option
	public void setElementTrendOption(String elementTrendOption) {
		this.m_trendReference.setAttributeName(elementTrendOption); 	
	}

	public void setTrendChoice(String trendChoice) {
		this.m_trendReference.setTrendChoice(trendChoice); 
	}
	
	public String getElementTrendOption() {
		return this.m_trendReference.getAttributeName();
	}
	public String getTrendChoice() {
		return this.m_trendReference.getTrendChoice();
	}
	
	
	
	// Distance function
	public void setDistance(String distance) {
		this.m_distance = distance;
	}
	public String getDistance() {
		return this.m_distance;
	}

	//Threshold
	public void setThresholdOption(String thresholdOption) {
		this.m_thresholdOption = thresholdOption;
	}
	public void setThresholdValue(float thresholdValue) {
		this.thresholdValue = thresholdValue;		
	}
	public String getThresholdOption() {
		return this.m_thresholdOption;
	}
	public float getThresholdValue() {
		return this.thresholdValue;
	}
	public BinaryFunction<Number, Number, Boolean> getThresholdFunction() {
		//Numbers.isGreaterOrEqual 	
		if (this.m_thresholdOption == "Smaller than") {
			return Numbers.isLessThan;
		}else {
			return Numbers.isGreaterOrEqual;
		}
	}
	
	
	
	public TrendProcessor getTrendProcessor() {
		TrendProcessor tp = null;
		switch (this.m_trendReference.getTrendChoice()) {
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
		switch (this.m_distance) {
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
