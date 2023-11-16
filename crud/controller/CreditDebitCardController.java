package com.crud.controller;

import javax.xml.bind.JAXBException;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import com.crud.service.CreditDebitCardrequestservice;
import com.crud.servicerequestDTO.CreditCardDTO;

@CrossOrigin
@RestController
public class CreditDebitCardController {
	@Autowired
	private CreditDebitCardrequestservice creditcardrequest;
	
	@GetMapping("/fetchaccountnumber/{customerId}")
	public Object getbyCustomerId(@PathVariable int customerId)
	{
		return(creditcardrequest.getbyid(customerId));
	}
	@PostMapping("/savecarrequest")
	public Object saveCardrequest(@RequestBody CreditCardDTO creditrequest )throws JAXBException, InstantiationException, IllegalAccessException
	{
		return(creditcardrequest.saverequest(creditrequest));
	}
	
	@GetMapping("/getrequestid/{service_request_id}")
	public Object getbyserviceid(@PathVariable int service_request_id )
	{
		return (creditcardrequest.getbyservicereqid(service_request_id));
		
	}
	
}
