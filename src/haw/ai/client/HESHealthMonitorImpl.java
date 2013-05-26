package haw.ai.client;

import haw.ai.client.gui.dashboard.Dashboard;
import haw.ai.common.Log;

import java.rmi.RemoteException;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

public class HESHealthMonitorImpl extends Thread implements HESHealthMonitor {

	private static final int CHECK_SERVER_HEALTH_EVERY_N_MILLISECONDS = 2000;
	private static final int SERVER_HEALTH_GRACE_PERIOD = 4000;
	private Map<RemoteHESInstance, Date> liveHESInstances;
	private Dispatcher dispatcher;
	private Dashboard dashboard;

	public HESHealthMonitorImpl(Dispatcher dispatcher, Dashboard dashboard) {
		this.liveHESInstances = new HashMap<RemoteHESInstance, Date>();
		this.dispatcher = dispatcher;
		this.dashboard = dashboard;
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

	@Override
	public Map<RemoteHESInstance, Date> getLiveHESInstances() {
		return new HashMap<RemoteHESInstance, Date>(liveHESInstances);
	}

	@Override
	public void iAmAlive(String hesInstanceName, String hesInstanceHostame,
			Integer hesInstanceRegistryPort) throws RemoteException {
		Log.log(HESHealthMonitorImpl.class.getName(),
				"I am Alive - Nachricht erhalten von " + hesInstanceName);
		RemoteHESInstance hesInstance = new RemoteHESInstance(hesInstanceName,
				hesInstanceHostame, hesInstanceRegistryPort);
		this.liveHESInstances.put(hesInstance,
				new Date(System.currentTimeMillis()));
		this.dispatcher.iAmAlive(hesInstance);
		this.dashboard.showNewInstanceState(hesInstanceName, true);
	}

	public void checkIfNotAlive() {
		Log.log(HESHealthMonitorImpl.class.getName(),
				"MONITOR CHECK IF NOT ALIVE");
		Map<RemoteHESInstance, Date> actuallyLiveHESInstances = getLiveHESInstances();
		for (RemoteHESInstance hesInstance : actuallyLiveHESInstances.keySet()) {
			if (((new Date(System.currentTimeMillis()).getTime() - actuallyLiveHESInstances
					.get(hesInstance).getTime()) / 1000) > SERVER_HEALTH_GRACE_PERIOD) {
				actuallyLiveHESInstances.remove(hesInstance);
				this.dispatcher.iAmNotAlive(hesInstance);
				this.dashboard.changeInstanceState(
						hesInstance.getHesInstanceName(), false);
			}
		}
		this.liveHESInstances = actuallyLiveHESInstances;
	}
}
