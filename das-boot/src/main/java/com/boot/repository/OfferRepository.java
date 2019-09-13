package com.boot.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.boot.model.Offer;


@Repository
public interface OfferRepository extends JpaRepository<Offer,Integer>{
	public Offer findOfferByOfferId(Integer offerId);
}