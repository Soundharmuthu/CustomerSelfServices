package com.crud.serviceimp;


import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.ObjectUtils;

import com.crud.model.AccountStatement;
import com.crud.repository.AccountSatementRepo;
import com.crud.service.AccountStatementService;

@Service
public class AccountStatementImp implements AccountStatementService {

	@Autowired
	private AccountSatementRepo accountrepo; 
	
	@Override
	public Object getstatement(long accountNumber){
		List<Map<String,Object>>list=new ArrayList<Map<String,Object>>();
		List<AccountStatement>statements=accountrepo.findByAccountnumber(accountNumber);
		
		if(!ObjectUtils.isEmpty(statements)) {	
		for(AccountStatement b:statements)
		{	Map<String,Object>map=new HashMap<>();
		   map.put("date", b.getDate());
		   map.put("Description",b.getDescription());
		   map.put("creditamount",b.getCreditAmount());
		   map.put("Debitamount",b.getDebitAmount());
		   map.put("ChequeNo",b.getChequeRefNo());
		   map.put("closingBalance",b.getClosingBalance());
		   map.put("accountNumber",b.getMyAccount().getAccountNumber());
		   list.add(map);  
		}
		}
		else
		{
			Map<String,Object>map=new HashMap<>();
			map.put("status","error");
			map.put("msg", "invalid account number");
			return map;
		}
		
		return list;
	}

	

}
