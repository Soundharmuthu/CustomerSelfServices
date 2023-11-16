package com.crud.service;

import java.io.IOException;

import com.crud.loginDto.LoginDto;
import com.crud.model.CustomerMaster;

public interface CustomerMasterService {

	public Object getCustomer(int customerId);

	public Object updatecustomer(CustomerMaster customerMaster);

	public Object validateDto(LoginDto logindto) throws IOException;


	

}