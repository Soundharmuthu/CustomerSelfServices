package com.crud.repository;

import java.util.List;


import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import com.crud.model.LostStolenCard;

@Repository
public interface LostStolenCardRepo extends JpaRepository<LostStolenCard, Integer>{

	@Query(value="select * from lost_stolencard where service_request_id=:service_request_id ",nativeQuery=true)
	List<LostStolenCard> findbyservicerequestid(int service_request_id);

	@Query(value="select * from lost_stolencard where response_status=:status",nativeQuery=true)
	List<LostStolenCard> getrequeststatus(String status);

	@Query(value="select * from lost_stolencard where account_number=:accountNumber",nativeQuery=true)
	List<LostStolenCard> findbyaccountnumber(long accountNumber);

	
}
