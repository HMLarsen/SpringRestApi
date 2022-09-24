package com.hugo.larsen.api.controllers;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("api")
public class HelloRestController {

	@GetMapping("public/user")
	public String helloUser() {
		return "Hello User";
	}

	@GetMapping("auth/test")
	public String helloAdmin() {
		return "Hello Admin";
	}

}