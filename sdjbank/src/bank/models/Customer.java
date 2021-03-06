package bank.models;

import java.util.ArrayList;

public class Customer {
	private String name;
	private int cpr;
	private String address;
	private ArrayList<Account> accounts;
	
	public Customer(String name, int cpr, String address) {
		this.name = name;
		this.cpr = cpr;
		this.address = address;
	}
	
	public String getCustomerName(){
		return name;
	}
	
	public int getCPR(){
		return cpr;
	}
	
	public String getAddress(){
		return address;
	}
	
	public ArrayList<Account> getAccounts(){
		return accounts;
	}
	
}
