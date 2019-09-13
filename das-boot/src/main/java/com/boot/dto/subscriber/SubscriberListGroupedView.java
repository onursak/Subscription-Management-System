package com.boot.dto.subscriber;

import java.util.ArrayList;
import java.util.List;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class SubscriberListGroupedView {
	private String name;
	private String surname;
	private List<SubscribersOfPersonView> subscribers = new ArrayList<SubscribersOfPersonView>();
}
