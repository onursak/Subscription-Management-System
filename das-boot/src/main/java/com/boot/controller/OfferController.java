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

import com.boot.dto.offer.AddOfferForm;
import com.boot.dto.offer.OfferDetailView;
import com.boot.dto.offer.UpdateOfferForm;
import com.boot.dto.response.ResponseDto;
import com.boot.service.OfferService;

@CrossOrigin(origins = "http://localhost:4200")
@RestController
public class OfferController{
	@Autowired
	private OfferService offerService;
	
	@RequestMapping(value="/offers",method=RequestMethod.GET)
	public ResponseEntity<List<OfferDetailView>> getOffers(){
		List<OfferDetailView> offers = offerService.listOffers();
		return new ResponseEntity<List<OfferDetailView>>(offers, HttpStatus.OK);
	}
	
	@RequestMapping(value="/offer",method=RequestMethod.GET)
	public ResponseEntity<OfferDetailView> getOffer(@RequestParam("offer_id") Integer offerid){
		OfferDetailView offer = offerService.getOffer(offerid);
		return new ResponseEntity<OfferDetailView>(offer,HttpStatus.OK);
	}
	
	@RequestMapping(value="/offer",method=RequestMethod.POST)
	public ResponseEntity<ResponseDto> addOffer(@Valid @RequestBody AddOfferForm addOfferForm){
		String offerName = offerService.addOffer(addOfferForm);
		return new ResponseEntity<ResponseDto>(new ResponseDto
				("OK", 201, "Offer with name: " + offerName + " is created."),HttpStatus.CREATED);
	}
	
	@RequestMapping(value="/offer",method=RequestMethod.PUT)
	public ResponseEntity<ResponseDto> updateOffer(@Valid @RequestBody UpdateOfferForm updateOfferForm){
		offerService.updateOffer(updateOfferForm);
		return new ResponseEntity<ResponseDto>(new ResponseDto
				("OK", 200, "Offer with id: " + updateOfferForm.getOfferId() + " is updated."),HttpStatus.OK);
	}
	
}
