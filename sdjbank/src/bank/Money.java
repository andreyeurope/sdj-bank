package bank;

import java.math.BigDecimal;
import java.util.Currency;

public class Money {
	private BigDecimal amount;
	private Currency currency;
	
	public Money(String curr) {
		currency = Currency.getInstance(curr);
		amount = new BigDecimal(0);
	}
	
	public Money(String curr, int val){
		currency = Currency.getInstance(curr);
		amount = new BigDecimal(val);
	}
	
	public Money(String curr, String val){
		currency = Currency.getInstance(curr);
		amount = new BigDecimal(val);
	}
	
	public void deposit(Money money){
		amount = amount.add(money.amount);
	}
	
	public boolean checkIfLessThan(Money money){
		return amount.compareTo(money.amount) == -1;
	}
	
	public boolean withdraw(Money money){
		if(checkIfLessThan(money))return false;
		else{
			amount.subtract(money.amount);
			return true;
		}
	}
	
	public BigDecimal getAmount(){
		return amount;
	}
	
	public Currency getCurrency(){
		return currency;
	}
}
