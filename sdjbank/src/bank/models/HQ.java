package bank.models;

import java.rmi.Remote;
import java.rmi.RemoteException;
import java.util.Currency;


public interface HQ extends Remote {

	public Money getExchangeRate(Money money, Currency currencyTo) throws RemoteException;

	public Branch getBranch(String registrationNumber) throws RemoteException;

	public void addBranch(Branch b) throws RemoteException;
}