package haw.ai.server.bestell_komponente;

import haw.ai.server.kunden_komponente.Kunde;
import haw.ai.server.lager_komponente.Produkt;

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

	/**
	 * Auftrag wird vom Buchhalter als abgeschlossen markiert: Erst wird
	 * geprueft, ob Rechnung bezahlt wurde. Wenn ja, wird die Rechnung als
	 * bezahlt markiert. Dann wird geprueft, ob Rechnung als bezahlt markiert
	 * wurde. Wenn ja, wird der Auftrag als abgeschlossen markiert. Ist die
	 * Rechnung nicht bezahlt, passiert nichts (Auftrag wird nicht als
	 * abgeschlossen markiert).
	 */
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
