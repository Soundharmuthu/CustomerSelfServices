package com.crud.controller;

import javax.xml.bind.JAXBException;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import com.crud.service.LostStolenCardService;
import com.crud.servicerequestDTO.LostorStolenDTO;


@CrossOrigin
@RestController
public class LostStolenController {
	
	@Autowired
	private LostStolenCardService cardService;
	
	@GetMapping("/accnumberfetch/{customerId}")
	public Object getbyCustomerId(@PathVariable int customerId)
	{
		return(cardService.getbycustomerid(customerId));
	}
	
	
	@GetMapping("/getservicerequestid/{service_request_id}")
	public Object getbyservicerequestid(@PathVariable int service_request_id)
	{
		return(cardService.getbysrevicereqid(service_request_id));
	}
	

	@PostMapping("/savelost")
	public Object savelost(@RequestBody LostorStolenDTO dtos) throws InstantiationException, IllegalAccessException, JAXBException
	{
		return(cardService.savelostcard(dtos));
	}
}
