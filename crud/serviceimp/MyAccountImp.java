package com.crud.serviceimp;

import java.util.ArrayList;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.ObjectUtils;

import com.crud.model.MyAccount;
import com.crud.repository.MyAccountRepo;
import com.crud.service.MyAccountService;

@Service
public class MyAccountImp implements MyAccountService {
	
	@Autowired
	private MyAccountRepo myAccountRepo;


	@Override
	public Object getaccountdetails(int customerId) {
		List<Map<String,Object>> list=new ArrayList<>();
		List<MyAccount>myAccounts=myAccountRepo.findByCustomerId(customerId);
		if(!ObjectUtils.isEmpty(myAccounts))
		{
		for(MyAccount a:myAccounts)
		{
			Map<String,Object>map=new HashMap<>();
			map.put("accountNumber", a.getAccountNumber());
			map.put("accountType", a.getAccountType());
			map.put("accountBalance", a.getAccountBalance());
			map.put("branchName", a.getBranchName());
			map.put("Ifsccode", a.getIfscCode());
			map.put("Name", a.getCustomermaster().getName());
			list.add(map);
		}
		}
		else
		{
			Map<String,Object>map=new HashMap<>();
			map.put("status","error");
			map.put("msg", "customerId is invalid");
			return map;
		}
		return list;
	
	
	}


}
