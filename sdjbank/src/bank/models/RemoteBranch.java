package bank.models;

import java.rmi.Remote;
import java.rmi.RemoteException;

public interface RemoteBranch extends Remote {
	void deposit(RemoteAccount account, Money money) throws RemoteException;
	
	void withdraw(RemoteAccount account, Money money) throws RemoteException;
	
	void transfer(RemoteAccount accountFrom, int registrationNumberTo, int accountNumberTo, Money money) throws RemoteException, Exception;

	RemoteAccount getAccount(int accountNumber);
}