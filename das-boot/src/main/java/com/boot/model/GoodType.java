package com.boot.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;

@Entity
@Table(name="goodtype")
public class GoodType {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "goodtype_id")
	private Integer goodTypeId;
	@Column(length=20)
	@NotNull
	private String name;
	
	//default constructor is mandatory when you want to create a java bean
	public GoodType() {}


	public Integer getGoodTypeId() {
		return goodTypeId;
	}

	public void setGoodTypeId(Integer goodTypeId) {
		this.goodTypeId = goodTypeId;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}
	
	
	
}
