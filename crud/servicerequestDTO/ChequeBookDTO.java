package com.crud.servicerequestDTO;

import java.io.Serializable;

public class ChequeBookDTO implements Serializable {

	private static final long serialVersionUID = -1148009908598858492L;
	
	
	private String noOfChequeLeaves;
	private int serviceRequestId;
	private Long accountNumber;
	private String requestMessage;
	
	public String getNoOfChequeLeaves() {
		return noOfChequeLeaves;
	}
	public void setNoOfChequeLeaves(String noOfChequeLeaves) {
		this.noOfChequeLeaves = noOfChequeLeaves;
	}
	public int getServiceRequestId() {
		return serviceRequestId;
	}
	public void setServiceRequestId(int serviceRequestId) {
		this.serviceRequestId = serviceRequestId;
	}
	public Long getAccountNumber() {
		return accountNumber;
	}
	public void setAccountNumber(Long accountNumber) {
		this.accountNumber = accountNumber;
	}
	public String getRequestMessage() {
		return requestMessage;
	}
	public void setRequestMessage(String requestMessage) {
		this.requestMessage = requestMessage;
	}
	public ChequeBookDTO(String noOfChequeLeaves, int serviceRequestId, Long accountNumber, String requestMessage) {
		super();
		this.noOfChequeLeaves = noOfChequeLeaves;
		this.serviceRequestId = serviceRequestId;
		this.accountNumber = accountNumber;
		this.requestMessage = requestMessage;
	}
	public ChequeBookDTO() {
		super();
	}
	
	

}
