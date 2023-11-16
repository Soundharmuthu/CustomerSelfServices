package com.crud.controller;

import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import com.crud.service.MyAccountService;
@CrossOrigin
@RestController
public class MyAccountController {
	
	@Autowired
	private MyAccountService myAccountService;
	
	@GetMapping("/accdetails/{customerId}")
	public Object getaccountDetails(@PathVariable int customerId)
	{
		return(myAccountService.getaccountdetails(customerId));
	}
	
	

}
