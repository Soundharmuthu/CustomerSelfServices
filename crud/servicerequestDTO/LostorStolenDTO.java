package com.crud.servicerequestDTO;

import java.io.Serializable;
import java.util.Date;

public class LostorStolenDTO implements Serializable{
	
	
	private static final long serialVersionUID = 871187396652642413L;
	
	
	private int serviceRequestId;
	private Long accountNumber;
	private String requestMessage;
	private long cardNumber;
	private String cardType;
	private Date stolenDate;
	
	
	
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
	public long getCardNumber() {
		return cardNumber;
	}
	public void setCardNumber(long cardNumber) {
		this.cardNumber = cardNumber;
	}
	public String getCardType() {
		return cardType;
	}
	public void setCardType(String cardType) {
		this.cardType = cardType;
	}
	public Date getStolenDate() {
		return stolenDate;
	}
	public void setStolenDate(Date stolenDate) {
		this.stolenDate = stolenDate;
	}
	public LostorStolenDTO(int serviceRequestId, Long accountNumber, String requestMessage, long cardNumber,
			String cardType, Date stolenDate) {
		super();
		this.serviceRequestId = serviceRequestId;
		this.accountNumber = accountNumber;
		this.requestMessage = requestMessage;
		this.cardNumber = cardNumber;
		this.cardType = cardType;
		this.stolenDate = stolenDate;
	}
	public LostorStolenDTO() {
		super();
	}
	
	
	
	
	
	
}
