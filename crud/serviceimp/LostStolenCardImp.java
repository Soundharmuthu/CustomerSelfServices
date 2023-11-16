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

import com.crud.config.RabbitMQConfig;
import com.crud.model.LostStolenCard;
import com.crud.model.MyAccount;
import com.crud.model.ServiceRequest;
import com.crud.repository.LostStolenCardRepo;
import com.crud.repository.MyAccountRepo;
import com.crud.repository.ServicerequestRepo;
import com.crud.service.LostStolenCardService;
import com.crud.servicerequestDTO.LostorStolenDTO;
import com.primesoftinc.message.service.CoreService;
import com.primesoftinc.message.template.RequestTemplate;

@Service
public class LostStolenCardImp implements LostStolenCardService{
	
	@Autowired
	private LostStolenCardRepo cardRepo;
	@Autowired
	private MyAccountRepo accountRepo;
	@Autowired
	private  MyAccountRepo myAccountRepo;
	@Autowired
	private ServicerequestRepo servicerequestRepo;
	@Autowired
	private CoreService coreService;
	@Autowired
	private RabbitTemplate rabbitTemplate;


	@Override
	public Object getbysrevicereqid(int service_request_id) {
		List<Map<String,Object>>list=new ArrayList<>();
		List<LostStolenCard>list2=cardRepo.findbyservicerequestid(service_request_id);
		if(cardRepo!=null)
		{
			for(LostStolenCard card:list2)
			{
				Map<String, Object>map=new HashMap<>();
				map.put("lostrequestid",card.getStolen_request_id());
				map.put("accountNumber",card.getMyAccount().getAccountNumber());
				map.put("requestdate",card.getResponsedate());
				map.put("StolenDate",card.getStolen_date());
				map.put("service_request_id", card.getServiceRequest().getService_request_id());
				map.put("responsemessage",card.getResponse_message());
				map.put("responsestatus",card.getResponse_status());
				map.put("processId",card.getProcess_id());
				map.put("responsedate", card.getResponsedate());
			   list.add(map);
					
			}
		}
		else
		{
			Map<String, Object>map=new HashMap<>();
			map.put("status","error");
			map.put("msg","invalid service request");
			list.add(map);
		
		}
		return list;
	}

	@Override
	public Object getbycustomerid(int customerId) {
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

	@Override
	public Object savelostcard(LostorStolenDTO dtos) throws InstantiationException, IllegalAccessException, JAXBException {
		System.out.println("//////////\\\\\\\\\\\\"+dtos.getCardType());
		Map<String,Object>map=new HashMap<>();
		if(dtos.getServiceRequestId()==0)
		{
			map.put("status","error");
			map.put("msg","invalid service requestid");
		}
		else if(dtos.getAccountNumber()==0)
		{
			map.put("status","error");
			map.put("msg","invalid account number");
			
		}
		else if(dtos.getRequestMessage().isEmpty()||dtos.getRequestMessage()==null)
		{
			map.put("status","error");
			map.put("msg","invalid request Message");
				
		}
		else if(dtos.getCardType().isEmpty()||dtos.getCardType()==null)
		{

			map.put("status","error");
			map.put("msg","invalid cardType");
		}
		else if(dtos.getCardNumber()==0)
		{

			map.put("status","error");
			map.put("msg","invalid cardType");
		}
		
		else
		{
			LostStolenCard  lostStolenCard= new LostStolenCard();
			lostStolenCard.setCardType(dtos.getCardType());
			lostStolenCard.setStolen_date(dtos.getStolenDate());
			lostStolenCard.setCardNumber(dtos.getCardNumber());
			MyAccount myAccount =  myAccountRepo.findById(dtos.getAccountNumber()).orElse(null);
			lostStolenCard.setMyAccount(myAccount);
			System.out.println("????????"+myAccount);
			lostStolenCard.setResquest_date(new Date());
			ServiceRequest serviceRequest =  servicerequestRepo.findById(dtos.getServiceRequestId()).orElse(null);
			lostStolenCard.setServiceRequest(serviceRequest);
			lostStolenCard.setResponse_status("pending");	
			lostStolenCard.setRequestMessage(dtos.getRequestMessage());
			LostStolenCard crdDebCard	=cardRepo.save(lostStolenCard);
				map.put("status","success");
				map.put("msg","data saved successfully");

		String ReqTemplate =	RequestTemplate.RequestXMLTemplate;
		com.primesoftinc.message.customerRegister.ServiceRequest serRequest = (com.primesoftinc.message.customerRegister.ServiceRequest) coreService.unmarshal(ReqTemplate, com.primesoftinc.message.customerRegister.ServiceRequest.class);
		serRequest.setCustomerId(myAccount.getCustomermaster().getCustomerId());
		serRequest.setAccountNumber(myAccount.getAccountNumber());
		serRequest.getRequestType().setRequestData(dtos.getCardType());
		serRequest.getRequestType().setStolenDate(dtos.getStolenDate());
		serRequest.getRequestType().setCardNumber(lostStolenCard.getCardNumber());
		serRequest.getRequestType().setRequestId(crdDebCard.getStolen_request_id());
		serRequest.getRequestType().setRequestMessage(dtos.getRequestMessage());
		serRequest.getRequestType().setTypeOfRequest(3);
		
		String xml = coreService.marshal(com.primesoftinc.message.customerRegister.ServiceRequest.class, serRequest);
		rabbitTemplate.convertAndSend(RabbitMQConfig.Exchange,RabbitMQConfig.ROUTINGKEYSENDER,xml);
			
		}
		return map;
	}


}
