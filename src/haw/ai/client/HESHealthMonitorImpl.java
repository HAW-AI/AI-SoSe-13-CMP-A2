package haw.ai.client;

import haw.ai.client.gui.dashboard.Dashboard;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;

public class HESHealthMonitorImpl implements HESHealthMonitor {
	
	private Map<String, Date> liveHESInstances;
	private Dispatcher dispatcher;
	private Dashboard dashboard;

	public HESHealthMonitorImpl(Dispatcher dispatcher, Dashboard dashboard) {
		this.liveHESInstances = new HashMap<String, Date>();
		this.dispatcher = dispatcher;
		this.dashboard = dashboard;
	}

	@Override
	public void iAmAlive(String hesInstanceName) {
		this.liveHESInstances.put(hesInstanceName,
				new Date(System.currentTimeMillis()));
		this.dispatcher.iAmAlive(hesInstanceName);
		this.dashboard.showInstanceState(hesInstanceName, true);	
	}

	@Override
	public Map<String, Date> liveHESInstances() {
		return new HashMap<String, Date>(liveHESInstances);
	}
}
