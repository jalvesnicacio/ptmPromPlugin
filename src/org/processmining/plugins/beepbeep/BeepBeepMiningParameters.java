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
package org.processmining.plugins.beepbeep;

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
public class BeepBeepMiningParameters {
	
	/**
	 * Classifier parameter. This determines which classifier will be used
	 * during the mining.
	 */
	private XEventClassifier classifier;
	
	/**
	 * Create default parameter values.
	 */
	public BeepBeepMiningParameters() {
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
		if (object instanceof BeepBeepMiningParameters) {
			BeepBeepMiningParameters parameters = (BeepBeepMiningParameters) object;
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
