package haw.ai.server.bestell_komponente;

import haw.ai.server.HESServerImpl;
import haw.ai.server.kunden_komponente.Kunde;
import haw.ai.server.lager_komponente.Produkt;

import java.rmi.RemoteException;
import java.rmi.registry.Registry;
import java.rmi.server.UnicastRemoteObject;
import java.util.Date;
import java.util.Map;

public class BestellFassadeImpl extends UnicastRemoteObject implements BestellFassade {

	private static final long serialVersionUID = 1L;

	public final static String bindFassadenName = BestellFassade.class.getName();

	private String instanceName;
	private Registry registry;
	private HESServerImpl hesServer;

	private BestellFassadeImpl(Registry registry, String instanceName, HESServerImpl hesServer) throws RemoteException {
		this.instanceName = instanceName;
		this.registry = registry;
	}
	
	public Angebot erstelleAngebot(Kunde kunde,
			Map<Produkt, Integer> produkte, Date gueltigAb, Date gueltigBis,
			int gesamtPreis) {
		return BestellRepository.erstelleAngebot(kunde, produkte, gueltigAb,
				gueltigBis, gesamtPreis);
	}

	public Auftrag erstelleAuftrag(Angebot angebot, Date beauftragtAm) {
		Auftrag auftrag = BestellRepository.erstelleAuftrag(angebot,
				beauftragtAm);
		BestellBusinessLogik.bearbeiteAuftrag(auftrag);
		return auftrag;
	}

	public void auftragAbschliessen(Auftrag auftrag) {
		if (auftrag != null) {
			BestellBusinessLogik.auftragAbschliessen(auftrag);
		}
	}

	public void save(Auftrag auftrag) {
		if (auftrag != null) {
			BestellRepository.save(auftrag);
		}
	}

	public void save(Angebot angebot) {
		if (angebot != null) {
			BestellRepository.save(angebot);
		}
	}
	
	public static BestellFassade createBestellFassade(Registry registry, HESServerImpl hesServer) throws RemoteException {
		BestellFassade bestellFassade = new BestellFassadeImpl(registry, hesServer.getInstanceName());
		registry.rebind(bestellFassade.bindName(), bestellFassade);
		return bestellFassade;
	}
	
	public String getInstanceName() {
		return hesServer.getInstanceName();
	}
	
	public String bindName() {
		return instanceName + " - " + bindFassadenName;
	}

}
