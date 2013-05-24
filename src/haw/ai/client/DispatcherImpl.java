package haw.ai.client;

import haw.ai.server.alive.ServerController;

import java.rmi.registry.Registry;
import java.util.ArrayList;
import java.util.List;

public class DispatcherImpl implements Dispatcher {

	// Dokumentation siehe Dispatcher-Interface
	
	private List<RemoteHESInstance> liveHESInstances;
	private Registry clientRegistry;
	private static int i;

	public DispatcherImpl(Registry clientRegistry) {
		this.clientRegistry = clientRegistry;
		this.liveHESInstances = new ArrayList<RemoteHESInstance>();
		i = 0;
	}

	@Override
	public void iAmAlive(RemoteHESInstance hesInstance) {
		if (!liveHESInstances.contains(hesInstance)) {
			liveHESInstances.add(hesInstance);
		}
	}
	
	@Override
	public void iAmNotAlive(RemoteHESInstance hesInstance) {
		if (liveHESInstances.contains(hesInstance)) {
			liveHESInstances.remove(hesInstance);
		}
	}

	@Override
	public RemoteHESInstance chooseNextInstance() {
		if (!liveHESInstances.isEmpty()) {
			if (i >= liveHESInstances.size()) {
				i = 0;
			}
			RemoteHESInstance instance = liveHESInstances.get(i);
			i++;
			return instance;
		}
		else return null;
	}

	@Override
	public void changeInstanceState(String instanzname, boolean state) {
		for (RemoteHESInstance instance : liveHESInstances) {
			if (instance.getHesInstanceName().equals(instanzname)) {
				instance.getServerController().changeInstanceState(state);
			}
		}
	}

}
