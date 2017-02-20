package bank.services;

import java.rmi.AlreadyBoundException;
import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;
import java.rmi.server.UnicastRemoteObject;

public class ServerHQ {
	public static void main(String[] args)throws RemoteException, AlreadyBoundException{
		   
		  
	    	  HQ rhq = new HQ();
	    	  RemoteHQ stub = (RemoteHQ) UnicastRemoteObject.exportObject(rhq, 0);
	    	  Registry reg = LocateRegistry.createRegistry(1099);
	    	  reg.rebind("HQ", stub);

	   

	}
}
