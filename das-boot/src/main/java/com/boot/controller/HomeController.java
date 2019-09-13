package com.boot.controller;

import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@CrossOrigin(origins = "http://localhost:4200")
@RestController
public class HomeController {
	/*
	@RequestMapping(value = "/", method = RequestMethod.GET)
	public String changeLocale() {
		return "my-locale";
	}
	*/
}
