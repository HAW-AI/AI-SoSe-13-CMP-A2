package haw.ai.server.alive;

import haw.ai.client.HESHealthMonitor;
import haw.ai.server.HESServerImpl;

import java.rmi.AccessException;
import java.rmi.NotBoundException;
import java.rmi.RemoteException;

public class AliveBeacon extends Thread {
	private HESServerImpl server;
	private static final Integer BROADCAST_EVERY_N_MILLISECONDS = 2000;

	public AliveBeacon(HESServerImpl server) {
		this.server = server;
	}

	public void run() {
		try {
			while (!interrupted()) {
				((HESHealthMonitor) server.getClientRegistry().lookup(HESHealthMonitor.class.getName())).iAmAlive(server.getInstanceName(), server.getServerRegistryHostname(), server.getServerRegistryPort());
				sleep(BROADCAST_EVERY_N_MILLISECONDS);
			}
		} catch (NotBoundException e) {
		} catch (InterruptedException e) {
		} catch (AccessException e) {
		} catch (RemoteException e) {
		}
	}

}
