package edu.northeastern.cs5500.controller;

import org.springframework.web.bind.annotation.RequestMapping;

@org.springframework.web.bind.annotation.RestController
public class RestController {

	@RequestMapping("/api/hello/string") 
	public String sayHello () {
		return "Hello World!";
	}
}
