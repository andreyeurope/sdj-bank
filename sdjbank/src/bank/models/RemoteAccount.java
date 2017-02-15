package bank.models;

import java.rmi.Remote;
import java.rmi.RemoteException;

public interface RemoteAccount extends Remote {
	Customer getOwner() throws RemoteException;
	int getRegistrationNumber() throws RemoteException;
	int getAccountNumber() throws RemoteException;
	boolean addTransaction(Transaction transaction) throws RemoteException;

}
