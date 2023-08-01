package project2.bank_account_management;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;

import io.swagger.v3.oas.annotations.parameters.RequestBody;




@RestController
public class BankController {

	@Autowired 
	private BankRepo bankRepo;
	
	
	//Listing all accounts
	@GetMapping("/list/all/accounts")
	public List<BankAccount> accounts()
	{
		return bankRepo.findAll();
	}
	
	@GetMapping("/deposit")
	public String depositAmount(@RequestParam() int acNumber, @RequestParam() int amount)
	{
		var a = bankRepo.findById(acNumber);
		if(a.isPresent())
		{
			bankRepo.depositAmount(acNumber, amount);
			return "Amount deposited successfully";
		}
		else
			throw new ResponseStatusException(HttpStatus.NOT_FOUND,"Account not found with the given account number");
		
	}
	
	//var bal = bankRepo.findByAcNumber(101).getBalance();
	
	
	@GetMapping("/withdraw")
	public String withdrawAmount(@RequestParam() int acNumber, @RequestParam() int amount)
	{
		var acnt = bankRepo.findById(acNumber);
		if(acnt.isPresent())
		{
		int bal = acnt.get().getBalance();
		//int bal=  bankRepo.getBalance(acNumber);
		if(bal>= amount+BankAccount.getMinBalance())
		{
			bankRepo.withdrawAmount(acNumber, amount);
			return "Amount withdrawn successfully";
		}
		else {
			throw new ResponseStatusException(HttpStatus.BAD_REQUEST,"You don't have enough balance to perform this withdrawl");
		}
		}
		else
			throw new ResponseStatusException(HttpStatus.NOT_FOUND,"Account not found with the given account number");

	}
	
	@GetMapping("/getBalance")
	public int balance(@RequestParam() int acNumber)
	{
		var acnt = bankRepo.findById(acNumber);
		if(acnt.isPresent())
		{
		return acnt.get().getBalance();
		}
		else
			throw new ResponseStatusException(HttpStatus.NOT_FOUND,"Account not found with the given account number");
	}
			
			
	@GetMapping("/getAcntHolderName")
	public String achName(@RequestParam() int acNumber)
	{
		var acnt = bankRepo.findById(acNumber);
		if(acnt.isPresent())
		{
		return acnt.get().getName();
		}
		else
			throw new ResponseStatusException(HttpStatus.NOT_FOUND,"Account not found with the given account number");
	}
	
	// Updating values
	
	//To add a new Bank Account
	@PostMapping("/addAccount")
	public BankAccount addAccount(@org.springframework.web.bind.annotation.RequestBody BankAccount acnt)
	{
		bankRepo.save(acnt);
		return acnt;
	}
}
