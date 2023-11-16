package com.crud.repository;

import java.util.List;


import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.crud.model.CreditDebitCardRequest;

public interface CreditDebitCardRequestRepo  extends JpaRepository<CreditDebitCardRequest,Integer>{
	
 @Query(value="Select * from credit_debitrequest where service_request_id=:service_request_id",nativeQuery=true)
	List<CreditDebitCardRequest> findByservicerequestId(int service_request_id);

 @Query(value="Select * from credit_debitrequest where response_status=:status",nativeQuery=true)
List<CreditDebitCardRequest> getrequeststatus(String status);

 @Query(value="Select * from credit_debitrequest where account_number=:accountNumber",nativeQuery=true)
List<CreditDebitCardRequest> findbyaccountnumber(long accountNumber);
 
 

}
