package services;

import java.rmi.Remote;
import java.rmi.RemoteException;
import java.util.Currency;

import bank.Money;
import bank.RemoteBranch;

public interface RemoteHQ extends Remote
{

   public  Money getExchangeRate (Money money, Currency currencyTo) throws RemoteException;
   
   public RemoteBranch getBranch (int registrationNumber) throws RemoteException;
}