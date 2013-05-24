package haw.ai.server.alive;

import haw.ai.client.HESHealthMonitor;

import java.rmi.AccessException;
import java.rmi.NotBoundException;
import java.rmi.RemoteException;
import java.rmi.registry.Registry;

public class AliveBeacon extends Thread {

	private String hesInstanceUUID;
	private Registry registry;

	public AliveBeacon(Registry registry, String hesInstanceUUID) {
		this.hesInstanceUUID = hesInstanceUUID;
		this.registry = registry;
	}

	public void run() {
		try {
			while (!interrupted()) {
				((HESHealthMonitor) registry.lookup(HESHealthMonitor.class.getName())).iAmAlive(hesInstanceUUID);
				sleep(2000);
			}
		} catch (NotBoundException e) {
		} catch (InterruptedException e) {
		} catch (AccessException e) {
		} catch (RemoteException e) {
		}
	}

}
