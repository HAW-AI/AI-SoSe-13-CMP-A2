package haw.ai.server.liefer_komponente;

import haw.ai.server.common.KomponentenBusinessLogik;

public class LieferBusinessLogik implements KomponentenBusinessLogik {

	public static void lieferungErfolgt(Transportauftrag transportAuftrag) {
		transportAuftrag.setLieferungErfolgt();
	}

}
