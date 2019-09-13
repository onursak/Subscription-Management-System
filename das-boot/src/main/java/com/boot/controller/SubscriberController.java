package com.boot.controller;

import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.boot.dto.response.ResponseDto;
import com.boot.dto.subscriber.AddBalanceForm;
import com.boot.dto.subscriber.AddSubscriberForm;
import com.boot.dto.subscriber.SubscriberDetailView;
import com.boot.dto.subscriber.SubscriberListGroupedView;
import com.boot.dto.subscriber.SubscriberListView;
import com.boot.service.SubscriberService;

import javassist.NotFoundException;

@CrossOrigin(origins = "http://localhost:4200")
@RestController
public class SubscriberController{
	@Autowired
	private SubscriberService subscriberService;
	
	/**
	 * This function lists all of the subscribers in the system.
	 * @return Subscribers info with necessary fields in JSON format.
	 */
	
	@RequestMapping(value="/subscribers",method=RequestMethod.GET)
	public ResponseEntity<List<SubscriberListGroupedView>> getSubscribers(){
		List<SubscriberListGroupedView> subscribers = subscriberService.listSubscribers();
		return new ResponseEntity<List<SubscriberListGroupedView>>(subscribers, HttpStatus.OK);
	}
	
	@RequestMapping(value="/subscribersOld",method=RequestMethod.GET)
	public ResponseEntity<List<SubscriberListView>> getSubscribersOld(){
		List<SubscriberListView> subscribers = subscriberService.listSubscribersOld();
		return new ResponseEntity<List<SubscriberListView>>(subscribers, HttpStatus.OK);
	}

	/**
	 * This function gets all of the subscriptions which belongs to the specific subscriber.
	 * @param subscriberid id of subscriber wanted to be searched.
	 * @return Subscription info with some Subscriber info in JSON format.
	 * @throws NotFoundException 
	 */
	
	@RequestMapping(value="/subscription",method=RequestMethod.GET)
	public ResponseEntity<SubscriberDetailView> getSubscriptions(@RequestParam("subscriber_id") Integer subscriberid) throws NotFoundException {
		SubscriberDetailView subscriptions = subscriberService.getSubscriptions(subscriberid);
		return new ResponseEntity<SubscriberDetailView>(subscriptions, HttpStatus.OK);
	}
	
	/**
	 * This function gets specific user from database and returns as JSON to the front end.
	 */
	@RequestMapping(value="/subscriber", method=RequestMethod.POST)
	public ResponseEntity<ResponseDto> addSubscriber(@Valid @RequestBody AddSubscriberForm subscriberForm) {
		subscriberService.addSubscriber(subscriberForm);
		return new ResponseEntity<ResponseDto>(new ResponseDto
				("OK", 201, "Subscriber with phone number " + subscriberForm.getMsisdnNo() + " is created."), HttpStatus.CREATED);
	}
	/**
	 * This function increments money balance of specific subscriber.
	 * @param balanceDto
	 * @return
	 * @throws NotFoundException 
	 */
	@RequestMapping(value="/addbalance", method=RequestMethod.PUT)
	public ResponseEntity<ResponseDto> addBalance(@Valid @RequestBody AddBalanceForm addBalanceForm) throws NotFoundException {
		subscriberService.addBalance(addBalanceForm);
		return new ResponseEntity<ResponseDto>(new ResponseDto
				("OK", 200, "Balance with phone number " + addBalanceForm.getMsisdnNo() + " is updated."), HttpStatus.OK);
	}
	
}
