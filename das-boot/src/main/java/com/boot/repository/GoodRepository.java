package com.boot.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.boot.model.Good;



@Repository
public interface GoodRepository extends JpaRepository<Good,Integer>{
	public Good findGoodByGoodId(Integer goodId);
	public Good findGoodByName(String name);
	public Good findGoodByOrderByNameDesc();
}
