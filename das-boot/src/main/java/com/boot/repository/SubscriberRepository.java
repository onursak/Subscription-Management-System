package com.boot.repository;


import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.boot.model.Subscriber;


@Repository
public interface SubscriberRepository extends JpaRepository<Subscriber,Integer>{
	public Subscriber findSubscriberBySubscriberId(Integer subscriberId);
	//to do: delete this query and construct the query by using function name!
	//@Query("SELECT s FROM Subscriber s WHERE s.msisdn_no = ?1")
	public Subscriber findSubscriberByMsisdnNo(String msisdnNo);
	public Subscriber findSubscriberByImsi(String imsi);
}