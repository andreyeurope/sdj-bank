package bank.branch;

import java.rmi.NotBoundException;
import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;
import java.rmi.server.UnicastRemoteObject;
import java.util.HashMap;

import bank.models.Account;
import bank.models.Branch;
import bank.models.Customer;
import bank.models.HQ;
import bank.models.Transaction;

public class RemoteBranch extends UnicastRemoteObject implements Branch{
	/**
	 * 
	 */
	private static final long serialVersionUID = 8963314576366086039L;
	private String registrationNumber;
	private HashMap<String, Account> accounts;
	private HashMap<String, Customer> customers;
	private HQ hq;

	public RemoteBranch(String registrationNumber, HQ hq)
					throws RemoteException, NotBoundException{
		this.registrationNumber = registrationNumber;
		accounts = new HashMap<>();
		customers = new HashMap<>();
		
		// get the remoteHQ
		Registry registry = LocateRegistry.getRegistry(1099);
		registry.rebind(this.getRegistrationNumber(), this);
		hq = (HQ) registry.lookup("HQ");
		hq.addBranch(this);
	}
	
	public String getRegistrationNumber(){
		return registrationNumber;
	}
	
	public void addCustomer(Customer c){
		
	}
	
//	@Override
//	public void deposit(RemoteAccount account, Money money) throws RemoteException {
//		Transaction deposite = new Transaction(account, Transaction.DEPOSITE, money);
//		// we except no exception for deposit action
//		account.addTransaction(deposite);
//	}
//
//	@Override
//	public void withdraw(RemoteAccount account, Money money) throws RemoteException {
//		Transaction withdraw = new Transaction(account, Transaction.WITHDRAW, money);
//		if(!account.addTransaction(withdraw)){
//			throw new IllegalStateException("There are not enough money");
//		}
//		
//	}
//
//	@Override
//	public void transfer(Account accountFrom, String registrationNumberTo, String accountNumberTo, Money money)
//			throws Exception {
//		if(registrationNumberTo.equals(registrationNumber)){
//			// get the account to 
//			RemoteAccount accountTo = getAccount(accountNumberTo);
//			Transaction transfer = new Transaction(accountFrom, accountTo,Transaction.TRANSFER, money);
//			// this one should withdraw money from the account
//			if(accountFrom.addTransaction(transfer)){
//				// only keep going when there is money
//				// this one should deposit money in the other account
//				accountTo.addTransaction(transfer);
//			}else{
//				// if there is no money
//				throw new Exception("Not enough money in the account");
//			}
//			return;
//		}
//		
//		RemoteBranch otherBranch = hq.getBranch(registrationNumberTo);
//		if(otherBranch == null){
//			throw new Exception("Illegal registrationNumber");
//		}
//		
//		RemoteAccount accountTo = otherBranch.getAccount(accountNumberTo);
//		if(accountTo == null){
//			throw new Exception("Account number to does not exist");
//		}
//		
//		Transaction transfer = new Transaction(accountFrom, accountTo, Transaction.TRANSFER, money);
//		if(accountFrom.addTransaction(transfer)){
//			// if there is money
//			accountTo.addTransaction(transfer);
//		}else{
//			throw new Exception("Not enough money in the account");
//		}
//	}	
//	

	@Override
	public boolean addTransaction(Transaction trans) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public Account getAccount(String accountNumber) {
		// TODO Auto-generated method stub
		return null;
	}

}
