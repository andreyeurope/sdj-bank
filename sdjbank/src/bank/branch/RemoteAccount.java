package bank.branch;

import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;
import java.util.ArrayList;
import java.util.Currency;

import bank.models.Account;
import bank.models.Customer;
import bank.models.Money;
import bank.models.Transaction;

public class RemoteAccount extends UnicastRemoteObject implements Account {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private Customer owner;
	private int accountNumber;
	private ArrayList<Transaction> history;
	private Money money;

	public RemoteAccount(Customer owner, int accountNumber, String curr) throws RemoteException {
		this.owner = owner;
		money = new Money(curr);
		this.accountNumber = accountNumber;

	}

	public Customer getOwner() {
		return owner;

	}

	public int getAccountNumber() {
		return accountNumber;
	}
	
	public Money getBalance(){
		return money;
	}
	
	public String getAccountCurrency(){
		return money.getCurrency().getCurrencyCode();
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
		
		history.add(transaction);
		return true;
	}
}
