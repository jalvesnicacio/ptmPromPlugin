package org.processmining.plugins.beepbeep.miner.models;

import ca.uqac.lif.cep.Processor;
import ca.uqac.lif.cep.functions.BinaryFunction;
import ca.uqac.lif.cep.functions.Cumulate;
import ca.uqac.lif.cep.functions.CumulativeFunction;

public class PTMSettingModel {
	
	private BeepbeepBPMModel bpmModel;
	private TrendReference trendReference;
	private int pastWindow, presentWindow;
	private String elementTrendOption, trendChoice;
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
		this.elementTrendOption = elementTrendOption;	
	}

	public void setTrendChoice(String trendChoice) {
		this.trendChoice = 	trendChoice;	
	}
	
	public String getElementTrendOption() {
		return this.elementTrendOption;
	}
	public String getTrendChoice() {
		return this.trendChoice;
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
	
	
	
	public Processor executeTrendOption() {
		switch (this.trendChoice) {
			case "cumulativeSum" :
				BinaryFunction<Number, Number, Number> bf = new BinaryFunction<Number, Number, Number>(Number.class, Number.class, Number.class) {

					public Number getValue(Number arg0, Number arg1) {
						// TODO Auto-generated method stub
						return (int)arg0 + (int)arg1;
					}
				};
				CumulativeFunction<Number> cf = new CumulativeFunction<Number>(bf);
				Cumulate cumulativeSum = new Cumulate(cf);
				return cumulativeSum;
			default :
				break;
		}
		
		return null;
	}

	
	
	
	/**
	 * Class TrendReference
	 * @author jalves
	 *
	 */
	public static class TrendReference{
		public enum DataMiningPattern {
			SELF_CORRELATED,
			PATTERN_BASED
		}
		private DataMiningPattern dataMiningPattern = DataMiningPattern.SELF_CORRELATED;
		private Number dataMiningPatternValue;
		
		public TrendReference(String type, Number value) {
			if (type == "SELF_CORRELATED") {
				this.dataMiningPattern = DataMiningPattern.SELF_CORRELATED;
			}else {
				this.dataMiningPattern = DataMiningPattern.PATTERN_BASED;
			}
			this.dataMiningPatternValue = value;
		}

		public DataMiningPattern getDataMiningPattern() {
			return dataMiningPattern;
		}

		public void setDataMiningPattern(DataMiningPattern dataMiningPattern) {
			this.dataMiningPattern = dataMiningPattern;
		}

		public Number getDataMiningPatternValue() {
			return dataMiningPatternValue;
		}
		
	}

}
