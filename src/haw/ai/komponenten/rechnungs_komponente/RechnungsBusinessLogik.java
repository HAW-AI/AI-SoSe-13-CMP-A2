package haw.ai.komponenten.rechnungs_komponente;

import haw.ai.komponenten.bestell_komponente.*;

public class RechnungsBusinessLogik {

	public static void auftragAbschliessen(Auftrag auftrag) {
		auftrag.setAuftragAbgeschlossen();
	}
	
}
