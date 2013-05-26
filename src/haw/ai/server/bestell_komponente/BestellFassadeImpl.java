package haw.ai.server.bestell_komponente;

import haw.ai.common.Log;
import haw.ai.server.HESServerImpl;
import haw.ai.server.kunden_komponente.Kunde;
import haw.ai.server.lager_komponente.Produkt;

import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;
import java.util.Date;
import java.util.Map;

public class BestellFassadeImpl extends UnicastRemoteObject implements BestellFassade {
	private static final long serialVersionUID = -851919772922635440L;
	private HESServerImpl hesServer;
	private BestellBusinessLogik bestellBusinessLogik;

	private BestellFassadeImpl(HESServerImpl hesServer) throws RemoteException {
		this.hesServer = hesServer;
		this.bestellBusinessLogik = new BestellBusinessLogik(hesServer);
	}
	
	public Angebot erstelleAngebot(Kunde kunde,
			Map<Produkt, Integer> produkte, Date gueltigAb, Date gueltigBis,
			int gesamtPreis) {
		Log.log(BestellFassadeImpl.class.getName(), hesServer.getInstanceName(), "erstelleAngebot");
		return BestellRepository.erstelleAngebot(kunde, produkte, gueltigAb,
				gueltigBis, gesamtPreis);
	}

	public Auftrag erstelleAuftrag(Angebot angebot, Date beauftragtAm) {
		Auftrag auftrag = BestellRepository.erstelleAuftrag(angebot,
				beauftragtAm);
		Log.log(BestellFassadeImpl.class.getName(), hesServer.getInstanceName(), "erstelleAuftrag");
		this.bestellBusinessLogik.bearbeiteAuftrag(auftrag);
		return auftrag;
	}

	public void auftragAbschliessen(Auftrag auftrag) {
		if (auftrag != null) {
			Log.log(BestellFassadeImpl.class.getName(), hesServer.getInstanceName(), "auftragAbschliessen");
			this.bestellBusinessLogik.auftragAbschliessen(auftrag);
		}
	}

	public void save(Auftrag auftrag) {
		if (auftrag != null) {
			Log.log(BestellFassadeImpl.class.getName(), hesServer.getInstanceName(), "save", "auftrag");
			BestellRepository.save(auftrag);
		}
	}

	public void save(Angebot angebot) {
		if (angebot != null) {
			Log.log(BestellFassadeImpl.class.getName(), hesServer.getInstanceName(), "save", "angebot");
			BestellRepository.save(angebot);
		}
	}
	
	public static BestellFassade createBestellFassade(HESServerImpl hesServer) throws RemoteException {
		Log.log(BestellFassadeImpl.class.getName(), hesServer.getInstanceName(), "createBestellFassade");
		BestellFassade bestellFassade = new BestellFassadeImpl(hesServer);
		Log.log(BestellFassadeImpl.class.getName(), hesServer.getInstanceName(), "createBestellFassade", "binding bestellfassade in server registry");
		hesServer.getServerRegistry().rebind(BestellFassade.class.getSimpleName(), bestellFassade);
		return bestellFassade;
	}
}
