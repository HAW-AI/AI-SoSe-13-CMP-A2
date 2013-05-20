package haw.ai.server.alive;

import haw.ai.client.HESHealthMonitor;

import java.rmi.NotBoundException;
import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;

public class AliveBeacon extends Thread {

	private String hesInstanceUUID;
	private String registryHost;
	private Integer registryPort;

	public AliveBeacon(String hesInstanceUUID, String registryHost,
			Integer registryPort) {
		this.hesInstanceUUID = hesInstanceUUID;
		this.registryHost = registryHost;
		this.registryPort = registryPort;
	}

	public void run() {
		try {
			Registry registry = LocateRegistry.getRegistry(registryHost, registryPort);
			while (!interrupted()) {
				((HESHealthMonitor) registry.lookup(HESHealthMonitor.class.getName())).iAmAlive(hesInstanceUUID);
				sleep(2000);
			}
		} catch (RemoteException e) {
		} catch (NotBoundException e) {
		} catch (InterruptedException e) {
		}
	}

}
