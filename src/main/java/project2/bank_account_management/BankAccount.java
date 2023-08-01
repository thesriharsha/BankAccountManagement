package project2.bank_account_management;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

class InsufficientBalanceException extends Exception{

	public InsufficientBalanceException() {
		super("Insufficient Balance for withdrawl ");
	}
}


@Entity
@Table(name="bankaccounts")
public class BankAccount {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int acNumber;
	
	private String name;
	private int balance;
	
private static int minBalance=1000;
	
	public BankAccount(int acNumber, String name, int balance) throws Exception {
		
		this.acNumber = acNumber;
		this.name = name;
		if (balance>=minBalance)
			this.balance = balance;
		else {
			throw new Exception("Balance is less than minimum balance");
		}
	}
	
	public BankAccount()
	{
		
	}
	
	
	//methods
	void deposit(int amount)
	{
		this.balance+=amount;
	}
	
	void withdraw(int amount) throws InsufficientBalanceException
	{
		if(amount<= this.balance-minBalance)
		{
			this.balance -= amount;
			System.out.println("Amount withdrawn successfully");
			
		}
		else {
			throw new InsufficientBalanceException();
		}
	}

	
	// Getters and Setters
	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public int getBalance() {
		return balance;
	}

	public void setBalance(int balance) {
		this.balance = balance;
	}

	public int getAcNumber() {
		return acNumber;
	}

	//static method
	public static int getMinBalance() {
		return minBalance;
	}
	public static void setMinBalance(int minBalance) {
		BankAccount.minBalance = minBalance;
	}
	
	
}
