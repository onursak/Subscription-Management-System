package com.boot.model;

import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.validation.constraints.NotNull;

import com.fasterxml.jackson.annotation.JsonIgnore;

@Entity
public class Good {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer goodId;
	
	@ManyToOne(fetch=FetchType.LAZY)
	@JoinColumn(name="goodtype_id")
	@JsonIgnore
	private GoodType goodType;
	
	@ManyToMany(fetch = FetchType.LAZY,
            cascade = {
                CascadeType.PERSIST,
                CascadeType.MERGE
            },
            mappedBy = "goods")
	@JsonIgnore
	private Set<Offer> offers;
	
	@Column(length=20)
	@NotNull
	private String name;
	
	@NotNull
	private Integer amount;
	
	public Good() {}
	
	public Good(String name, Integer amount) {
		this.name = name;
		this.amount = amount;
	}
	
	public Good(Integer goodId, String name, Integer amount) {
		this.goodId = goodId;
		this.name = name;
		this.amount = amount;
	}

	public Integer getGoodId() {
		return goodId;
	}

	public void setGoodId(Integer goodId) {
		this.goodId = goodId;
	}

	public GoodType getGoodType() {
		return goodType;
	}

	public void setGoodType(GoodType goodType) {
		this.goodType = goodType;
	}

	public Set<Offer> getOffers() {
		return offers;
	}

	public void setOffers(Set<Offer> offers) {
		this.offers = offers;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Integer getAmount() {
		return amount;
	}

	public void setAmount(Integer amount) {
		this.amount = amount;
	}

	
	
	
}
