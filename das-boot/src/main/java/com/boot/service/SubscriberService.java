package com.boot.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.boot.dto.subscriber.AddBalanceForm;
import com.boot.dto.subscriber.AddSubscriberForm;
import com.boot.dto.subscriber.SubscriberDetailView;
import com.boot.dto.subscriber.SubscriberListGroupedView;
import com.boot.dto.subscriber.SubscriberListView;
import com.boot.dto.subscriber.SubscribersOfPersonView;
import com.boot.dto.subscription.SubscriptionOfSubscriber;
import com.boot.exceptions.AlreadyExistException;
import com.boot.model.Person;
import com.boot.model.Subscriber;
import com.boot.model.Subscription;
import com.boot.repository.PersonRepository;
import com.boot.repository.SubscriberRepository;

import javassist.NotFoundException;

@Service
public class SubscriberService {
	
	@Autowired
	private SubscriberRepository subscriberRepository;
	
	@Autowired
	private PersonRepository personRepository;
	
	/**
	 * This function takes all subscribers from SubscriberRepository.
	 * @return List of SubscriberDto which contains necessary fields for showing to user.
	 */
	public List<SubscriberListView> listSubscribersOld(){
		List<Subscriber> subscribers = subscriberRepository.findAll();
		List<SubscriberListView> subscriberDtos = new ArrayList<SubscriberListView>();
		for(Subscriber s : subscribers) {
			SubscriberListView subscriber = new SubscriberListView();
			BeanUtils.copyProperties(s, subscriber);
			BeanUtils.copyProperties(s.getPerson(), subscriber);
			subscriberDtos.add(subscriber);
		}
		return subscriberDtos;
	}
	
	public List<SubscriberListGroupedView> listSubscribers(){
		List<Person> persons = personRepository.findAll();
		List<SubscriberListGroupedView> subscribers = new ArrayList<SubscriberListGroupedView>();
		for(Person p: persons) {
			if(p.getSubscribers().size() == 0) { continue; }
			List<SubscribersOfPersonView> subscribersView = new ArrayList<SubscribersOfPersonView>();
			for(Subscriber s: p.getSubscribers()) {
				SubscribersOfPersonView subscriber = new SubscribersOfPersonView();
				BeanUtils.copyProperties(s, subscriber);
				subscribersView.add(subscriber);
			}
			SubscriberListGroupedView subscriberView = new SubscriberListGroupedView();
			BeanUtils.copyProperties(p, subscriberView, "subscribers"); subscriberView.setSubscribers(subscribersView);
			subscribers.add(subscriberView);
		}
		return subscribers;
	}
	
	/**
	 * This function lists subscriptions of specific subscriber with some subscriber info.
	 * @param subscriberid
	 * @return Returns SubscriberDto which contains some subscriber info and its subscriptions.
	 * @throws NotFoundException 
	 */
	public SubscriberDetailView getSubscriptions(Integer subscriberid) throws NotFoundException{
		Subscriber subscriber = subscriberRepository.findSubscriberBySubscriberId(subscriberid);
		if (subscriber == null) { throw new NotFoundException("The subscriber with id " + subscriberid + " not found!");}
		List<SubscriptionOfSubscriber> subscriptions = new ArrayList<SubscriptionOfSubscriber>();
		for(Subscription s: subscriber.getSubscriptions()) {   //Loop is for mapping Subscription to SubscriberDto
			SubscriptionOfSubscriber subscription = new SubscriptionOfSubscriber(); 
			BeanUtils.copyProperties(s, subscription);
			BeanUtils.copyProperties(s, subscription);
			subscription.setOfferName(s.getOffer().getName());
			subscriptions.add(subscription);
		}
		SubscriberDetailView subscriberWithSubscriptions = new SubscriberDetailView();
		BeanUtils.copyProperties(subscriber, subscriberWithSubscriptions, "subscriptions");
		BeanUtils.copyProperties(subscriber.getPerson(), subscriberWithSubscriptions, "subscriptions");
		subscriberWithSubscriptions.setSubscriptions(subscriptions);
		return subscriberWithSubscriptions;
	}
	
	/**
	 * This function add new subscriber to the system.
	 * @param person_id Id which is given from query parameter.
	 * @param sDto 
	 * @return
	 */
	public Integer addSubscriber(AddSubscriberForm subscriberForm) {
		if(subscriberRepository.findSubscriberByMsisdnNo(subscriberForm.getMsisdnNo()) != null) {
			throw new AlreadyExistException("The subscriber with phone number " + subscriberForm.getMsisdnNo() + 
											" is already exist in the database!");
		}
		Subscriber newSubscriber = new Subscriber();
		BeanUtils.copyProperties(subscriberForm, newSubscriber);
		newSubscriber.setPerson(personRepository.getOne(subscriberForm.getPersonId()));
		Subscriber saved = subscriberRepository.save(newSubscriber);
		return saved.getSubscriberId();
	}
	
	/**
	 * This function increments of subscriber' balance and update balance information with the updated balance value.
	 * @param balanceDto Parameter that carries msisdn no and balance information which is wanted to be added.
	 * @return Updated subscriber object.
	 * @throws NotFoundException 
	 */
	public Integer addBalance(AddBalanceForm addBalanceForm) throws NotFoundException {
		Subscriber s = subscriberRepository.findSubscriberByMsisdnNo(addBalanceForm.getMsisdnNo());
		if(s == null) {
			throw new NotFoundException("There is no subscriber with phone number " + addBalanceForm.getMsisdnNo() + "!");
		}
		s.setBalance( s.getBalance() + addBalanceForm.getIncBalance() );
		Subscriber saved = subscriberRepository.save(s);
		return saved.getSubscriberId();
	}
	

}
