package com.quiz.model;

public class StateModel {
	private String stateCode;
	private String stateName;
	public String getStateCode() {
		return stateCode;
	}
	public void setStateCode(String stateCode) {
		this.stateCode = stateCode;
	}
	public String getStateName() {
		return stateName;
	}
	public void setStateName(String stateName) {
		this.stateName = stateName;
	}
	@Override
	public String toString() {
		return "StateModel [stateCode=" + stateCode + ", stateName=" + stateName + "]";
	}
	
	
}
