package haw.ai.komponenten.liefer_komponente;

import haw.ai.komponenten.common.KomponentenBusinessLogik;

public class LieferBusinessLogik implements KomponentenBusinessLogik {

	void lieferungErfolgt(Transportauftrag tAuftrag) {
		tAuftrag.setLieferungErfolgt();
	}
	
}
