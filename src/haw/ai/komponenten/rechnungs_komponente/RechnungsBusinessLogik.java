package haw.ai.komponenten.rechnungs_komponente;

public class RechnungsBusinessLogik {

	public static void rechnungBezahlt(Rechnung rechnung) {
		rechnung.setIstBezahlt(true);
	}
	
}
