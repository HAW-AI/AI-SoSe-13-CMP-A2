package haw.ai.server.rechnungs_komponente;

import java.rmi.RemoteException;
import java.rmi.registry.Registry;
import java.rmi.server.UnicastRemoteObject;
import java.util.Date;

import haw.ai.server.bestell_komponente.Auftrag;

public class RechnungsFassadeImpl extends UnicastRemoteObject implements RechnungsFassade {
	public final static String bindFassadenName = RechnungsFassade.class.getName();

	private static final long serialVersionUID = 1L;
	private String instanceName;
	private Registry registry;

	private RechnungsFassadeImpl(Registry registry, String instanceName) throws RemoteException {
		this.instanceName = instanceName;
		this.registry = registry;
	}
	
	public Rechnung erstelleRechnung(Date rechnungsDatum, Auftrag auftrag) {
		return RechnungsRepository.erstelleRechnung(rechnungsDatum, false,
				auftrag);
	}

	public Zahlungseingang erstelleZahlungseingang(Rechnung rechnung,
			Date eingangsDatum, int betrag) {
		return RechnungsRepository.erstelleZahlungseingang(rechnung,
				eingangsDatum, betrag);
	}

	public void rechnungBezahltWennZahlungAusreichend(Rechnung rechnung) {
		RechnungsBusinessLogik.rechnungBezahltWennZahlungAusreichend(rechnung);
	}

	public void save(Rechnung rechnung) {
		RechnungsRepository.save(rechnung);
	}

	public void save(Zahlungseingang zahlungseingang) {
		RechnungsRepository.save(zahlungseingang);
	}
	
	public static RechnungsFassade createRechnungsFassade(Registry registry, String instanceName) throws RemoteException {
		RechnungsFassadeImpl rechnungsFassade = new RechnungsFassadeImpl(registry, instanceName);
		registry.rebind(rechnungsFassade.bindName(), rechnungsFassade);
		return rechnungsFassade;
	}
	
	public String bindName() {
		return instanceName + " - " + bindFassadenName;
	}

}
