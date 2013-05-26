package haw.ai.client;

import haw.ai.client.gui.dashboard.Dashboard;
import haw.ai.common.Log;
import haw.ai.server.bestell_komponente.BestellFassade;
import haw.ai.server.kunden_komponente.KundenFassade;
import haw.ai.server.lager_komponente.LagerFassade;
import haw.ai.server.liefer_komponente.LieferFassade;
import haw.ai.server.rechnungs_komponente.RechnungsFassade;

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
		Log.log(DispatcherImpl.class.getName(),
				"I am Alive - Nachricht erhalten von "
						+ hesInstance.getHesInstanceName());
	}

	@Override
	public void iAmNotAlive(RemoteHESInstance hesInstance) {
		if (liveHESInstances.contains(hesInstance)) {
			liveHESInstances.remove(hesInstance);
		}
		Log.log(DispatcherImpl.class.getName(), "I am not Alive - HES Instanz "
				+ hesInstance.getHesInstanceName() + "wurde geloescht");
	}

	public BestellFassade getBestellFassade() {
		return chooseNextInstance().getBestellFassade();
	}

	public KundenFassade getKundenFassade() {
		return chooseNextInstance().getKundenFassade();
	}

	public LagerFassade getLagerFassade() {
		return chooseNextInstance().getLagerFassade();
	}

	public LieferFassade getLieferFassade() {
		return chooseNextInstance().getLieferFassade();
	}

	public RechnungsFassade getRechnungsFassade() {
		return chooseNextInstance().getRechnungsFassade();
	}

	private RemoteHESInstance chooseNextInstance() {
		Log.log(DispatcherImpl.class.getName(), "CHOOSE NEW INSTANCE");
		if (!liveHESInstances.isEmpty()) {
			if (i >= liveHESInstances.size()) {
				i = 0;
			}
			RemoteHESInstance instance = liveHESInstances.get(i);
			i++;
			return instance;
		} else
			return null;
	}

	@Override
	public void changeInstanceState(String instanzname, boolean state) {
		Log.log(DispatcherImpl.class.getName(),
				"Aufruf changeInstanceState mit Instanz " + instanzname
						+ " und Status " + state
						+ " wird an ServerController weitergeleitet");
		for (RemoteHESInstance instance : liveHESInstances) {
			if (instance.getHesInstanceName().equals(instanzname)) {
				instance.getServerController().changeInstanceState(state);
			}
		}
	}

}
