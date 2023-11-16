package com.crud.serviceimp;

import java.util.ArrayList;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import javax.xml.bind.JAXBException;

import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.ObjectUtils;

import com.crud.config.RabbitMQConfig;
import com.crud.model.CreditDebitCardRequest;
import com.crud.model.MyAccount;
import com.crud.model.ServiceRequest;
import com.crud.repository.CreditDebitCardRequestRepo;
import com.crud.repository.MyAccountRepo;
import com.crud.repository.ServicerequestRepo;
import com.crud.service.CreditDebitCardrequestservice;
import com.crud.servicerequestDTO.CreditCardDTO;
import com.primesoftinc.message.service.CoreService;
import com.primesoftinc.message.template.RequestTemplate;
@Service
public class CreditDebitRequestImp implements CreditDebitCardrequestservice {

	@Autowired
	private CreditDebitCardRequestRepo cardRequestRepo;
	@Autowired
	private MyAccountRepo myAccountRepo;
	@Autowired
	private ServicerequestRepo servicerequestRepo;
	@Autowired
	private CoreService coreService;
	@Autowired
	private RabbitTemplate rabbitTemplate;
	
	@Override
	public Object getbyid(int customerId) {
	List<Map<String, Object>>list=new ArrayList<>();
    List<MyAccount>myaccount=myAccountRepo.findBycustomerId(customerId);
    if(!ObjectUtils.isEmpty(myaccount))
    {
    	for(MyAccount  e:myaccount)
    	{
    		Map<String,Object>map=new HashMap<>();
    		map.put("accountnumber", e.getAccountNumber());
  		map.put("customerId",e.getCustomermaster().getCustomerId());
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
	public Object saverequest(CreditCardDTO creditrequest) throws JAXBException, InstantiationException, IllegalAccessException {
	
		Map<String,Object>map=new HashMap<String, Object>();

		if(creditrequest.getCardtype().isEmpty()||creditrequest.getCardtype()==null)
		{
			map.put("status","error");
			map.put("msg","enter the valid cardtype");
		}
			else if(creditrequest.getAccountNumber()==0)
		{
			map.put("status","error");
			map.put("msg","enter the valid accountnumber");
		}
		else if(creditrequest.getServiceRequestId()==0)
		{
			map.put("status","error");
			map.put("msg","enter the valid requestid");
		}
		else if(creditrequest.getRequestMessage().isEmpty()||creditrequest.getRequestMessage()==null)
		{
			map.put("status","error");
			map.put("msg","enter the valid requestMessage");
		}
		else
		{
		CreditDebitCardRequest creditDebitCard= new CreditDebitCardRequest();
		creditDebitCard.setCardtype(creditrequest.getCardtype());
		MyAccount myAccount =  myAccountRepo.findById(creditrequest.getAccountNumber()).orElse(null);
      
		creditDebitCard.setAccount(myAccount);
		creditDebitCard.setRequest_date(new Date());
		ServiceRequest serviceRequest =  servicerequestRepo.findById(creditrequest.getServiceRequestId()).orElse(null);
		creditDebitCard.setServiceRequest(serviceRequest);
		creditDebitCard.setResponseStatus("pending");	
		creditDebitCard.setRequestMessage(creditrequest.getRequestMessage());
		CreditDebitCardRequest crdDebCard	=cardRequestRepo.save(creditDebitCard);
			map.put("status","success");
			map.put("msg","data saved successfully");

	String ReqTemplate =	RequestTemplate.RequestXMLTemplate;
	com.primesoftinc.message.customerRegister.ServiceRequest serRequest = (com.primesoftinc.message.customerRegister.ServiceRequest) coreService.unmarshal(ReqTemplate, com.primesoftinc.message.customerRegister.ServiceRequest.class);
	serRequest.setCustomerId(myAccount.getCustomermaster().getCustomerId());
	serRequest.setAccountNumber(myAccount.getAccountNumber());
	serRequest.getRequestType().setRequestDate(new Date());
	serRequest.getRequestType().setRequestId(crdDebCard.getCreditDebitRequestId());
	serRequest.getRequestType().setRequestMessage(creditrequest.getRequestMessage());
	serRequest.getRequestType().setTypeOfRequest(2);
	serRequest.getRequestType().setRequestData(creditrequest.getCardtype());
	String xml = coreService.marshal(com.primesoftinc.message.customerRegister.ServiceRequest.class, serRequest);
	rabbitTemplate.convertAndSend(RabbitMQConfig.Exchange,RabbitMQConfig.ROUTINGKEYSENDER,xml);
		}
		return map ;
		}
	
	
	
	@Override
	public Object getbyservicereqid(int service_request_id) {
		List<Map<String,Object>>list=new ArrayList<>();
		List<CreditDebitCardRequest> cardRequests=cardRequestRepo.findByservicerequestId(service_request_id);
		if(!ObjectUtils.isEmpty(cardRequests))
		{
			for(CreditDebitCardRequest cardRequest:cardRequests)
			{
				Map<String,Object>map=new HashMap<>();
				map.put("creditrequestid",cardRequest.getCreditDebitRequestId());
				map.put("requestDate", cardRequest.getRequest_date());
				map.put("processid", cardRequest.getProcessId());
				map.put("cardtype", cardRequest.getCardtype());
				map.put("response date", cardRequest.getResponse_date());
				map.put("responsemessage", cardRequest.getResponseMessage());
				map.put("response status", cardRequest.getResponseStatus());
				map.put("accountnumber", cardRequest.getAccount().getAccountNumber());
				map.put("servicerequest id", cardRequest.getServiceRequest().getService_request_id());
				map.put("Name", cardRequest.getAccount().getCustomermaster().getName());
				list.add(map);
			}
			
		}
		else
		{
			Map<String,Object>map1=new HashMap<>();
			map1.put("status","error");
			map1.put("msg","invalid service request id");
			return map1;
		}
		return list;
	}





	

	
	

}
