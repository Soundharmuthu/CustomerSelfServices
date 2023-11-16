package com.crud.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import com.crud.service.AccountStatementService;
@CrossOrigin
@RestController
public class AccountStatementController  {
	
	@Autowired
	private AccountStatementService statementService;
	
	@GetMapping("/getstatement/{accountNumber}")
	public Object getStatement(@PathVariable long accountNumber)
	{
		return(statementService.getstatement(accountNumber));
	}

}
