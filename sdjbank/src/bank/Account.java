package bank;

import java.util.ArrayList;

public class Account implements RemoteAccount {
   
   private Customer account;
   private int registrationNumber;
   private int accountNumber;
   private ArrayList<Transaction> transactions;
   
   public Account(Customer account, int registrationNumber, int accountNumber)
   {
      this.account = account;
      this.registrationNumber = registrationNumber;
      this.accountNumber = accountNumber;
      
   }
   
   public Customer getaccount()
   {
      return account;
      
   }
   
   public int getregistrationNumber()
   {
      return registrationNumber;
      
   }
   
   public int getaccountNumber()
   {
      return accountNumber;
   }
   
   public boolean addTransaction(Transaction transaction)
   {
      return transactions.add(transaction);
   }  

}