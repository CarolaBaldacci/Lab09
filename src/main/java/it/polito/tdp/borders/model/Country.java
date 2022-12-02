package it.polito.tdp.borders.model;

public class Country implements Comparable<Country> {

	private int cCode;
	private String stateAbb;
	private String stateName;
	
	public Country(int cCode, String stateAbb, String stateName) {
		super();
		this.cCode = cCode;
		this.stateAbb = stateAbb;
		this.stateName = stateName;
	}
	public int getcCode() {
		return cCode;
	}
	public String getStateAbb() {
		return stateAbb;
	}
	public String getStateName() {
		return stateName;
	}
	@Override
	public String toString() {
		return "Country=" + stateName +"\n";
	}
	@Override
	public int compareTo(Country o) {
		return this.cCode-o.cCode;
	}
	
	
}
