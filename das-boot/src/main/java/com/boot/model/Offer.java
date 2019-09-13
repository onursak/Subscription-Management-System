package com.boot.model;

import java.time.LocalDateTime;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.validation.constraints.NotNull;

import org.springframework.format.annotation.DateTimeFormat;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.fasterxml.jackson.datatype.jsr310.deser.LocalDateTimeDeserializer;
import com.fasterxml.jackson.datatype.jsr310.ser.LocalDateTimeSerializer;

@Entity
public class Offer {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer offerId;

	@Column(length=20)
	private String name;
	@NotNull
	@JsonSerialize(using = LocalDateTimeSerializer.class)
    @JsonDeserialize(using = LocalDateTimeDeserializer.class)
    @DateTimeFormat(pattern = "dd-MM-yyyy HH:mm", iso = DateTimeFormat.ISO.DATE_TIME)
	private LocalDateTime startDate;
	
	@NotNull
	@JsonSerialize(using = LocalDateTimeSerializer.class)
    @JsonDeserialize(using = LocalDateTimeDeserializer.class)
    @DateTimeFormat(pattern = "dd-MM-yyyy HH:mm", iso = DateTimeFormat.ISO.DATE_TIME)
	private LocalDateTime endDate;
	
	@NotNull
	private Double price;
	@NotNull
	private Integer subscriptionPeriod;
	
	@NotNull
	private Integer chargingPeriod;
	@NotNull
	private Boolean recurring;
	@NotNull
	private Boolean secondSubscription;
	private String description;
	
	@ManyToMany(fetch = FetchType.LAZY,
            cascade = {
                CascadeType.PERSIST,
                CascadeType.MERGE
            })
	/* There is no offer_good entity in the java classes because Spring Data JPA will 
	automatically create this table in the database thanks to below annotations. */
    @JoinTable(name = "offer_good",
            joinColumns = {@JoinColumn(name = "offer_id") },
            inverseJoinColumns = { @JoinColumn(name = "good_id") })
	@JsonIgnore
	//Set data structure recommended for many to many relationships
	private Set<Good> goods;
	
	public Offer() {}

	public Offer(String name, LocalDateTime startDate, LocalDateTime endDate, Integer chargingPeriod,
			Boolean recurring, Boolean secondSubscription, Integer subscriptionPeriod,
			Double price, String description, Set<Good> goods) {
		this.name = name;
		this.startDate = startDate;
		this.endDate = endDate;
		this.chargingPeriod = chargingPeriod;
		this.recurring = recurring;
		this.secondSubscription = secondSubscription;
		this.subscriptionPeriod = subscriptionPeriod;
		this.price = price;
		this.description = description;
		this.goods = goods;
	}

	public Integer getOfferId() {
		return offerId;
	}

	public void setOfferId(Integer offerId) {
		this.offerId = offerId;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public LocalDateTime getStartDate() {
		return startDate;
	}

	public void setStartDate(LocalDateTime startDate) {
		this.startDate = startDate;
	}

	public LocalDateTime getEndDate() {
		return endDate;
	}

	public void setEndDate(LocalDateTime endDate) {
		this.endDate = endDate;
	}

	public Double getPrice() {
		return price;
	}

	public void setPrice(Double price) {
		this.price = price;
	}

	public Integer getSubscriptionPeriod() {
		return subscriptionPeriod;
	}

	public void setSubscriptionPeriod(Integer subscriptionPeriod) {
		this.subscriptionPeriod = subscriptionPeriod;
	}

	public Integer getChargingPeriod() {
		return chargingPeriod;
	}

	public void setChargingPeriod(Integer chargingPeriod) {
		this.chargingPeriod = chargingPeriod;
	}

	public Boolean getRecurring() {
		return recurring;
	}

	public void setRecurring(Boolean recurring) {
		this.recurring = recurring;
	}

	public Boolean getSecondSubscription() {
		return secondSubscription;
	}

	public void setSecondSubscription(Boolean secondSubscription) {
		this.secondSubscription = secondSubscription;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public Set<Good> getGoods() {
		return goods;
	}

	public void setGoods(Set<Good> goods) {
		this.goods = goods;
	}


	
	
}
