package com.crud.repository;

import java.util.List;


import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.crud.model.ChequeBookRequest;

public interface ChequeBookRequestRepo extends JpaRepository<ChequeBookRequest, Integer>{

	@Query(value="Select * from cheque_book_request where service_request_id=:service_request_id",nativeQuery=true)
	List<ChequeBookRequest> findbyserviceid(int service_request_id);


	@Query(value="SELECT * FROM cheque_book_request WHERE account_number = :accountNumber  ",nativeQuery=true)
	List<ChequeBookRequest> findbyaccountnumber(long accountNumber);

	
	

}
