package com.crud.controller;

import javax.xml.bind.JAXBException;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import com.crud.service.ChequeBookService;
import com.crud.servicerequestDTO.ChequeBookDTO;
@CrossOrigin

@RestController
public class ChequeBookController {
	
	@Autowired
	private ChequeBookService bookService;
	
	@GetMapping("/fetchaccnumber/{customerId}")
	public Object getbyCustomerId(@PathVariable int customerId)
	{
		return(bookService.getbycustomerid(customerId));
	}
	
		
	
  @GetMapping("/getbyservicerequestid/{service_request_id}")
 public Object getbyserviceid(@PathVariable int service_request_id) {
	  
	  return(bookService.getbyservicerequestid(service_request_id));
	  
  }
  
  
  @PostMapping("/postcardrequest")
	public Object saveCardrequest(@RequestBody ChequeBookDTO chequeBookDTO )throws JAXBException, InstantiationException, IllegalAccessException
	{
		return(bookService.saverequest(chequeBookDTO));
	}
  
}
