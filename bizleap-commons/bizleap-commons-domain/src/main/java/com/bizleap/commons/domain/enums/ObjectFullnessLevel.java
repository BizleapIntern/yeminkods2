package com.bizleap.commons.domain.enums;

public enum ObjectFullnessLevel {

	FULL("Full"), DETAIL("Detail"), SUMMARY("Summary");

	private String value;

	private ObjectFullnessLevel(String value) {
		this.value = value;
	}

	public String getValue() {
		return this.value;
	}

}
