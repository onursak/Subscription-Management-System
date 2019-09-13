package com.boot.dto.person;

public class AskPerson {
	private Boolean isPerson;
	private String description;
	
	public AskPerson(Boolean isPerson, String description) {
		this.isPerson = isPerson;
		this.description = description;
	}
	public Boolean getIsPerson() {
		return isPerson;
	}
	public void setIsPerson(Boolean isPerson) {
		this.isPerson = isPerson;
	}
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	
	
}
