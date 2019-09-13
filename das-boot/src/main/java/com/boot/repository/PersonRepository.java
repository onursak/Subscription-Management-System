package com.boot.repository;


import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.boot.model.Person;

@Repository
public interface PersonRepository extends JpaRepository<Person,String>{
	public Person findPersonByPersonId(String personId);
	public List<Person> findAllByOrderByName();  //This is to list all subscribers according to their name orders
}
