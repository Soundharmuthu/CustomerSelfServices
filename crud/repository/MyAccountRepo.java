package com.crud.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import com.crud.model.MyAccount;
@Repository
public interface MyAccountRepo extends JpaRepository<MyAccount, Long> {

	@Query(value="Select * from my_account  where customer_id=:customerId",nativeQuery=true)
	List<MyAccount> findByCustomerId(int customerId);

	@Query(value="Select * from my_account  where customer_id=:customerId",nativeQuery=true)
	List<MyAccount> findBycustomerId(int customerId);

	@Query(value="Select a.* from my_account a, credit_debitrequest b, lost_stolencard c, cheque_book_request d "
			+ "where b.account_number=a.account_number and c.account_number=d.account_number and a.account_number=:accountNumber",nativeQuery=true)
	List<MyAccount> getbyaccountnumber( @	Param("accountNumber")  long accountNumber);
	
	
}
