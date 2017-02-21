package bank.headq;

import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;
import java.rmi.server.UnicastRemoteObject;

import bank.models.HQ;

public class ServerHQ {
	public static void main(String[] args) throws Exception {
		RemoteHQ rhq = new RemoteHQ();
		HQ hq = (HQ)UnicastRemoteObject.exportObject(rhq, 8080);
		Registry registry = LocateRegistry.createRegistry(1099);
		registry.rebind("HQ", hq);
	}
}
