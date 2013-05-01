package haw.ai.komponenten.liefer_komponente;

import haw.ai.komponenten.common.KomponentenBusinessLogik;

public class LieferBusinessLogik implements KomponentenBusinessLogik {

	public static void lieferungErfolgt(Transportauftrag transportAuftrag) {
		transportAuftrag.setLieferungErfolgt();
	}

}
