package haw.ai.komponenten.bestell_komponente;

import haw.ai.komponenten.kunden_komponente.Kunde;
import haw.ai.komponenten.lager_komponente.Produkt;

import java.util.Date;
import java.util.Map;

public class BestellFassade {

	public static Angebot erstelleAngebot(Kunde kunde,
			Map<Produkt, Integer> produkte, Date gueltigAb, Date gueltigBis,
			int gesamtPreis) {
		return BestellRepository.erstelleAngebot(kunde, produkte, gueltigAb,
				gueltigBis, gesamtPreis);
	}

	public static Auftrag erstelleAuftrag(Angebot angebot, Date beauftragtAm) {
		Auftrag auftrag = BestellRepository.erstelleAuftrag(angebot,
				beauftragtAm);
		BestellBusinessLogik.bearbeiteAuftrag(auftrag);
		return auftrag;
	}

	public static void auftragAbschliessen(Auftrag auftrag) {
		if (auftrag != null) {
			BestellBusinessLogik.auftragAbschliessen(auftrag);
		}
	}

	public static void save(Auftrag auftrag) {
		if (auftrag != null) {
			BestellRepository.save(auftrag);
		}
	}

	public static void save(Angebot angebot) {
		if (angebot != null) {
			BestellRepository.save(angebot);
		}
	}
}