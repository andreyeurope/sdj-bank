package bank;

import java.rmi.Remote;

public interface RemoteAccount extends Remote {
	public boolean addTransactions(Transaction transaction);
}
