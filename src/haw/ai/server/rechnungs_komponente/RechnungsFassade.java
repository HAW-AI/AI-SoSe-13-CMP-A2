package haw.ai.server.rechnungs_komponente;

import java.util.Date;

import haw.ai.server.bestell_komponente.Auftrag;
import haw.ai.server.common.KomponentenFassade;

public class RechnungsFassade implements KomponentenFassade {

	public static Rechnung erstelleRechnung(Date rechnungsDatum, Auftrag auftrag) {
		return RechnungsRepository.erstelleRechnung(rechnungsDatum, false,
				auftrag);
	}

	public static Zahlungseingang erstelleZahlungseingang(Rechnung rechnung,
			Date eingangsDatum, int betrag) {
		return RechnungsRepository.erstelleZahlungseingang(rechnung,
				eingangsDatum, betrag);
	}

	/**
	 * Rechnung wird vom Buchhalter nur dann als bezahlt markiert, wenn sie
	 * bezahlt ist (Summe aller Zahlungseing�nge fuer diese Rechnung entspricht
	 * dem Rechnungsbetrag). Ist sie nicht bezahlt, passiert nichts (Rechnung
	 * wird nicht als bezahlt markiert). Wichtig: Die Methode wird nie direkt
	 * aufgerufen, der Methodenaufruf ist bereits in der Methode
	 * "auftragAbschliessen" in der Bestellfassade integriert!
	 * 
	 * @param rechnung
	 */
	public static void rechnungBezahltWennZahlungAusreichend(Rechnung rechnung) {
		RechnungsBusinessLogik.rechnungBezahltWennZahlungAusreichend(rechnung);
	}

	public static void save(Rechnung rechnung) {
		RechnungsRepository.save(rechnung);
	}

	public static void save(Zahlungseingang zahlungseingang) {
		RechnungsRepository.save(zahlungseingang);
	}

}
