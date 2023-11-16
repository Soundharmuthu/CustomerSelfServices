
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
@Table(name="account_statement")
public class AccountStatement {
	
	
	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	private int accountStatementId;
	
	@Column(nullable=false)
	private Date date;
	
	@Column(length=30,nullable=false)
	private String description;
	
	@Column(length=30,nullable=false)
	private double creditAmount;
	
	@Column(length=30,nullable=false)
	private double debitAmount;
	
	public double getCreditAmount() {
		return creditAmount;
	}



	public void setCreditAmount(double creditAmount) {
		this.creditAmount = creditAmount;
	}



	public double getDebitAmount() {
		return debitAmount;
	}



	public void setDebitAmount(double debitAmount) {
		this.debitAmount = debitAmount;
	}



	@Column(nullable=false)
	private String chequeRefNo;
	
	@Column(nullable=false)
	private double closingBalance;

	

	public int getAccountStatementId() {
		return accountStatementId;
	}



	public void setAccountStatementId(int accountStatementId) {
		this.accountStatementId = accountStatementId;
	}



	public Date getDate() {
		return date;
	}



	public void setDate(Date date) {
		this.date = date;
	}



	public String getDescription() {
		return description;
	}



	public void setDescription(String description) {
		this.description = description;
	}





	public String getChequeRefNo() {
		return chequeRefNo;
	}



	public void setChequeRefNo(String chequeRefNo) {
		this.chequeRefNo = chequeRefNo;
	}



	public double getClosingBalance() {
		return closingBalance;
	}



	public void setClosingBalance(double closingBalance) {
		this.closingBalance = closingBalance;
	}



	public MyAccount getMyAccount() {
		return myAccount;
	}



	public void setMyAccount(MyAccount myAccount) {
		this.myAccount = myAccount;
	}



	@ManyToOne
	@JoinColumn(name="accountNumber")
	private  MyAccount myAccount;

}
