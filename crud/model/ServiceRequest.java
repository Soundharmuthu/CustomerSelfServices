package com.crud.model;


import java.util.ArrayList;

import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;



@Entity
@Table(name="service_request")
public class ServiceRequest {
	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	private int service_request_id;
	@Column(length=45,nullable=false)
	private  String requesttype;
	
	
	public String getRequesttype() {
		return requesttype;
	}
	public void setRequesttype(String requesttype) {
		this.requesttype = requesttype;
	}
	public int getService_request_id() {
		return service_request_id;
	}
	public void setService_request_id(int service_request_id) {
		this.service_request_id = service_request_id;
	}
	
	
	
	@OneToMany(mappedBy="serviceRequest")
List<CreditDebitCardRequest>lists=new ArrayList<CreditDebitCardRequest>();


	public List<CreditDebitCardRequest> getLists() {
		return lists;
	}
	public void setLists(List<CreditDebitCardRequest> lists) {
		this.lists = lists;
	}

@OneToMany(mappedBy="serviceRequest")
List<ChequeBookRequest>bookRequests=new ArrayList<>();


public List<ChequeBookRequest> getBookRequests() {
	return bookRequests;
}
public void setBookRequests(List<ChequeBookRequest> bookRequests) {
	this.bookRequests = bookRequests;
}
	
@OneToMany(mappedBy="serviceRequest")
List<LostStolenCard>lostStolenCards=new ArrayList<>();


public List<LostStolenCard> getLostStolenCards() {
	return lostStolenCards;
}
public void setLostStolenCards(List<LostStolenCard> lostStolenCards) {
	this.lostStolenCards = lostStolenCards;
}

	
	
}
