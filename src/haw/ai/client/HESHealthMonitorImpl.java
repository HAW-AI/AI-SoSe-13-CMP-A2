package haw.ai.client;

import haw.ai.client.gui.dashboard.Dashboard;

import java.rmi.RemoteException;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

public class HESHealthMonitorImpl implements HESHealthMonitor {
	
	private Map<RemoteHESInstance, Date> liveHESInstances;
	private Dispatcher dispatcher;
	private Dashboard dashboard;

	public HESHealthMonitorImpl(Dispatcher dispatcher, Dashboard dashboard) {
		this.liveHESInstances = new HashMap<RemoteHESInstance, Date>();
		this.dispatcher = dispatcher;
		this.dashboard = dashboard;
	}

	@Override
	public Map<RemoteHESInstance, Date> liveHESInstances() {
		return new HashMap<RemoteHESInstance, Date>(liveHESInstances);
	}

	@Override
	public void iAmAlive(String hesInstanceName, String hesInstanceHostame,
			Integer hesInstanceRegistryPort) throws RemoteException {
		RemoteHESInstance hesInstance = new RemoteHESInstance(hesInstanceName, hesInstanceHostame, hesInstanceRegistryPort);
		this.liveHESInstances.put(hesInstance,
				new Date(System.currentTimeMillis()));
		this.dispatcher.iAmAlive(hesInstance);
		this.dashboard.showNewInstanceState(hesInstanceName, true);	
	}
	
	public void checkIfNotAlive() {
		// TODO Instanzen melden sich nicht mehr. In neuem Thread starten (?)
	}
}
