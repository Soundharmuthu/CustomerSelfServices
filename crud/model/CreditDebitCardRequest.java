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
@Table(name="credit_debitrequest")
public class CreditDebitCardRequest {


	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	private int creditDebitRequestId;
	@Column(length=10,nullable=false)
	private String cardtype;
	@Column(length=30)
	private String responseStatus;
	@Column(length=45)
	private String responseMessage;
	private int ProcessId;
	@Column(nullable=false)
	private Date request_date;

	private Date response_date;
	
	private String requestMessage;
	@Column(length=16)
	private long cardNumber;
	
	public long getCardNumber() {
		return cardNumber;
	}
	public void setCardNumber(long cardNumber) {
		this.cardNumber = cardNumber;
	}
	public String getRequestMessage() {
		return requestMessage;
	}
	public void setRequestMessage(String requestMessage) {
		this.requestMessage = requestMessage;
	}
	public int getCreditDebitRequestId() {
		return creditDebitRequestId;
	}
	public void setCreditDebitRequestId(int creditDebitRequestId) {
		this.creditDebitRequestId = creditDebitRequestId;
	}
	public String getCardtype() {
		return cardtype;
	}
	public void setCardtype(String cardtype) {
		this.cardtype = cardtype;
	}
	public String getResponseStatus() {
		return responseStatus;
	}
	public void setResponseStatus(String responseStatus) {
		this.responseStatus = responseStatus;
	}
	public String getResponseMessage() {
		return responseMessage;
	}
	public void setResponseMessage(String responseMessage) {
		this.responseMessage = responseMessage;
	}
	
	public int getProcessId() {
		return ProcessId;
	}
	public void setProcessId(int processId) {
		ProcessId = processId;
	}
	
	
	


	public Date getRequest_date() {
		return request_date;
	}
	public void setRequest_date(Date request_date) {
		this.request_date = request_date;
	}
	public Date getResponse_date() {
		return response_date;
	}
	public void setResponse_date(Date response_date) {
		this.response_date = response_date;
	}





	@ManyToOne
	@JoinColumn(name="accountNumber")
	private MyAccount account;
	
	
		public MyAccount getAccount() {
			return account;
		}
		public void setAccount(MyAccount account) {
			this.account = account;
		}
		
		@ManyToOne
		@JoinColumn(name="service_request_id")
		private ServiceRequest serviceRequest;
	
		public ServiceRequest getServiceRequest() {
			return serviceRequest;
		}
		public void setServiceRequest(ServiceRequest serviceRequest) {
			this.serviceRequest = serviceRequest;
		}
	
}
