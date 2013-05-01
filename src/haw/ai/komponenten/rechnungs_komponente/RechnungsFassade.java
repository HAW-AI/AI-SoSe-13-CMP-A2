package haw.ai.komponenten.rechnungs_komponente;

import java.util.Date;

import haw.ai.komponenten.bestell_komponente.Auftrag;
import haw.ai.komponenten.common.KomponentenFassade;

public class RechnungsFassade implements KomponentenFassade {
	public static Rechnung erstelleRechnung(Date rechnungsDatum, Auftrag auftrag) {
		return RechnungsRepository.erstelleRechnung(rechnungsDatum, false, auftrag);
	}
	
	public static Zahlungseingang erstelleZahlungseingang(Rechnung rechnung, Date eingangsDatum, int betrag) {
		return RechnungsRepository.erstelleZahlungseingang(rechnung, eingangsDatum, betrag);
	}
}
