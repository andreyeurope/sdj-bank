package bank;

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
}
