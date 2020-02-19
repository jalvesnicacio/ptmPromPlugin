package org.processmining.plugins.beepbeep.miner;

import org.deckfour.xes.classification.XEventAndClassifier;
import org.deckfour.xes.classification.XEventClassifier;
import org.deckfour.xes.classification.XEventLifeTransClassifier;
import org.deckfour.xes.classification.XEventNameClassifier;

public class BeepbeepMiningParameters {
	
	/**
	 * Classifier parameter. This determines which classifier will be used
	 * during the mining.
	 */
	private XEventClassifier classifier;
	
	/**
	 * Create default parameter values.
	 */
	public BeepbeepMiningParameters() {
		classifier = new XEventAndClassifier(new XEventNameClassifier(), new XEventLifeTransClassifier());
	}

	
	/**
	 * Gets the classifier.
	 * 
	 * @return The classifier.
	 */
	public XEventClassifier getClassifier() {
		return this.classifier;
	}
	
	
	/**
	 * Set the classifier to the given classifier.
	 * 
	 * @param classifier
	 *            The given classifier.
	 */
	public void setClassifier(XEventClassifier selectedValue) {
		if (this.classifier != null) {
			this.classifier = classifier;
		}
	}
	
	/**
	 * Returns whether these parameter values are equal to the given parameter
	 * values.
	 * 
	 * @param object
	 *            The given parameter values.
	 * @return Whether these parameter values are equal to the given parameter
	 *         values.
	 */
	public boolean equals(Object object) {
		if (object instanceof BeepbeepMiningParameters) {
			BeepbeepMiningParameters parameters = (BeepbeepMiningParameters) object;
			if (this.classifier.equals(parameters.classifier)) {
				return true;
			}
		}
		return false;
	}
	
	/**
	 * Returns the hash code for these parameters.
	 */
	public int hashCode() {
		return classifier.hashCode();
	}

}
