package com.boot.dto.subscriber;

import java.util.List;

import com.boot.dto.subscription.SubscriptionOfSubscriber;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class SubscriberDetailView extends SubscriberListView{
	
	private List<SubscriptionOfSubscriber> subscriptions;
	
}
