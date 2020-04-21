package org.processmining.plugins.beepbeep.miner.models;

import org.deckfour.xes.model.XAttribute;

public class Attribute {
	
	private String key;
	private String value;
	private Object className;
	
	
	public String getKey() {
		return key;
	}

	public void setKey(String key) {
		this.key = key;
	}

	public String getValue() {
		return value;
	}

	public void setValue(String value) {
		this.value = value;
	}

	
	public Attribute(XAttribute xatt) {
		this.key = xatt.getKey();
		this.value = xatt.toString();
		this.className = xatt.getClass();
	}

	public String toString() {
		return "\tAttribute [key: " + this.key + "\t | value: " + this.value + "]\n";
	}

	public Object getClassName() {
		return className;
	}

	public void setClassName(Object className) {
		this.className = className;
	}
	
	
	
	
	
	
	
	

}
