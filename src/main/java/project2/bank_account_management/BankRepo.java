package project2.bank_account_management;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import jakarta.transaction.Transactional;

public interface BankRepo extends JpaRepository<BankAccount, Integer>{

	@Modifying
	@Transactional
	@Query("update BankAccount set balance = balance+:amount where acNumber=:id")
	public void depositAmount(@Param("id") int id, @Param("amount") int amount);
	
	
	@Query("select balance from BankAccount where acNumber=:id")
	public int getBalance(@Param("id") int id);//, @Param("amount") int amount);
	
	@Modifying
	@Transactional
	@Query("update BankAccount set balance = balance-:amount where acNumber=:id")
	public void withdrawAmount(@Param("id") int id, @Param("amount") int amount);
	
}
