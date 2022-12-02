package it.polito.tdp.borders.model;

public class Border {

	
	private int state1no;
	private int state2no;
	private int dyad;
	private String state1ab;
	private String state2ab;
	private int year;
	private int conttype;
	private double version;
	
	public Border(String state1ab, String state2ab) {
		super();
		this.state1ab = state1ab;
		this.state2ab = state2ab;
	}

	public int getState1no() {
		return state1no;
	}

	public int getState2no() {
		return state2no;
	}

	public String getState1ab() {
		return state1ab;
	}

	public String getState2ab() {
		return state2ab;
	}

	public int getYear() {
		return year;
	}

	public int getConttype() {
		return conttype;
	}


	
}
