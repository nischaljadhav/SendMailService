package com.mailservice.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.mailservice.services.MailService;

@RestController
@RequestMapping("/api")
public class MailController {
	@Autowired
	private MailService sendMail;
	
	@GetMapping("/sendmail")
	public String sendMail(){
		return sendMail.sendMail();
		
	}
	

}
