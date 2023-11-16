package com.crud.service;

import java.util.ArrayList;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.crud.model.CustomerMaster;
import com.crud.repository.CustomerMasterRepo;

@Service
public class JwtUserDetailsService implements UserDetailsService{
	
	@Autowired
	private CustomerMasterRepo customerMasterRepo;

	public UserDetails loadUserByUsername(String userName) {
		CustomerMaster customer  =customerMasterRepo.findByUser(userName);
		if(customer.getUserName().equals(userName)) {
		return new User(customer.getUserName(),customer.getPassword(),
		new ArrayList<>());
		} else {
			throw new UsernameNotFoundException("User not found with username: " + userName);
		}
	
	}
	

}
