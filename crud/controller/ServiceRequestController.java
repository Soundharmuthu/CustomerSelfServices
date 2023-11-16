package com.crud.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.crud.service.ServiceRequestService;
import com.crud.servicerequestDTO.ServicerequestDto;
@CrossOrigin
@RestController
public class ServiceRequestController {
	
	@Autowired
	private ServiceRequestService requestService;
	
	@PostMapping("/getbyseviceid")
	public Object getbyserviceid(@RequestBody ServicerequestDto dto)
	{
		return(requestService.getbyserviceid(dto));
	}
	
	@GetMapping("/accnumbers/{customerId}")
	public Object getaccountDetails(@PathVariable int customerId)
	{
		return(requestService.getaccountalldetails(customerId));
	}

}
