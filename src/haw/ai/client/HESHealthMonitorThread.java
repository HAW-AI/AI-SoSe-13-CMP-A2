package haw.ai.client;

import java.rmi.RemoteException;
import java.rmi.registry.Registry;

import haw.ai.client.gui.dashboard.Dashboard;

public class HESHealthMonitorThread extends Thread {
	private static final int CHECK_SERVER_HEALTH_EVERY_N_MILLISECONDS = 2000;
	private final HESHealthMonitorImpl hesHealthMonitor;
	
	public HESHealthMonitorThread(Dispatcher dispatcher, Dashboard dashboard, Registry clientRegistry) throws RemoteException {
		this.hesHealthMonitor = new HESHealthMonitorImpl(dispatcher, dashboard);
		clientRegistry.rebind(HESHealthMonitor.class.getSimpleName(), this.hesHealthMonitor);
	}

	public void run() {
		while (!isInterrupted()) {
			try {
				checkIfNotAlive();
				sleep(CHECK_SERVER_HEALTH_EVERY_N_MILLISECONDS);
			} catch (InterruptedException e) {
			}
		}
	}
	
	public void checkIfNotAlive() {
		hesHealthMonitor.checkIfNotAlive();
	}
}
