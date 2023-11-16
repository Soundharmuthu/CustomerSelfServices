package com.crud.service;

import javax.xml.bind.JAXBException;

import com.crud.servicerequestDTO.LostorStolenDTO;

public interface LostStolenCardService {

	public Object getbysrevicereqid(int service_request_id);

	public Object getbycustomerid(int customerId);

	public Object savelostcard(LostorStolenDTO dtos) throws InstantiationException, IllegalAccessException, JAXBException;

}
