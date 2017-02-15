package bank.models;

import java.util.ArrayList;

public class Account implements RemoteAccount {

	private Customer owner;
	private int registrationNumber;
	private int accountNumber;
	private ArrayList<Transaction> transactions;
	private Money money;

	public Account(Customer owner, int registrationNumber, int accountNumber) {
		this.owner = owner;
		this.registrationNumber = registrationNumber;
		this.accountNumber = accountNumber;

	}

	public Customer getOwner() {
		return owner;

	}

	public int getRegistrationNumber() {
		return registrationNumber;

	}

	public int getAccountNumber() {
		return accountNumber;
	}

	public boolean addTransaction(Transaction transaction) {
		if(transaction.getType() == Transaction.DEPOSITE){
			money.deposit(transaction.getMoney());
		}else if(transaction.getType() == Transaction.WITHDRAW){
			if(money.checkIfLessThan(transaction.getMoney())){
				// if there are not enough money
				return false;
			}
			money.withdraw(transaction.getMoney());
		}else if(transaction.getType() == Transaction.TRANSFER && this.equals(transaction.getAccount())){
			// if the account is account from
			if(money.checkIfLessThan(transaction.getMoney())){
				// if there are not enough money
				return false;
			}
			money.withdraw(transaction.getMoney());
		}else if(transaction.getType() == Transaction.TRANSFER && this.equals(transaction.getAccountTransfer())){
			// if the account is account to
			money.deposit(transaction.getMoney());
		}else{
			return false;
		}
		
		transactions.add(transaction);
		return true;
	}

}
