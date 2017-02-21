package bank.models;

import java.rmi.Remote;
import java.rmi.RemoteException;

public interface Account extends Remote {

	 Customer getOwner() throws RemoteException;
	 int getAccountNumber() throws RemoteException;
	 Money getBalance() throws RemoteException;
	 String getAccountCurrency() throws RemoteException;
	 boolean addTransaction(Transaction transaction) throws RemoteException;
}
