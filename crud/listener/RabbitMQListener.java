package com.crud.listener;


import javax.xml.bind.JAXBException;


import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;


import com.crud.config.RabbitMQConfig;
import com.crud.model.ChequeBookRequest;
import com.crud.model.CreditDebitCardRequest;
import com.crud.model.LostStolenCard;
import com.crud.repository.ChequeBookRequestRepo;
import com.crud.repository.CreditDebitCardRequestRepo;
import com.crud.repository.LostStolenCardRepo;
import com.primesoftinc.message.customerRegister.ServiceResponse;
import com.primesoftinc.message.service.CoreService;

@Component
public class RabbitMQListener {
	
	@Autowired
	RabbitTemplate template;
	
	@Autowired
	private CoreService coreService;
	
	@Autowired
	private ChequeBookRequestRepo chequeBookRequestRepo;
	
	
	@Autowired
	private CreditDebitCardRequestRepo cardRequestRepo;
	
	
	@Autowired
	private LostStolenCardRepo lostStolenCardRepo;
	
	
	@RabbitListener(queues = RabbitMQConfig.RECIVERQUEUE)
	public void onMessage(String msg) throws JAXBException
	{
		if(msg !=null)
		{
			System.out.println("Receied Msg"+msg);
			ServiceResponse serResponse = (ServiceResponse) coreService.unmarshal(msg, ServiceResponse.class);		
		
			int typeOdRequest = serResponse.getResponse().getServiceReqId();
			 if(typeOdRequest ==1)
			{
				System.out.println("///////"+msg);
			ChequeBookRequest chequeBookRequest= chequeBookRequestRepo.findById(serResponse.getResponse().getRequestId()).orElse(null);
	System.out.println("??????????"+chequeBookRequest.getChequebookRequestId());
			if(chequeBookRequest != null)
			{
				chequeBookRequest.setResponsestatus(serResponse.getResponse().getStatus());
				chequeBookRequest.setProcessid(serResponse.getResponse().getProcessId());
				chequeBookRequest.setResponsemessage(serResponse.getResponse().getResponseMessage());
				chequeBookRequest.setResponsedate(serResponse.getResponse().getResponseDate());
				chequeBookRequestRepo.saveAndFlush(chequeBookRequest);
			}
			}

			 else if(typeOdRequest ==2)
			{
			CreditDebitCardRequest crdDebCardReq = cardRequestRepo.findById(serResponse.getResponse().getRequestId()).orElse(null);
			if(crdDebCardReq != null)
			{
				crdDebCardReq.setResponseStatus(serResponse.getResponse().getStatus());
				crdDebCardReq.setProcessId(serResponse.getResponse().getProcessId());
				crdDebCardReq.setResponseMessage(serResponse.getResponse().getResponseMessage());
				crdDebCardReq.setResponse_date(serResponse.getResponse().getResponseDate());
				crdDebCardReq.setCardNumber(serResponse.getResponse().getCardNumber());
				cardRequestRepo.saveAndFlush(crdDebCardReq);
			}
			}
			else if(typeOdRequest ==3)
			{
				System.out.println("///////////"+msg);
				LostStolenCard lost_stolen_card_request=lostStolenCardRepo.findById(serResponse.getResponse().getRequestId()).orElse(null);
				System.out.println("??????????"+lost_stolen_card_request.getStolen_request_id()+"???????????????");
				if(lost_stolen_card_request != null)
				{
					lost_stolen_card_request.setResponse_status(serResponse.getResponse().getStatus());
					lost_stolen_card_request.setProcess_id(serResponse.getResponse().getProcessId());
					lost_stolen_card_request.setResponse_message(serResponse.getResponse().getResponseMessage());
					lost_stolen_card_request.setResponsedate(serResponse.getResponse().getResponseDate());
					lostStolenCardRepo.saveAndFlush(lost_stolen_card_request);
				}
			}
		
	
	}
}
}
