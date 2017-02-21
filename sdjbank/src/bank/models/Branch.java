package bank.models;

import java.rmi.Remote;
import java.rmi.RemoteException;


public interface Branch extends Remote {

	boolean addTransaction(Transaction trans) throws RemoteException;

	// void deposit(RemoteAccount account, Money money) throws RemoteException;
	//
	// void withdraw(RemoteAccount account, Money money) throws RemoteException;
	//
	// void transfer(RemoteAccount accountFrom, String registrationNumberTo,
	// String accountNumberTo, Money money) throws RemoteException, Exception;

	Account getAccount(String accountNumber) throws RemoteException;

	String getRegistrationNumber() throws RemoteException;
}
