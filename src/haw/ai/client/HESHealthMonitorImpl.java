package haw.ai.client;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;

public class HESHealthMonitorImpl implements HESHealthMonitor {
	private Map<String, Date> liveHESInstances;

	public HESHealthMonitorImpl() {
		this.liveHESInstances = new HashMap<String, Date>();
	}

	@Override
	public void iAmAlive(String hesInstanceName) {
		this.liveHESInstances.put(hesInstanceName,
				new Date(System.currentTimeMillis()));
	}

	@Override
	public Map<String, Date> liveHESInstances() {
		return new HashMap<String, Date>(liveHESInstances);
	}
}
