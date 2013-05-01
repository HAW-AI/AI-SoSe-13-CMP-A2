package haw.ai.komponenten.rechnungs_komponente;

public class RechnungsBusinessLogik {

	public static void rechnungBezahltWennZahlungAusreichend(Rechnung rechnung) {
		if (rechnung.summeAllerZahlungseingaenge() >= rechnung.getAuftrag()
				.getAngebot().getGesamtPreis()) {
			rechnung.setIstBezahlt(true);
		}
	}

}
