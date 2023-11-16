package com.crud.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.crud.model.AccountStatement;

@Repository
public interface AccountSatementRepo extends JpaRepository<AccountStatement, Integer>{

	@Query("Select e from AccountStatement e where e.myAccount.accountNumber=:accountNumber")
	List<AccountStatement>findByAccountnumber(long accountNumber);
}
