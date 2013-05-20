package haw.ai.server.bestell_komponente;

import haw.ai.server.kunden_komponente.Kunde;
import haw.ai.server.lager_komponente.Produkt;
import haw.ai.server.persistenz.PersistenzService;

import java.util.Date;
import java.util.Map;

public class BestellRepository {

	/**
	 * Diese Methode erstellt ein Angebot fuer die folgenden Parameter und gibt dieses Angeboy zurueck
	 * @param kunde
	 * @param produkte
	 * @param gueltigAb
	 * @param gueltigBis
	 * @param gesamtPreis
	 * @return Gibt einen Kunden oder null zurueck.
	 */
	public static Angebot erstelleAngebot(Kunde kunde,
			Map<Produkt, Integer> produkte, Date gueltigAb, Date gueltigBis,
			int gesamtPreis) {
		Angebot angebot = new Angebot(kunde, produkte, gueltigAb, gueltigBis,
				gesamtPreis);
		save(angebot);
		return angebot;
	}

	public static Auftrag erstelleAuftrag(Angebot angebot, Date beauftragtAm) {
		Auftrag auftrag = new Auftrag(angebot, false, beauftragtAm);
		save(auftrag);
		return auftrag;
	}

	public static void save(Auftrag auftrag) {
		if (auftrag != null) {
			PersistenzService.saveEntity(auftrag);
		}
	}

	public static void save(Angebot angebot) {
		if (angebot != null) {
			PersistenzService.saveEntity(angebot);
		}
	}
}
