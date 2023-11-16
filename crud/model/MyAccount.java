package com.crud.model;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;



@Entity
@Table(name="my_account")
public class MyAccount {

	@Id
    @GeneratedValue(strategy=GenerationType.AUTO)
	private   long accountNumber;
    @Column(length=20,nullable=false)
    private String accountType;
    @Column(nullable=false)
    private double accountBalance;
    @Column(length=45,nullable=false)
    private String branchName;
    @Column(length=11,nullable=false)
    private String ifscCode;
   
    
   public long getAccountNumber() {
		return accountNumber;
	}
	public void setAccountNumber(long accountNumber) {
		this.accountNumber = accountNumber;
	}
	public String getAccountType() {
		return accountType;
	}
	public void setAccountType(String accountType) {
		this.accountType = accountType;
	}
	public double getAccountBalance() {
		return accountBalance;
	}
	public void setAccountBalance(double accountBalance) {
		this.accountBalance = accountBalance;
	}
	
	public String getBranchName() {
		return branchName;
	}
	public void setBranchName(String branchName) {
		this.branchName = branchName;
	}
	public String getIfscCode() {
		return ifscCode;
	}
	public void setIfscCode(String ifscCode) {
		this.ifscCode = ifscCode;
	}
	
	
   public CustomerMaster getCustomermaster() {
		return customermaster;
	}
	public void setCustomer_master(CustomerMaster customermaster) {
		this.customermaster = customermaster;
	}


@ManyToOne
   @JoinColumn(name="customerId")
   private CustomerMaster customermaster;




	@OneToMany(mappedBy="myAccount")
	private List<AccountStatement> lists=new ArrayList<>();

	public List<AccountStatement> getLists() {
		return lists;
	}
	public void setLists(List<AccountStatement> lists) {
		this.lists = lists;
	}
	
	
	
	@OneToMany(mappedBy="account")
	List<CreditDebitCardRequest>list=new ArrayList<>();


	
	public List<CreditDebitCardRequest> getList() {
		return list;
	}
	public void setList(List<CreditDebitCardRequest> list) {
		this.list = list;
	}
	
	@OneToMany(mappedBy="myAccount")
	List<ChequeBookRequest>bookRequests=new ArrayList<>();


	public List<ChequeBookRequest> getBookRequests() {
		return bookRequests;
	}
	public void setBookRequests(List<ChequeBookRequest> bookRequests) {
		this.bookRequests = bookRequests;
	}

	@OneToMany(mappedBy="myAccount")
	List<LostStolenCard>cards=new ArrayList<LostStolenCard>();


	public List<LostStolenCard> getCards() {
		return cards;
	}
	public void setCards(List<LostStolenCard> cards) {
		this.cards = cards;
	}
	
	}


