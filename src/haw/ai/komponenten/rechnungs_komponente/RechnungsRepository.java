package haw.ai.komponenten.rechnungs_komponente;

import haw.ai.komponenten.bestell_komponente.Auftrag;
import haw.ai.komponenten.persistenz.PersistenzService;

import java.util.Date;

public class RechnungsRepository {

	public static Rechnung erstelleRechnung(Date rechnungsDatum,
			boolean istBezahlt, Auftrag auftrag) {
		Rechnung rechnung = new Rechnung(rechnungsDatum, istBezahlt, auftrag);
		save(rechnung);
		return rechnung;
	}

	public static Zahlungseingang erstelleZahlungseingang(Rechnung rechnung,
			Date eingangsDatum, int betrag) {
		Zahlungseingang zahlungseingang = new Zahlungseingang(rechnung,
				eingangsDatum, betrag);
		save(zahlungseingang);
		return zahlungseingang;
	}

	public static void save(Rechnung rechnung) {
		if (rechnung != null) {
			PersistenzService.saveEntity(rechnung);
		}
	}

	public static void save(Zahlungseingang zahlungseingang) {
		if (zahlungseingang != null) {
			PersistenzService.saveEntity(zahlungseingang);
		}
	}

}
