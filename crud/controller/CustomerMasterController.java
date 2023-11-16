package com.crud.controller;

import java.io.IOException;

import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.crud.loginDto.LoginDto;
import com.crud.model.CustomerMaster;
import com.crud.service.CustomerMasterService;
@CrossOrigin
@RestController
public class CustomerMasterController {
	@Autowired
	private CustomerMasterService customerservice;
	
	@PostMapping("validatelogin")
	public Object loginDto(@RequestBody LoginDto logindto) throws IOException
	{
//		System.out.println("///////////"+logindto.getPassword()+"22222222"+logindto.getUserName());
		return(customerservice.validateDto(logindto));
	}
	
	@GetMapping("/getdetails/{customerId}")
	public Object getcustomerdetails(@PathVariable int customerId)
	{
		return(customerservice.getCustomer(customerId));	
	}
	
	@PostMapping("/updatecustomer")
	public Object updateCustomer(@RequestBody CustomerMaster customerMaster ){
		return(customerservice.updatecustomer(customerMaster));
	}

}
