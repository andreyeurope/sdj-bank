package bank.services;

import java.rmi.RemoteException;
import java.util.Currency;
import java.util.HashMap;

import bank.models.Money;
import bank.models.RemoteBranch;

public class HQ implements RemoteHQ {
	private HashMap<Integer, RemoteBranch> branches;

	public HQ(HashMap<Integer, RemoteBranch> branches){
		this.branches = branches;
	}
	@Override
	public Money getExchangeRate(Money money, Currency currencyTo) throws RemoteException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public RemoteBranch getBranch(int registrationNumber) throws RemoteException {
		return branches.get(registrationNumber);
	}

}
