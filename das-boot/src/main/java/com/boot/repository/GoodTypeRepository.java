package com.boot.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.boot.model.GoodType;



@Repository
public interface GoodTypeRepository extends JpaRepository<GoodType,Integer>{

}
