package haw.ai.client;

import haw.ai.client.gui.dashboard.Dashboard;
import haw.ai.common.Log;

import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

public class HESHealthMonitorImpl extends UnicastRemoteObject implements HESHealthMonitor {
	private static final long serialVersionUID = -2242583635367813341L;
	private static final int SERVER_HEALTH_GRACE_PERIOD_IN_SECONDS = 4;
	private Map<RemoteHESInstance, Date> liveHESInstances;
	private Dispatcher dispatcher;
	private transient Dashboard dashboard;

	public HESHealthMonitorImpl(Dispatcher dispatcher, Dashboard dashboard) throws RemoteException {
		this.liveHESInstances = new HashMap<RemoteHESInstance, Date>();
		this.dispatcher = dispatcher;
		this.dashboard = dashboard;
	}

	public Map<RemoteHESInstance, Date> getLiveHESInstances() {
		return new HashMap<RemoteHESInstance, Date>(liveHESInstances);
	}

	public void iAmAlive(String hesInstanceName, String hesInstanceHostame,
			Integer hesInstanceRegistryPort) throws RemoteException {
		Log.log(HESHealthMonitorImpl.class.getName(),
				"I am Alive - Nachricht erhalten von " + hesInstanceName);
		RemoteHESInstance hesInstance = new RemoteHESInstance(hesInstanceName,
				hesInstanceHostame, hesInstanceRegistryPort);
		this.liveHESInstances.put(hesInstance,
				new Date(System.currentTimeMillis()));
		Log.log(HESHealthMonitorImpl.class.getName(), "number of registred HES instances: ", "" + liveHESInstances.size());
		this.dispatcher.iAmAlive(hesInstance);
		this.dashboard.showNewInstanceState(hesInstanceName, true);
	}

	public void checkIfNotAlive() {
		Log.log(HESHealthMonitorImpl.class.getName(),
				"MONITOR CHECK IF NOT ALIVE");
		Map<RemoteHESInstance, Date> actuallyLiveHESInstances = getLiveHESInstances();
		for (RemoteHESInstance hesInstance : actuallyLiveHESInstances.keySet()) {
			if (((new Date(System.currentTimeMillis()).getTime() - actuallyLiveHESInstances
					.get(hesInstance).getTime()) / 1000) > SERVER_HEALTH_GRACE_PERIOD_IN_SECONDS) {
				actuallyLiveHESInstances.remove(hesInstance);
				this.dispatcher.iAmNotAlive(hesInstance);
				this.dashboard.showNewInstanceState(
						hesInstance.getHesInstanceName(), false);
			}
		}
		this.liveHESInstances = actuallyLiveHESInstances;
	}
}
