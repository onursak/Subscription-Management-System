package com.boot.service;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.boot.dto.good.GoodListView;
import com.boot.dto.offer.AddOfferForm;
import com.boot.dto.offer.OfferDetailView;
import com.boot.dto.offer.UpdateOfferForm;
import com.boot.model.Good;
import com.boot.model.Offer;
import com.boot.repository.GoodRepository;
import com.boot.repository.OfferRepository;

@Service
public class OfferService {
	@Autowired
	private OfferRepository offerRepository;
	
	@Autowired
	private GoodRepository goodRepository;
	
	/**
	 * This function simply returns list of all offers in the system.
	 * Mapping between OfferDto and Offer objects.
	 * */
	public List<OfferDetailView> listOffers(){
		List<Offer> offers = offerRepository.findAll();
		List<OfferDetailView> offerDetails = new ArrayList<OfferDetailView>();
		for(Offer offer: offers) {
			OfferDetailView offerDetail = getOffer(offer.getOfferId());
			offerDetails.add(offerDetail);
		}
		return offerDetails;
	}
	
	/**
	 * This function returns only one offer object from database according to given id.
	 * @param Id value of which is wanted to be searched.
	 * @return OfferDto object which contains necessary information for JSON format.
	 * */
	public OfferDetailView getOffer(Integer offerid) {
		Offer offer = offerRepository.findOfferByOfferId(offerid);
		OfferDetailView offerDetail = new OfferDetailView();
		BeanUtils.copyProperties(offer, offerDetail);
		Set<GoodListView> goods = new HashSet<GoodListView>();
		for(Good g: offer.getGoods()) {
			GoodListView goodView = new GoodListView();
			BeanUtils.copyProperties(g, goodView);
			goodView.setGoodTypeId(g.getGoodType().getGoodTypeId());
			goods.add(goodView);
		}
		offerDetail.setGoods(goods);
		return offerDetail;
	}
	
	/**
	 * This function adds new offer to the system.
	 */
	public String addOffer(AddOfferForm offerInfo) {
		Set<Good> goods = new HashSet<Good>();
		for(Integer i:offerInfo.getGoodIds()) {
			goods.add(goodRepository.getOne(i));
		}
		Offer newOffer = new Offer();
		BeanUtils.copyProperties(offerInfo, newOffer, "goods");
		newOffer.setGoods(goods);
		Offer saved = offerRepository.save(newOffer);
		return saved.getName();
	}
	
	/**
	 * This function updates existing offer info.
	 */
	public Integer updateOffer(UpdateOfferForm offerInfo) {
		Set<Good> goods = new HashSet<Good>();
		for(Integer i:offerInfo.getGoodIds()) {
			goods.add(goodRepository.getOne(i));
		}
		Offer offer = new Offer();
		BeanUtils.copyProperties(offerInfo, offer, "goods");
		offer.setGoods(goods);
		Offer updated = offerRepository.save(offer);
		return updated.getOfferId();
	}
	
	
}
