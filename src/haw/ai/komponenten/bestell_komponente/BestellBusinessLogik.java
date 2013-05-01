package haw.ai.komponenten.bestell_komponente;

import haw.ai.komponenten.common.KomponentenBusinessLogik;

public class BestellBusinessLogik implements KomponentenBusinessLogik {

	public static void auftragAbschliessen(Auftrag auftrag) {
		auftrag.setAuftragAbgeschlossen();
	}
	
}
