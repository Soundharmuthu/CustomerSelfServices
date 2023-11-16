package com.crud.serviceimp;

import java.util.ArrayList;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.ObjectUtils;

import com.crud.model.ChequeBookRequest;
import com.crud.model.CreditDebitCardRequest;
import com.crud.model.LostStolenCard;
import com.crud.model.MyAccount;
import com.crud.repository.ChequeBookRequestRepo;
import com.crud.repository.CreditDebitCardRequestRepo;
import com.crud.repository.LostStolenCardRepo;
import com.crud.repository.MyAccountRepo;
import com.crud.service.ServiceRequestService;
import com.crud.servicerequestDTO.ServicerequestDto;
@Service
public class ServiceRequestServicemp implements ServiceRequestService{

	@Autowired
	private CreditDebitCardRequestRepo cardRequestRepo;
	@Autowired
	private ChequeBookRequestRepo bookRequestRepo;
	@Autowired
	private LostStolenCardRepo cardRepo;
	@Autowired
	private MyAccountRepo accountRepo;
	

	
	
	@Override
	public Object getbyserviceid(ServicerequestDto dto) {
		List<Map<String,Object>>list=new ArrayList<>();
			
		 if(dto.getService_request_id()==1)
		{
			 System.out.println("//////@@@@@@@@@"+dto.getService_request_id());
			 
			 List<ChequeBookRequest>bookRequest=bookRequestRepo.findbyaccountnumber(dto.getAccountNumber());
			
				for(ChequeBookRequest chequeBookRequest:bookRequest)
				{
					Map<String,Object>map=new HashMap<>();
					map.put("requestdate", chequeBookRequest.getRequestdate());
					map.put("responseDate", chequeBookRequest.getResponsedate());
					map.put("responseMessage", chequeBookRequest.getResponsemessage());
					map.put("responsestatus",chequeBookRequest.getResponsestatus());
					map.put("serviceid",chequeBookRequest.getServiceRequest().getService_request_id());
					list.add(map);
				}
			
	
		}
		 else if(dto.getService_request_id()==2)
		{
			 System.out.println("//////@@@@@@@@@"+dto.getAccountNumber());
				List<CreditDebitCardRequest>credits=cardRequestRepo.findbyaccountnumber(dto.getAccountNumber());
				for(CreditDebitCardRequest credit:credits)
				{
					Map<String,Object>map=new HashMap<>();
					map.put("requestdate", credit.getRequest_date());
					map.put("responseDate", credit.getResponse_date());
					map.put("responseMessage", credit.getResponseMessage());
					map.put("responsestatus",credit.getResponseStatus());
					map.put("serviceid",credit.getServiceRequest().getService_request_id());
					list.add(map);
					System.out.println("LIST"+list);
				}

		}
		 else if(dto.getService_request_id()==3)
		{
				List<LostStolenCard>lost=cardRepo.findbyaccountnumber(dto.getAccountNumber());
				for(LostStolenCard LostStolenCard:lost)
				{ 
					Map<String,Object>map=new HashMap<>();
					map.put("responseDate", LostStolenCard.getResponsedate());
					map.put("requestdate", LostStolenCard.getResquest_date());
					map.put("responseMessage", LostStolenCard.getResponse_message());
					map.put("responsestatus",LostStolenCard.getResponse_status());
					map.put("serviceid",LostStolenCard.getServiceRequest().getService_request_id());
					list.add(map);
				}
		}
			else
			{
				List<MyAccount>lost=accountRepo.getbyaccountnumber(dto.getAccountNumber());
				Map<String,Object>map=new HashMap<>();
				for(MyAccount card:lost)
				{ 
					List<Map<String,Object>> chequeList= new ArrayList<>();
			        List<Map<String,Object>> cardDebList= new ArrayList<>();
			        List<Map<String,Object>>  lostenCardList= new ArrayList<>();
			 	       
					 List<ChequeBookRequest> chequebook = card.getBookRequests();
				        for(ChequeBookRequest cheque : chequebook)
				        {
				        	
				        	Map<String,Object>chequeMap=new HashMap<>();
				        	chequeMap.put("requestDate", cheque.getRequestdate());
				        	chequeMap.put("responseDate", cheque.getResponsedate());
				        	chequeMap.put("responseMessage", cheque.getResponsemessage());
				        	chequeMap.put("responsestatus",cheque.getResponsestatus());	
				        	chequeMap.put("service_req_id", cheque.getServiceRequest().getService_request_id());
				        	chequeList.add(chequeMap);
				      	map.put("chequebook", chequeList);
//				        	list.add(map);
				        
				        }
				        			        
			        List<CreditDebitCardRequest> creditCard = card.getList();
			        for(CreditDebitCardRequest cardDeb : creditCard)
			        {	
			        	Map<String,Object>cardDebMap=new HashMap<>();
			        	cardDebMap.put("requestDate", cardDeb.getRequest_date());
			        	cardDebMap.put("responseDate", cardDeb.getResponse_date());
			        	cardDebMap.put("responseMessage", cardDeb.getResponseMessage());
			        	cardDebMap.put("responsestatus",cardDeb.getResponseStatus());
			        	cardDebMap.put("service_req_id", cardDeb.getServiceRequest().getService_request_id());
			        	cardDebList.add(cardDebMap);
			        	map.put("cardDebitCard",cardDebList);
//			        	list.add(map);
			        }
			        List<LostStolenCard> lostenCard = card.getCards();
			        for(LostStolenCard losten : lostenCard)
			        {	
			        	Map<String,Object>lostenCardMap=new HashMap<>();
			        	lostenCardMap.put("requestDate", losten.getResquest_date());
			        	lostenCardMap.put("responseDate", losten.getResponsedate());
			        	lostenCardMap.put("responseMessage", losten.getResponse_message());
			        	lostenCardMap.put("responsestatus",losten.getResponse_status());
			        	lostenCardMap.put("service_req_id", losten.getServiceRequest().getService_request_id());
			        	lostenCardList.add(lostenCardMap);
			        	map.put("lostenCard",lostenCardList);
//			        	list.add(map);
			        }       
				}
				list.add(map);
			}
		return list;
	
}




	@Override
	public Object getaccountalldetails(int customerId) {
		List<Map<String, Object>>list=new ArrayList<>();
	    List<MyAccount>myaccount=accountRepo.findBycustomerId(customerId);
	    if(myaccount!=null)
	    {
	    	for(MyAccount  e:myaccount)
	    	{
	    		Map<String,Object>map=new HashMap<>();
	    		map.put("accountnumber", e.getAccountNumber());
	    		list.add(map);
	    	}
	    }
	    else
		{
			Map<String,Object>map=new HashMap<>();
			map.put("status", "error");
			map.put("msg","invalid id");
			list.add(map);
		}
		return list;
	}






}


