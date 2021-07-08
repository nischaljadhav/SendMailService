package com.mailservice.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api")
public class MailController {
	@GetMapping("/sendmail")
	public String sendMail(){
		return "success!"; 
	}
	

}
