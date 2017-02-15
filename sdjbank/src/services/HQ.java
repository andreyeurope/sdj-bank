package services;

import java.io.IOException;
import java.math.BigDecimal;
import java.rmi.Naming;
import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;
import java.util.Currency;
import java.util.Hashtable;

import org.apache.http.client.HttpClient;
import org.apache.http.client.ResponseHandler;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.BasicResponseHandler;
import org.apache.http.impl.client.DefaultHttpClient;

import bank.Money;
import bank.RemoteBranch;

public class HQ extends UnicastRemoteObject implements RemoteHQ 

{
   
   private Hashtable<Integer, RemoteBranch> branches;

   public HQ() throws RemoteException
   {
      super();
      branches = new Hashtable<Integer, RemoteBranch>();
      // TODO Auto-generated constructor stub
   }

   /**
    * 
    */
   private static final long serialVersionUID = 1L;

   @Override
   public Money getExchangeRate(Money money, Currency currencyTo)
   {
      BigDecimal moneyAmount = money.getAmount();
      String currTo = currencyTo.getCurrencyCode();
      String currFrom = money.getCurrency().getCurrencyCode();
      
      HttpClient httpclient = new DefaultHttpClient();
      HttpGet httpGet = new HttpGet("http://quote.yahoo.com/d/quotes.csv?s=" + currFrom + currTo + "=X&f=l1&e=.csv");
      ResponseHandler<String> responseHandler = new BasicResponseHandler();
      String responseBody = "1";
	try {
		responseBody = httpclient.execute(httpGet, responseHandler);
	} catch (IOException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	}
      httpclient.getConnectionManager().shutdown();
      float exchangeRate = Float.parseFloat(responseBody);
      
      return new Money(currencyTo.getCurrencyCode(), new BigDecimal(moneyAmount.doubleValue() / exchangeRate).intValue());
   }

   @Override
   public RemoteBranch getBranch(int registrationNumber)
   {
      return branches.get(new Integer(registrationNumber));
   }
   
   public static void main(String[] args){

      try {

          Naming.rebind("//localhost/", new HQ());
          System.err.println("Server ready");

      } catch (Exception e) {

          System.err.println("Server exception: " + e.toString());
          e.printStackTrace();

      }
   

}
}