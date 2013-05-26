package haw.ai.client;

import java.rmi.Remote;
import java.rmi.RemoteException;
import java.util.Date;
import java.util.Map;

public interface HESHealthMonitor extends Remote {
	public void iAmAlive(String hesInstanceName, String hesInstanceHostame, Integer hesInstanceRegistryPort) throws RemoteException;
	public Map<RemoteHESInstance, Date> getLiveHESInstances() throws RemoteException;
}
