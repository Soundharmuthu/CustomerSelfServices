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
@Table(name="cheque_book_request")

public class ChequeBookRequest {
	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	private int chequebookRequestId;
	
	@Column(length=3,nullable=false)
	private String noofchequeleaves;
	
	private Date requestdate;
	
	private Date responsedate;
	
	private String requestMessage;
	
	@Column(length=45)
	private String responsemessage;
	
	@Column(length=45)
	private String responsestatus;
	
	private int processid;
	

	public String getRequestMessage() {
		return requestMessage;
	}

	public void setRequestMessage(String requestMessage) {
		this.requestMessage = requestMessage;
	}

	public Date getRequestdate() {
		return requestdate;
	}

	public void setRequestdate(Date requestdate) {
		this.requestdate = requestdate;
	}

	public Date getResponsedate() {
		return responsedate;
	}

	public void setResponsedate(Date responsedate) {
		this.responsedate = responsedate;
	}

	

	
	public int getChequebookRequestId() {
		return chequebookRequestId;
	}

	public void setChequebookRequestId(int chequebookRequestId) {
		this.chequebookRequestId = chequebookRequestId;
	}

	public String getNoofchequeleaves() {
		return noofchequeleaves;
	}

	public void setNoofchequeleaves(String noofchequeleaves) {
		this.noofchequeleaves = noofchequeleaves;
	}


	


	

	public String getResponsestatus() {
		return responsestatus;
	}
	public void setResponsestatus(String responsestatus) {
		this.responsestatus = responsestatus;
	}
	

  public int getProcessid() {
		return processid;
	}

	public void setProcessid(int processid) {
		this.processid = processid;
	}
	

	public String getResponsemessage() {
		return responsemessage;
	}

	public void setResponsemessage(String responsemessage) {
		this.responsemessage = responsemessage;
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

	@ManyToOne
	@JoinColumn(name="accountNumber")
	private MyAccount myAccount;


	public MyAccount getMyAccount() {
		return myAccount;
	}

	public void setMyAccount(MyAccount myAccount) {
		this.myAccount = myAccount;
	}


		
	}

