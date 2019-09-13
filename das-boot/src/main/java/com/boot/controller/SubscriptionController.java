package com.boot.controller;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.boot.dto.response.ResponseDto;
import com.boot.dto.subscription.AddSubscriptionForm;
import com.boot.exceptions.NotEnoughMoneyException;
import com.boot.service.SubscriptionService;

@CrossOrigin(origins = "http://localhost:4200")
@RestController
public class SubscriptionController {
	
	@Autowired
	private SubscriptionService subscriptionService;
	
	@RequestMapping(value="/subscription", method=RequestMethod.POST)
	public ResponseEntity<ResponseDto> addSubscription(@Valid @RequestBody AddSubscriptionForm subscriptionForm) throws NotEnoughMoneyException {
		String phoneNumber = subscriptionService.addSubscription(subscriptionForm);
		return new ResponseEntity<ResponseDto>(new ResponseDto
				("OK", 201, "Subscription is added to the subscriber has phone number " + phoneNumber + "."), HttpStatus.CREATED);
	}
	
}