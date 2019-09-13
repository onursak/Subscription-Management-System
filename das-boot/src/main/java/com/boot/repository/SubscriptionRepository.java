package com.boot.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.boot.model.Subscription;


@Repository
public interface SubscriptionRepository extends JpaRepository<Subscription,Integer>{
	public Subscription findSubscriptionBySubscriptionId(Integer subscriptionId);
}
