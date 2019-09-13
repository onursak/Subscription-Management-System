package com.boot.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.boot.dto.person.AddPersonForm;
import com.boot.dto.person.PersonDetailView;
import com.boot.dto.person.PersonListView;
import com.boot.dto.subscriber.SubscribersOfPersonView;
import com.boot.exceptions.AlreadyExistException;
import com.boot.model.Person;
import com.boot.model.Subscriber;
import com.boot.repository.PersonRepository;

import javassist.NotFoundException;


@Service
public class PersonService {
	
	@Autowired
	private PersonRepository personRepository;
	
	/**
	 * This function list all people in the system.
	 */
	public List<PersonListView> listPeople(){
		List<Person> personList = personRepository.findAllByOrderByName();
		List<PersonListView> persons = new ArrayList<PersonListView>();
		for(Person person : personList) {
			PersonListView personView = new PersonListView();
			BeanUtils.copyProperties(person, personView);
			persons.add(personView);
		}
		return persons;
	}
	
	/**
	 * This function gets specific person and send this info to the controller as PersonDto.
	 * @throws NotFoundException 
	 */
	public PersonDetailView getPerson(String personid) throws NotFoundException{
		Person person = personRepository.findPersonByPersonId(personid);
		if(person == null) {
			throw new NotFoundException("Person with id: " + personid + " not found!");
		} 
		List<SubscribersOfPersonView> subscribers = new ArrayList<SubscribersOfPersonView>();
		for(Subscriber s: person.getSubscribers()) {
			SubscribersOfPersonView subscriberView = new SubscribersOfPersonView();
			BeanUtils.copyProperties(s, subscriberView);
			subscribers.add(subscriberView);
		}
		PersonDetailView personDetail = new PersonDetailView();
		BeanUtils.copyProperties(person, personDetail, "subscribers");
		personDetail.setSubscribers(subscribers);
		return personDetail;
	}
	
	public String createPerson(AddPersonForm personForm){
		if(personRepository.existsById(personForm.getPersonId())) {
			throw new AlreadyExistException("The person with id " + personForm.getPersonId() + " already exist in the database!");
		}
		Person newPerson = new Person();
		BeanUtils.copyProperties(personForm, newPerson);
		Person saved = personRepository.save(newPerson);
		return saved.getPersonId();
	}
	
	public String updatePerson(AddPersonForm personForm) throws NotFoundException{
		Person updatedPerson = new Person();
		if(personRepository.existsById(personForm.getPersonId()) == false) { 
			throw new NotFoundException("There is no person with id " + personForm.getPersonId()); 
		}
		BeanUtils.copyProperties(personForm, updatedPerson);
		Person updated = personRepository.save(updatedPerson);
		return updated.getPersonId();
	}
	
	public Boolean askPerson(String personId) throws NotFoundException {
		Boolean result = personRepository.existsById(personId);
		if(result == true) {
			return result;
		}
		else {
			throw new NotFoundException("Person with id: " + personId + " not found!");
		}
	}
	
	
}
