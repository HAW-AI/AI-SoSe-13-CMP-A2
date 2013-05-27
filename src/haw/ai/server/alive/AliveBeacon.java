package haw.ai.server.alive;

import haw.ai.client.HESHealthMonitor;
import haw.ai.common.Log;
import haw.ai.server.HESServerImpl;

import java.rmi.AccessException;
import java.rmi.NotBoundException;
import java.rmi.RemoteException;

public class AliveBeacon extends Thread {
	private HESServerImpl server;
	private static final Integer BROADCAST_EVERY_N_MILLISECONDS = 2000;

	public AliveBeacon(HESServerImpl server) {
		this.server = server;
		Log.log(AliveBeacon.class.getName(), server.getInstanceName(), "AliveBeacon initialized");
	}

	public void run() {
		try {
			Log.log(AliveBeacon.class.getName(), server.getInstanceName(), "AliveBeacon started sending.");
			while (!interrupted()) {
				((HESHealthMonitor) server.getClientRegistry().lookup(HESHealthMonitor.class.getSimpleName())).iAmAlive(server.getInstanceName(), server.getServerRegistryHostname(), server.getServerRegistryPort());
				sleep(BROADCAST_EVERY_N_MILLISECONDS);
			}
			Log.log(AliveBeacon.class.getName(), server.getInstanceName(), "AliveBeacon was interrupted");
		} catch (NotBoundException e) {
			Log.log(AliveBeacon.class.getName(), server.getInstanceName(), "NotBoundException", e.getMessage());
		} catch (InterruptedException e) {
			Log.log(AliveBeacon.class.getName(), server.getInstanceName(), "InterruptedException", e.getMessage());
		} catch (AccessException e) {
			Log.log(AliveBeacon.class.getName(), server.getInstanceName(), "AccessException", e.getMessage());
		} catch (RemoteException e) {
			Log.log(AliveBeacon.class.getName(), server.getInstanceName(), "RemoteException", e.getMessage());
		}
	}

}
