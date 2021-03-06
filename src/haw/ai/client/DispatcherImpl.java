package haw.ai.client;

import haw.ai.client.gui.dashboard.Dashboard;
import haw.ai.common.Log;
import haw.ai.server.bestell_komponente.BestellFassade;
import haw.ai.server.kunden_komponente.KundenFassade;
import haw.ai.server.lager_komponente.LagerFassade;
import haw.ai.server.liefer_komponente.LieferFassade;
import haw.ai.server.rechnungs_komponente.RechnungsFassade;

import java.rmi.RemoteException;
import java.rmi.registry.Registry;
import java.util.ArrayList;
import java.util.List;

public class DispatcherImpl implements Dispatcher {

	// Dokumentation siehe Dispatcher-Interface

	private Dashboard dashboard;
	private List<RemoteHESInstance> liveHESInstances;
	private Registry clientRegistry;
	private static int i;

	public DispatcherImpl(Registry clientRegistry) {
		this.clientRegistry = clientRegistry;
		this.liveHESInstances = new ArrayList<RemoteHESInstance>();
		i = 0;
	}

	public void setDashboard(Dashboard dashboard) {
		this.dashboard = dashboard;
	}

	public void iAmAlive(RemoteHESInstance hesInstance) {
		if (!liveHESInstances.contains(hesInstance)) {
			liveHESInstances.add(hesInstance);
		}
		Log.log(DispatcherImpl.class.getName(),
				"I am Alive - Nachricht erhalten von "
						+ hesInstance.getHesInstanceName());
	}

	public void iAmNotAlive(RemoteHESInstance hesInstance) {
		if (liveHESInstances.contains(hesInstance)) {
			liveHESInstances.remove(hesInstance);
		}
		Log.log(DispatcherImpl.class.getName(), "I am not Alive - HES Instanz "
				+ hesInstance.getHesInstanceName() + "wurde geloescht");
	}

	public BestellFassade getBestellFassade() {
		RemoteHESInstance instance = chooseNextInstance();
		if (instance != null) {
			return instance.getBestellFassade();
		} else {
			return null;
		}
	}

	public KundenFassade getKundenFassade() {
		RemoteHESInstance instance = chooseNextInstance();
		if (instance != null) {
			return instance.getKundenFassade();
		} else {
			return null;
		}
	}

	public LagerFassade getLagerFassade() {
		RemoteHESInstance instance = chooseNextInstance();
		if (instance != null) {
			return instance.getLagerFassade();
		} else {
			return null;
		}
	}

	public LieferFassade getLieferFassade() {
		RemoteHESInstance instance = chooseNextInstance();
		if (instance != null) {
			return instance.getLieferFassade();
		} else {
			return null;
		}
	}

	public RechnungsFassade getRechnungsFassade() {
		RemoteHESInstance instance = chooseNextInstance();
		if (instance != null) {
			return instance.getRechnungsFassade();
		} else {
			return null;
		}
	}

	private RemoteHESInstance chooseNextInstance() {
		Log.log(DispatcherImpl.class.getName(), "CHOOSE NEW INSTANCE");
		Log.log(DispatcherImpl.class.getName(), "number of registered instances:", "" + liveHESInstances.size());
		if (!liveHESInstances.isEmpty()) {
			if (i >= liveHESInstances.size()) {
				i = 0;
			}
			RemoteHESInstance instance = liveHESInstances.get(i);
			Log.log(DispatcherImpl.class.getName(), "chose: ", instance.getHesInstanceName());
			i++;
			dashboard.increaseCount(instance.getHesInstanceName());
			return instance;
		} else
			return null;
	}

	public void changeInstanceState(String instanzname, boolean state) {
		Log.log(DispatcherImpl.class.getName(),
				"Aufruf changeInstanceState mit Instanz " + instanzname
						+ " und Status " + state
						+ " wird an ServerController weitergeleitet");
		Log.log(DispatcherImpl.class.getName(), "size of liveinstances: " + liveHESInstances.size());
		for (RemoteHESInstance instance : liveHESInstances) {
			if (instance.getHesInstanceName().equals(instanzname)) {
				Log.log(DispatcherImpl.class.getName(), "name of instance::: " + instance.getHesInstanceName());
				try {
					instance.getServerController().changeInstanceState(state);
				} catch (RemoteException e) {
					Log.log(DispatcherImpl.class.getName(), "changeInstanceState", "couldnt get ServerController");
				}
			}
		}
	}

}
