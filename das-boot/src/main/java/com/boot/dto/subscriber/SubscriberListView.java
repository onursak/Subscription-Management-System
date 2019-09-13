package com.boot.dto.subscriber;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class SubscriberListView {
	private Integer subscriberId;
	private String msisdnNo;
	private String name;     //person's name
	private String surname;   //person's surname
	private Double balance;
	
}
