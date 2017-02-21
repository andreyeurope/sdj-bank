package bank.models;

import java.security.Timestamp;

import bank.branch.RemoteAccount;

public class Transaction {
	public static final byte DEPOSITE = 0;
	public static final byte WITHDRAW = 1;
	public static final byte TRANSFER = 2;
	private Timestamp timestamp;
	private RemoteAccount account;
	private RemoteAccount accountTransfer;
	private byte type;
	private Money money;
	
	public Transaction(RemoteAccount account, byte type, Money money){
		this.account = account;
		this.money = money;
		if(type != DEPOSITE && type != WITHDRAW && type != TRANSFER)
			throw new IllegalArgumentException("Illegal type");
	}
	
	public Transaction(RemoteAccount account, RemoteAccount accountTransfer, byte type, Money money){
		this.account = account;
		this.accountTransfer = accountTransfer;
		this.type = type;
		this.money = money;
	}
	
	public Timestamp getTimeStamp(){
		return timestamp;
	}
	
	public RemoteAccount getAccount(){
		return account;
	}
	
	public RemoteAccount getAccountTransfer(){
		return accountTransfer;
	}
	
	public byte getType(){
		return type;
	}
	
	public Money getMoney(){
		return money;
	}
	
}