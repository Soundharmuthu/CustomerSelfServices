package com.crud.servicerequestDTO;

import java.io.Serializable;


public class CreditCardDTO implements Serializable{
	
	
	private static final long serialVersionUID = -474451413496014605L;
	
	
	private String cardtype;
	private int serviceRequestId;
	private Long accountNumber;
	private String requestMessage;
	
	public String getCardtype() {
		return cardtype;
	}
	public void setCardtype(String cardtype) {
		this.cardtype = cardtype;
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
	public CreditCardDTO(String cardtype, int serviceRequestId, Long accountNumber, String requestMessage) {
		super();
		this.cardtype = cardtype;
		this.serviceRequestId = serviceRequestId;
		this.accountNumber = accountNumber;
		this.requestMessage = requestMessage;
	}
	public CreditCardDTO() {
		super();
	}
	
	
	
	
}
