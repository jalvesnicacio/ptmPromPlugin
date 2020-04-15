package org.processmining.plugins.beepbeep.miner.models;
/**
 * Class TrendReference
 * @author jalves
 *
 */
public class TrendReference{
		public enum DataMiningPattern {
			SELF_CORRELATED,
			PATTERN_BASED
		}
		private DataMiningPattern type = DataMiningPattern.SELF_CORRELATED;
		private Number value;
		private String trendAttributeName, trendChoice;
		
		public TrendReference(String type, Number value) {
			if (type == "SELF_CORRELATED") {
				this.type = DataMiningPattern.SELF_CORRELATED;
			}else {
				this.type = DataMiningPattern.PATTERN_BASED;
			}
			this.value = value;
		}
		

		public String getAttributeName() {
			return trendAttributeName;
		}


		public void setAttributeName(String attributeName) {
			this.trendAttributeName = attributeName;
		}
		
		public String getTrendChoice() {
			return trendChoice;
		}

		public void setTrendChoice(String trendChoice) {
			this.trendChoice = trendChoice;
		}


		public DataMiningPattern getDataMiningPattern() {
			return type;
		}

		public void setDataMiningPattern(DataMiningPattern dataMiningPattern) {
			this.type = dataMiningPattern;
		}

		public Number getDataMiningPatternValue() {
			return value;
		}
		
	}