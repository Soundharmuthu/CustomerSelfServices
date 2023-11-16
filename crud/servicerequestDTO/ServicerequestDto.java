package com.crud.servicerequestDTO;

import java.io.Serializable;

public class ServicerequestDto implements Serializable{
	
	private static final long serialVersionUID = 8336749435408801262L;
	
	
	private int service_request_id;
	private long accountNumber;
	
	
	public int getService_request_id() {
		return service_request_id;
	}
	public void setService_request_id(int service_request_id) {
		this.service_request_id = service_request_id;
	}
	public long getAccountNumber() {
		return accountNumber;
	}
	public void setAccountNumber(long accountNumber) {
		this.accountNumber = accountNumber;
	}
	public ServicerequestDto(int service_request_id, long accountNumber) {
		super();
		this.service_request_id = service_request_id;
		this.accountNumber = accountNumber;
	}
	public ServicerequestDto() {
		super();
	}
	
	
	
	
	
	
	
	


}
