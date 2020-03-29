package org.processmining.plugins.beepbeep.miner;

import org.deckfour.xes.classification.XEventAndClassifier;
import org.deckfour.xes.classification.XEventClassifier;
import org.deckfour.xes.classification.XEventLifeTransClassifier;
import org.deckfour.xes.classification.XEventNameClassifier;


/**
 * This class creates a XEventClassifier. 
 * XEventClassifier can be used to impose a classification of events. 
 * Two events which belong to the same event class can be considered equal, 
 * i.e. to refer to the same higher-level concept they represent (e.g., an activity). 
 * Event classes are imposed on a log by a specific classifier. 
 * This class can be configured with such a classifier, which is then used to derive the actual 
 * event classes from a log, by determining the identity of the contained events.
 * @author jalves Nicacio
 *
 */
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
	public void setClassifier(XEventClassifier classifier) {
		if (this.classifier != null) {
			this.classifier = classifier;
		}
	}
	
	/**
	 * Returns whether these parameter values (classifier) are equal to the given parameter
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
			if (this.classifier.equals(parameters.getClassifier())) {
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
