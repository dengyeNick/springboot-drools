package com.neo.drools.model;


public class Address {

	private int postcode;
	
	private String street;
	
	private String state;
	
	private String context;//付款比例
	
	private int controlCable;//控制电缆
	
	private int uncontrolCable;//非控制电缆
	
	private int unAndControlCable;//控制电缆+非控制电缆
	
	
	
	

	public int getUnAndControlCable() {
		return unAndControlCable;
	}

	public void setUnAndControlCable(int unAndControlCable) {
		this.unAndControlCable = unAndControlCable;
	}

	public int getControlCable() {
		return controlCable;
	}

	public void setControlCable(int controlCable) {
		this.controlCable = controlCable;
	}

	public int getUncontrolCable() {
		return uncontrolCable;
	}

	public void setUncontrolCable(int uncontrolCable) {
		this.uncontrolCable = uncontrolCable;
	}

	public String getContext() {
		return context;
	}

	public void setContext(String context) {
		this.context = context;
	}

	public int getPostcode() {
		return postcode;
	}

	public void setPostcode(int postcode) {
		this.postcode = postcode;
	}

	public String getStreet() {
		return street;
	}

	public void setStreet(String street) {
		this.street = street;
	}

	public String getState() {
		return state;
	}

	public void setState(String state) {
		this.state = state;
	}
	
}
