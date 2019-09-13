package com.boot.model;

import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.validation.constraints.NotNull;

import com.fasterxml.jackson.annotation.JsonIgnore;

@Entity
public class Subscriber {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer subscriberId;
	
	//Lazy fetch type used for fetching when only need to fetch person data.
	@ManyToOne(fetch=FetchType.LAZY)
	@JoinColumn(name="person_id")
	@JsonIgnore
	private Person person;
	
	@Column(length=10,unique=true)
	@NotNull
	private String msisdnNo;
	
	@Column(length=15,unique=true)
	@NotNull
	@JsonIgnore
	private String imsi;
	
	private Double balance;
	
	//Bidirectional relationship between subscriber and subscriptions
	@OneToMany(mappedBy="subscriber")
	@JsonIgnore
	private List<Subscription> subscriptions;
	
	public Subscriber() {}
	
	public Subscriber(String msisdnNo, String imsi, Double balance) {
		this.msisdnNo = msisdnNo;
		this.imsi = imsi;
		this.balance = balance;
	}

	public Integer getSubscriberId() {
		return subscriberId;
	}

	public void setSubscriberId(Integer subscriberId) {
		this.subscriberId = subscriberId;
	}

	public Person getPerson() {
		return person;
	}

	public void setPerson(Person person) {
		this.person = person;
	}

	public String getMsisdnNo() {
		return msisdnNo;
	}

	public void setMsisdnNo(String msisdnNo) {
		this.msisdnNo = msisdnNo;
	}

	public String getImsi() {
		return imsi;
	}

	public void setImsi(String imsi) {
		this.imsi = imsi;
	}

	public Double getBalance() {
		return balance;
	}

	public void setBalance(Double balance) {
		this.balance = balance;
	}

	public List<Subscription> getSubscriptions() {
		return subscriptions;
	}

	public void setSubscriptions(List<Subscription> subscriptions) {
		this.subscriptions = subscriptions;
	}
	
	
	
	
}
