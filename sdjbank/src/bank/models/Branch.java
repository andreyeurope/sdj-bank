package bank.models;

import java.rmi.RemoteException;
import java.util.Hashtable;

import bank.services.RemoteHQ;

public class Branch implements RemoteBranch{
	private int registrationNumber;
	private Hashtable<Integer, RemoteAccount> accounts;
	private Hashtable<Integer, Customer> customers;
	private RemoteHQ hq;

	public Branch(int registrationNumber, Hashtable<Integer, RemoteAccount> accounts, Hashtable<Integer,Customer> customers){
		this.registrationNumber = registrationNumber;
		this.accounts = accounts;
		this.customers = customers;
		
		// get the remoteHQ
	}
	
	@Override
	public void deposit(RemoteAccount account, Money money) throws RemoteException {
		Transaction deposite = new Transaction(account, Transaction.DEPOSITE, money);
		// we except no exception for deposit action
		account.addTransaction(deposite);
	}

	@Override
	public void withdraw(RemoteAccount account, Money money) throws RemoteException {
		Transaction withdraw = new Transaction(account, Transaction.WITHDRAW, money);
		if(!account.addTransaction(withdraw)){
			throw new IllegalStateException("There are not enough money");
		}
		
	}

	@Override
	public void transfer(RemoteAccount accountFrom, int registrationNumberTo, int accountNumberTo, Money money)
			throws Exception {
		if(registrationNumberTo == registrationNumber){
			// get the account to 
			RemoteAccount accountTo = getAccount(accountNumberTo);
			Transaction transfer = new Transaction(accountFrom, accountTo,Transaction.TRANSFER, money);
			// this one should withdraw money from the account
			if(accountFrom.addTransaction(transfer)){
				// only keep going when there is money
				// this one should deposit money in the other account
				accountTo.addTransaction(transfer);
			}else{
				// if there is no money
				throw new Exception("Not enough money in the account");
			}
			return;
		}
		
		RemoteBranch otherBranch = hq.getBranch(registrationNumberTo);
		if(otherBranch == null){
			throw new Exception("Illegal registrationNumber");
		}
		
		RemoteAccount accountTo = otherBranch.getAccount(accountNumberTo);
		if(accountTo == null){
			throw new Exception("Account number to does not exist");
		}
		
		Transaction transfer = new Transaction(accountFrom, accountTo, Transaction.TRANSFER, money);
		if(accountFrom.addTransaction(transfer)){
			// if there is money
			accountTo.addTransaction(transfer);
		}else{
			throw new Exception("Not enough money in the account");
		}
	}	
	
	public RemoteAccount getAccount(int accountNumber){
		return accounts.get(accountNumber);
	}

}
