package com.crud.model;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

@Entity
@Table(name="customer_master")
public class CustomerMaster {
	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	private int customerId;
	@Column(length=30,nullable=false)
	private String name;
	@Column(length=30,nullable=false)
	private String userName;
	@Column(length=15,nullable=false)
	private String password;
	@Column(nullable=false)
	@Temporal(TemporalType.DATE)
	private Date dob;
	@Column(length=10,nullable=false)
	private String phoneNo;
	@Column(length=45,nullable=false)
	private String doorStreet;
	@Column(length=20,nullable=false)
	private String city;
	@Column(length=6,nullable=false)
	private String pincode;
	@Column(length=10,nullable=false)
	private String panNo;
	@Column(length=35,nullable=false)
	private String email;
	@Column(length=30,nullable=false)
	private String state;
	
	
	
	
	public int getCustomerId() {
		return customerId;
	}
	public void setCustomerId(int customerId) {
		this.customerId = customerId;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getUserName() {
		return userName;
	}
	public void setUserName(String userName) {
		this.userName = userName;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public Date getDob() {
		return dob;
	}
	public void setDob(Date dob) {
		this.dob = dob;
	}
	public String getPhoneNo() {
		return phoneNo;
	}
	public void setPhoneNo(String phoneNo) {
		this.phoneNo = phoneNo;
	}
	public String getDoorStreet() {
		return doorStreet;
	}
	public void setDoorStreet(String doorStreet) {
		this.doorStreet = doorStreet;
	}
	public String getCity() {
		return city;
	}
	public void setCity(String city) {
		this.city = city;
	}
	public String getPincode() {
		return pincode;
	}
	public void setPincode(String pincode) {
		this.pincode = pincode;
	}
	public String getPanNo() {
		return panNo;
	}
	public void setPanNo(String panNo) {
		this.panNo = panNo;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public String getState() {
		return state;
	}
	public void setState(String state) {
		this.state = state;
	}
	@OneToMany(mappedBy="customermaster")
	private List<MyAccount> list=new ArrayList<>();


	public List<MyAccount> getList() {
		return list;
	}
	public void setList(List<MyAccount> list) {
		this.list = list;
	}
	


}
