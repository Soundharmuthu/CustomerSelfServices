package com.crud.service;

import javax.xml.bind.JAXBException;

import com.crud.servicerequestDTO.CreditCardDTO;

public interface CreditDebitCardrequestservice {

	public Object saverequest(CreditCardDTO creditrequest)throws JAXBException, InstantiationException, IllegalAccessException;

	public Object getbyservicereqid(int service_request_id);


	public Object getbyid(int customerId);

	
	

	

}
