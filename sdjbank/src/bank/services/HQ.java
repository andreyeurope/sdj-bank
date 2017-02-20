package bank.services;

import java.io.IOException;
import java.math.BigDecimal;
import java.rmi.AlreadyBoundException;
import java.rmi.Naming;
import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;
import java.rmi.server.UnicastRemoteObject;
import java.util.Currency;
import java.util.Hashtable;
import bank.models.Branch;
import bank.models.Money;
import bank.models.RemoteBranch;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

public class HQ extends UnicastRemoteObject implements RemoteHQ

{

	private Hashtable<Integer, RemoteBranch> branches;

	public HQ() throws RemoteException {
		super();
		branches = new Hashtable<Integer, RemoteBranch>();
		// TODO Auto-generated constructor stub
	}

	/**
	* 
	*/
	private static final long serialVersionUID = 1L;

	@Override
	public Money getExchangeRate(Money money, Currency currencyTo) {

		OkHttpClient cl = new OkHttpClient();
		String returnable = "0";
		try{
			Request req = new Request.Builder().url("http://quote.yahoo.com/d/quotes.csv?s=" + money.getCurrency().getCurrencyCode() + currencyTo.getCurrencyCode() + "=X&f=l1&e=.csv").build();
			Response res = cl.newCall(req).execute();
			returnable = res.body().string();
		}catch (IOException e) {
			// TODO: handle exception
		}
		
		
		float exchangeRate = Float.parseFloat(returnable);

		return new Money(currencyTo.getCurrencyCode(),
				new BigDecimal(money.getAmount().doubleValue() * exchangeRate).intValue());
	}

	@Override
	public RemoteBranch getBranch(int registrationNumber) {
		return branches.get(registrationNumber);
	}

	@Override
	public void addBranch(Branch b, int pin) {
		// TODO Auto-generated method stub
		if(pin == 0){
			branches.put((int) Math.random(), b);
		}
		
	}
	
	

}