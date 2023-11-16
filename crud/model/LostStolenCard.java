package com.crud.model;


import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table(name="lostStolencard")
public class LostStolenCard {
	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	private int stolen_request_id;
		private Date resquest_date;
		
		private String requestMessage;
		
		@Column(length=10)
	   private String cardType;

		@Column(length=16)
		private long cardNumber;
	

	@Column(length=45)
	private String response_message;
	@Column(length=30)
	private String response_status;
	@Column(nullable=false)
	private Date stolen_date;
	
	private int process_id;

	private Date responsedate;
	


	



	public int getStolen_request_id() {
		return stolen_request_id;
	}



	public void setStolen_request_id(int stolen_request_id) {
		this.stolen_request_id = stolen_request_id;
	}



	public Date getResquest_date() {
		return resquest_date;
	}



	public void setResquest_date(Date resquest_date) {
		this.resquest_date = resquest_date;
	}



	public String getRequestMessage() {
		return requestMessage;
	}



	public void setRequestMessage(String requestMessage) {
		this.requestMessage = requestMessage;
	}



	public String getCardType() {
		return cardType;
	}



	public void setCardType(String cardType) {
		this.cardType = cardType;
	}



	public long getCardNumber() {
		return cardNumber;
	}



	public void setCardNumber(long cardNumber) {
		this.cardNumber = cardNumber;
	}



	public String getResponse_message() {
		return response_message;
	}



	public void setResponse_message(String response_message) {
		this.response_message = response_message;
	}



	public String getResponse_status() {
		return response_status;
	}



	public void setResponse_status(String response_status) {
		this.response_status = response_status;
	}



	public Date getStolen_date() {
		return stolen_date;
	}



	public void setStolen_date(Date stolen_date) {
		this.stolen_date = stolen_date;
	}



	public int getProcess_id() {
		return process_id;
	}



	public void setProcess_id(int process_id) {
		this.process_id = process_id;
	}



	public Date getResponsedate() {
		return responsedate;
	}



	public void setResponsedate(Date responsedate) {
		this.responsedate = responsedate;
	}



	public MyAccount getMyAccount() {
		return myAccount;
	}



	public void setMyAccount(MyAccount myAccount) {
		this.myAccount = myAccount;
	}



	public ServiceRequest getServiceRequest() {
		return serviceRequest;
	}



	public void setServiceRequest(ServiceRequest serviceRequest) {
		this.serviceRequest = serviceRequest;
	}



	@ManyToOne
	@JoinColumn(name="accountNumber")
	private MyAccount myAccount;

	
	
	@ManyToOne
	@JoinColumn(name="service_request_id")
	private ServiceRequest serviceRequest;

	
}
