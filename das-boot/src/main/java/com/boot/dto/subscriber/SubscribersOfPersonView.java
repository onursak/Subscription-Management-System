package com.boot.dto.subscriber;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class SubscribersOfPersonView {
	private Integer subscriberId;
	private String msisdnNo;
	private Double balance;
}
