package bank.headq;

import java.io.IOException;
import java.math.BigDecimal;
import java.rmi.RemoteException;
import java.util.Currency;
import java.util.HashMap;

import bank.models.*;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

public class RemoteHQ implements HQ

{

	private HashMap<String, Branch> branches;

	public RemoteHQ() throws RemoteException {
		super();
		branches = new HashMap<String, Branch>();
	}

	@Override
	public Money getExchangeRate(Money money, Currency currencyTo) {

		OkHttpClient cl = new OkHttpClient();
		String returnable = "0";
		try {
			Request req = new Request.Builder().url("http://quote.yahoo.com/d/quotes.csv?s="
					+ money.getCurrency().getCurrencyCode() + currencyTo.getCurrencyCode() + "=X&f=l1&e=.csv").build();
			Response res = cl.newCall(req).execute();
			returnable = res.body().string();
		} catch (IOException e) {
			System.out.println("Server not responding");
		}

		float exchangeRate = Float.parseFloat(returnable);

		return new Money(currencyTo.getCurrencyCode(),
				new BigDecimal(money.getAmount().doubleValue() * exchangeRate).intValue());
	}

	@Override
	public Branch getBranch(String registrationNumber) {
		return branches.get(registrationNumber);
	}

	@Override
	public void addBranch(Branch b) throws RemoteException {
		branches.put(b.getRegistrationNumber(), b);

	}

}