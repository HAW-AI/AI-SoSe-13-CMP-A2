package haw.ai.server.rechnungs_komponente;

import haw.ai.common.Log;
import haw.ai.server.HESServerImpl;
import haw.ai.server.bestell_komponente.Auftrag;

import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;
import java.util.Date;

public class RechnungsFassadeImpl extends UnicastRemoteObject implements RechnungsFassade {
	private static final long serialVersionUID = -4094915660403030235L;
	private HESServerImpl hesServer;

	private RechnungsFassadeImpl(HESServerImpl hesServer) throws RemoteException {
		this.hesServer = hesServer;
	}
	
	public Rechnung erstelleRechnung(Date rechnungsDatum, Auftrag auftrag) {
		Log.log(RechnungsFassadeImpl.class.getName(), hesServer.getInstanceName(), "erstelleRechnung");
		return RechnungsRepository.erstelleRechnung(rechnungsDatum, false,
				auftrag);
	}

	public Zahlungseingang erstelleZahlungseingang(Rechnung rechnung,
			Date eingangsDatum, int betrag) {
		Log.log(RechnungsFassadeImpl.class.getName(), hesServer.getInstanceName(), "erstelleZahlungseingang");
		return RechnungsRepository.erstelleZahlungseingang(rechnung,
				eingangsDatum, betrag);
	}

	public void rechnungBezahltWennZahlungAusreichend(Rechnung rechnung) {
		Log.log(RechnungsFassadeImpl.class.getName(), hesServer.getInstanceName(), "rechnungBezahltWennZahlungAusreichend");
		RechnungsBusinessLogik.rechnungBezahltWennZahlungAusreichend(rechnung);
	}

	public void save(Rechnung rechnung) {
		Log.log(RechnungsFassadeImpl.class.getName(), hesServer.getInstanceName(), "save", "rechnung");
		RechnungsRepository.save(rechnung);
	}

	public void save(Zahlungseingang zahlungseingang) {
		Log.log(RechnungsFassadeImpl.class.getName(), hesServer.getInstanceName(), "save", "zahlungseingang");
		RechnungsRepository.save(zahlungseingang);
	}
	
	public static RechnungsFassade createRechnungsFassade(HESServerImpl hesServer) throws RemoteException {
		Log.log(RechnungsFassadeImpl.class.getName(), hesServer.getInstanceName(), "createRechnungsFassade");
		RechnungsFassadeImpl rechnungsFassade = new RechnungsFassadeImpl(hesServer);
		Log.log(RechnungsFassadeImpl.class.getName(), hesServer.getInstanceName(), "createRechnungsFassade", "binding in serverregistry");
		hesServer.getServerRegistry().rebind(RechnungsFassade.class.getSimpleName(), rechnungsFassade);
		return rechnungsFassade;
	}
}
