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
		if (classifier != null) {
			this.classifier = classifier;
		}
	}
	
	/**
	 * Returns the hash code for these parameters.
	 */
	public int hashCode() {
		return classifier.hashCode();
	}

}
