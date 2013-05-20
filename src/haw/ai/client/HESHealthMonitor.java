package haw.ai.client;

import java.rmi.Remote;
import java.rmi.RemoteException;
import java.util.Date;
import java.util.Map;

public interface HESHealthMonitor extends Remote {
	public void iAmAlive(String hesInstanceName) throws RemoteException;
	public Map<String, Date> liveHESInstances() throws RemoteException;
}
