package haw.ai.server;

import haw.ai.server.alive.AliveBeacon;
import haw.ai.server.bestell_komponente.BestellFassade;
import haw.ai.server.bestell_komponente.BestellFassadeImpl;
import haw.ai.server.kunden_komponente.KundenFassade;
import haw.ai.server.kunden_komponente.KundenFassadeImpl;
import haw.ai.server.lager_komponente.LagerFassade;
import haw.ai.server.lager_komponente.LagerFassadeImpl;
import haw.ai.server.liefer_komponente.LieferFassade;
import haw.ai.server.liefer_komponente.LieferFassadeImpl;
import haw.ai.server.rechnungs_komponente.RechnungsFassade;
import haw.ai.server.rechnungs_komponente.RechnungsFassadeImpl;

import java.rmi.AccessException;
import java.rmi.NotBoundException;
import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;
import java.util.UUID;

public class HESServerImpl implements HESServer {
	private String instanceName;
	public final static String REGISTRY_HOST = "127.0.0.1";
	public final static Integer REGISTRY_PORT = 1099;
	protected Registry registry;
	private BestellFassade bestellFassade;
	private KundenFassade kundenFassade;
	private LagerFassade lagerFassade;
	private LieferFassade lieferFassade;
	private RechnungsFassade rechnungsFassade;
	protected AliveBeacon aliveBeacon;

	public HESServerImpl() {
		this.setInstanceName(UUID.randomUUID().toString());
	}

	public static HESServer create() {
		HESServerImpl hesServer = new HESServerImpl();
		try {
			hesServer.registry = LocateRegistry.getRegistry(
					hesServer.REGISTRY_HOST, hesServer.REGISTRY_PORT);
		} catch (RemoteException e1) {
		}

		try {
			hesServer.setBestellFassade(BestellFassadeImpl
					.createBestellFassade(hesServer.registry,
							hesServer.getInstanceName()));
			hesServer.setKundenFassade(KundenFassadeImpl.createKundenFassade(
					hesServer.registry, hesServer.getInstanceName()));
			hesServer.setLagerFassade(LagerFassadeImpl.createLagerFassade(
					hesServer.registry, hesServer.getInstanceName()));
			hesServer.setLieferFassade(LieferFassadeImpl.createLieferFassade(
					hesServer.registry, hesServer.getInstanceName()));
			hesServer.setRechnungsFassade(RechnungsFassadeImpl
					.createRechnungsFassade(hesServer.registry,
							hesServer.getInstanceName()));
			hesServer.aliveBeacon = new AliveBeacon(hesServer.registry,
					hesServer.getInstanceName());
			hesServer.aliveBeacon.start();
		} catch (RemoteException e) {
		}
		return hesServer;
	}

	@Override
	public BestellFassade getBestellFassade() {
		// TODO Auto-generated method stub
		return null;
	}

	private void setBestellFassade(BestellFassade bestellFassade) {
		this.bestellFassade = bestellFassade;
	}

	@Override
	public KundenFassade getKundenFassade() {
		KundenFassade result = null;
		try {
			// wir reichen nur das StubObjekt weiter. so brauch nicht das
			// gesamte
			// Kundenfassaden Objekt serialisiert werden.
			result = ((KundenFassade) registry.lookup(this.kundenFassade
					.bindName()));
		} catch (AccessException e) {
		} catch (RemoteException e) {
		} catch (NotBoundException e) {
		}
		return result;
	}

	public void setKundenFassade(KundenFassade kundenFassade) {
		this.kundenFassade = kundenFassade;
	}

	@Override
	public LagerFassade getLagerFassade() {
		LagerFassade result = null;
		try {
			result = ((LagerFassade) registry.lookup(this.lagerFassade
					.bindName()));
		} catch (AccessException e) {
		} catch (RemoteException e) {
		} catch (NotBoundException e) {
		}
		return result;
	}

	private void setLagerFassade(LagerFassade lagerFassade) {
		this.lagerFassade = lagerFassade;
	}

	@Override
	public LieferFassade getLieferFassade() {
		LieferFassade result = null;
		try {
			result = ((LieferFassade) registry.lookup(this.lieferFassade
					.bindName()));
		} catch (AccessException e) {
		} catch (RemoteException e) {
		} catch (NotBoundException e) {
		}
		return result;
	}

	private void setLieferFassade(LieferFassade lieferFassade) {
		this.lieferFassade = lieferFassade;
	}

	@Override
	public RechnungsFassade getRechnungsFassade() {
		RechnungsFassade result = null;
		try {
			result = ((RechnungsFassade) registry.lookup(this.rechnungsFassade
					.bindName()));
		} catch (AccessException e) {
		} catch (RemoteException e) {
		} catch (NotBoundException e) {
		}
		return result;
	}

	private void setRechnungsFassade(RechnungsFassade rechnungsFassade) {
		this.rechnungsFassade = rechnungsFassade;
	}

	public BestellFassade getInstanceLocalBestellFassade() {
		return this.bestellFassade;
	}

	public KundenFassade getInstanceLocalKundenFassade() {
		return this.kundenFassade;
	}

	public LagerFassade getInstanceLocalLagerFassade() {
		return this.lagerFassade;
	}

	public LieferFassade getInstanceLocalLieferFassade() {
		return this.lieferFassade;
	}

	public RechnungsFassade getInstanceLocalRechnungsFassade() {
		return this.rechnungsFassade;
	}

	public String getInstanceName() {
		return instanceName;
	}

	private void setInstanceName(String instanceName) {
		this.instanceName = instanceName;
	}
}
