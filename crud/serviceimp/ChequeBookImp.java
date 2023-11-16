package com.crud.serviceimp;

import java.util.*;


import javax.xml.bind.JAXBException;

import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.ObjectUtils;

import com.crud.config.RabbitMQConfig;
import com.crud.model.ChequeBookRequest;
import com.crud.model.MyAccount;
import com.crud.model.ServiceRequest;
import com.crud.repository.ChequeBookRequestRepo;
import com.crud.repository.MyAccountRepo;
import com.crud.repository.ServicerequestRepo;
import com.crud.service.ChequeBookService;
import com.crud.servicerequestDTO.ChequeBookDTO;
import com.primesoftinc.message.service.CoreService;
import com.primesoftinc.message.template.RequestTemplate;
@Service
public class ChequeBookImp implements ChequeBookService {

	@Autowired
	private ChequeBookRequestRepo requestRepo;
	@Autowired
	private MyAccountRepo myAccountRepo;
	@Autowired
	private ServicerequestRepo servicerequestRepo;
	@Autowired
	private CoreService coreService;
	@Autowired
	private RabbitTemplate rabbitTemplate;
	
	
	@Override
	public Object getbyservicerequestid(int service_request_id) {
		List<Map<String,Object>>list=new ArrayList<>();
		List<ChequeBookRequest>bookRequests=requestRepo.findbyserviceid(service_request_id);
		if(bookRequests!=null)
		{
			for(ChequeBookRequest request:bookRequests)
			{
				Map<String,Object>map=new HashMap<>();
				map.put("chequebookrequestis", request.getChequebookRequestId());
				map.put("noofchequeleaves", request.getNoofchequeleaves());
				map.put("processid",request.getRequestdate());
				map.put("responsemessgae", request.getRequestMessage());
				map.put("responsedate",request.getResponsedate());
				map.put("responsestatus",request.getResponsestatus());
				map.put("accountNumber",request.getMyAccount().getAccountNumber());
				map.put("servicerequestid",request.getServiceRequest().getService_request_id());
				list.add(map);
			}
		}
		else
		{
			Map<String,Object>map=new HashMap<>();
			map.put("status","error");
			map.put("msg","invalid servicerequestid");
			list.add(map);
			
		}
		return list;
	}

	@Override
	public Object getbycustomerid(int customerId) {
		List<Map<String, Object>>list=new ArrayList<>();
	    List<MyAccount>myaccount=myAccountRepo.findBycustomerId(customerId);
	    if(!ObjectUtils.isEmpty(myaccount))
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
			return map;
		}
	    
			return list;
	}

	@Override
	public Object saverequest(ChequeBookDTO chequeBookDTO) throws JAXBException, InstantiationException, IllegalAccessException {
		Map<String,Object>map=new HashMap<String,Object>();
		if(chequeBookDTO.getNoOfChequeLeaves().isEmpty()||chequeBookDTO.getNoOfChequeLeaves()==null)
		{
			map.put("status","error");
			map.put("msg","give the valid numbers");
		}
		
		else if(chequeBookDTO.getAccountNumber()==0)
		{
			map.put("status","error");
			map.put("msg","give the valid accountNumber");
		}
		else if(chequeBookDTO.getServiceRequestId()==0)
		{
			map.put("status","error");
			map.put("msg","give the valid requesttypeid");
		}
		else if(chequeBookDTO.getRequestMessage().isEmpty()||chequeBookDTO.getRequestMessage()==null)
		{
			map.put("status","error");
			map.put("msg","give the valid requesttypeid");
		}
	else
		{
			ChequeBookRequest chequeBook=new ChequeBookRequest();
			chequeBook.setNoofchequeleaves(chequeBookDTO.getNoOfChequeLeaves());
			MyAccount myAccount =  myAccountRepo.findById(chequeBookDTO.getAccountNumber()).orElse(null);

			chequeBook.setMyAccount(myAccount);
			chequeBook.setRequestdate(new Date());
			ServiceRequest serviceRequest1 =  servicerequestRepo.findById(chequeBookDTO.getServiceRequestId()).orElse(null);
			chequeBook.setServiceRequest(serviceRequest1);
			chequeBook.setRequestMessage(chequeBookDTO.getRequestMessage());
			chequeBook.setResponsestatus("pending");	
			ChequeBookRequest creditrequest	=requestRepo.save(chequeBook);
				map.put("status","success");
				map.put("msg","data saved successfully");
		String ReqTemplate =	RequestTemplate.RequestXMLTemplate;
		com.primesoftinc.message.customerRegister.ServiceRequest serRequest = (com.primesoftinc.message.customerRegister.ServiceRequest) coreService.unmarshal(ReqTemplate, com.primesoftinc.message.customerRegister.ServiceRequest.class);
		serRequest.setCustomerId(myAccount.getCustomermaster().getCustomerId());
		serRequest.setAccountNumber(myAccount.getAccountNumber());
		serRequest.getRequestType().setRequestDate(new Date());
		serRequest.getRequestType().setRequestId(creditrequest.getChequebookRequestId());
		serRequest.getRequestType().setRequestMessage(chequeBookDTO.getRequestMessage());
		serRequest.getRequestType().setTypeOfRequest(1);
		serRequest.getRequestType().setRequestData(chequeBookDTO.getNoOfChequeLeaves());
		String xml = coreService.marshal(com.primesoftinc.message.customerRegister.ServiceRequest.class, serRequest);
		System.out.println("/////////"+xml);
		rabbitTemplate.convertAndSend(RabbitMQConfig.Exchange,RabbitMQConfig.ROUTINGKEYSENDER,xml);
			
		}
		return map;
	}
 
}
