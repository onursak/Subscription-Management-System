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

import com.boot.dto.person.AddPersonForm;
import com.boot.dto.person.AskPerson;
import com.boot.dto.person.PersonDetailView;
import com.boot.dto.person.PersonListView;
import com.boot.dto.response.ResponseDto;
import com.boot.service.PersonService;

import javassist.NotFoundException;

@CrossOrigin(origins = "http://localhost:4200")
@RestController
public class PersonController {

	@Autowired
	private PersonService personService;

	@RequestMapping(value = "/checkperson", method = RequestMethod.GET)
	public ResponseEntity<AskPerson> askPerson(@RequestParam("person_id") String personId) throws NotFoundException {
		Boolean result = personService.askPerson(personId);
		AskPerson askPerson = new AskPerson(result, "Person with id: " + personId + " was found!");
		return new ResponseEntity<AskPerson>(askPerson,HttpStatus.OK);
	}
	
	/* Getting all the persons without subscriber info. */
	@RequestMapping(value = "/people", method = RequestMethod.GET)
	public ResponseEntity<List<PersonListView>> getPeople() {
		List<PersonListView> persons = personService.listPeople();
		return new ResponseEntity<List<PersonListView>>(persons, HttpStatus.OK);
	}

	@RequestMapping(value = "/person", method = RequestMethod.GET)
	public ResponseEntity<PersonDetailView> getPerson(@RequestParam("person_id") String personid) throws NotFoundException {
		PersonDetailView person = personService.getPerson(personid);
		return new ResponseEntity<PersonDetailView>(person, HttpStatus.OK);
	}

	@RequestMapping(value = "/person", method = RequestMethod.POST)
	public ResponseEntity<ResponseDto> addPerson(@Valid @RequestBody AddPersonForm personForm) {
		String personId = personService.createPerson(personForm);
		return new ResponseEntity<ResponseDto>(new ResponseDto
				("OK", 201, "Person with id " + personId + " is created."), HttpStatus.CREATED);
	}

	@RequestMapping(value = "/person", method = RequestMethod.PUT)
	public ResponseEntity<ResponseDto> updatePerson(@Valid @RequestBody AddPersonForm personForm) throws NotFoundException {
		personService.updatePerson(personForm);
		return new ResponseEntity<ResponseDto>(new ResponseDto
				("OK", 200, "Person with id " + personForm.getPersonId() + " is updated."), HttpStatus.OK);
	}

}
