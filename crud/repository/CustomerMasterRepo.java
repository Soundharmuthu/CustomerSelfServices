package com.crud.repository;


import org.springframework.data.jpa.repository.JpaRepository;


import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.crud.model.CustomerMaster;

@Repository
public interface CustomerMasterRepo extends JpaRepository<CustomerMaster, Integer>{

	@Query(value="select * from customer_master where user_name=:userName",nativeQuery = true)
	CustomerMaster findUserName(@Param("userName")String userName);

	@Query(value ="Select* from customer_master where user_name=:userName", nativeQuery =  true)
	CustomerMaster findByUser(@Param("userName") String userName);

	

}
