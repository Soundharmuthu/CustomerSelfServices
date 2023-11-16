package com.crud.service;

import javax.xml.bind.JAXBException;

import com.crud.servicerequestDTO.ChequeBookDTO;

public interface ChequeBookService {



	public Object getbyservicerequestid(int service_request_id);


	public Object getbycustomerid(int customerId);

	public Object saverequest(ChequeBookDTO chequeBookDTO) throws JAXBException, InstantiationException, IllegalAccessException;



}
