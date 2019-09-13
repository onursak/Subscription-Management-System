package com.boot.service;



import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.boot.dto.subscription.AddSubscriptionForm;
import com.boot.exceptions.NotEnoughMoneyException;
import com.boot.model.Offer;
import com.boot.model.Subscriber;
import com.boot.model.Subscription;
import com.boot.repository.OfferRepository;
import com.boot.repository.SubscriberRepository;
import com.boot.repository.SubscriptionRepository;

@Service
public class SubscriptionService {
	@Autowired
	private SubscriptionRepository subscriptionRepository;
	
	@Autowired
	private OfferRepository offerRepository;
	
	@Autowired
	private SubscriberRepository subscriberRepository;
	/*
	//to do: look at this function later, it might be unnecessary and can be deleted.
	public List<SubscriptionDto> listSubscriptions() {
		List<Subscription> subscriptions = subscriptionRepository.findAll();		
		List<SubscriptionDto> subscriptionDtos = new ArrayList<SubscriptionDto>();
		for(Subscription s: subscriptions) {
			subscriptionDtos.add(new SubscriptionDto(s.getSubscriptionId(), s.getStartDate(),
											s.getEndDate(), s.getStatus(), 
											s.getCost(), new OfferDto(s.getOffer().getName())));
		}
		return subscriptionDtos;
	}
	/*
	/**
	 * This function adds new subscription to the specific subscriber.
	 * @throws NotEnoughMoneyException 
	 */
	public String addSubscription(AddSubscriptionForm subscriptionForm) throws NotEnoughMoneyException {
		Subscription s = new Subscription();
		BeanUtils.copyProperties(subscriptionForm, s);
		Subscriber subscriber = subscriberRepository.getOne(subscriptionForm.getSubscriberId());
		Offer offer = offerRepository.getOne(subscriptionForm.getOfferId());
		Double newPrice = offer.getPrice() - (offer.getPrice() * subscriptionForm.getDiscountAmount() / 100);
		Double newBalance = performPayment(subscriber.getBalance(), newPrice);
		s.setCost(newPrice);
		subscriber.setBalance(newBalance);
		s.setSubscriber(subscriber);
		s.setOffer(offerRepository.getOne(subscriptionForm.getOfferId()));
		Subscription saved = subscriptionRepository.save(s);
		return saved.getSubscriber().getMsisdnNo();
	}
	
	private Double performPayment(Double balance, Double newPrice) throws NotEnoughMoneyException {
		Double newBalance = balance - newPrice;
		if(newBalance > 0) {
			return newBalance;
		}
		else {
			throw new NotEnoughMoneyException();
		}
	}
}
