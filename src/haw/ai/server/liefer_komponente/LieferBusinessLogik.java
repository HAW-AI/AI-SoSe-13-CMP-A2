package haw.ai.server.liefer_komponente;

import haw.ai.server.HESServerImpl;
import haw.ai.server.common.KomponentenBusinessLogik;

public class LieferBusinessLogik implements KomponentenBusinessLogik {
	
	private HESServerImpl hesServer;
	private DummyDiensleisterAdapter dummyDiensleisterAdapter;

	public LieferBusinessLogik(HESServerImpl hesServer) {
		this.hesServer = hesServer;
		this.dummyDiensleisterAdapter = new DummyDiensleisterAdapter();
	}
	
	public void transportAuftragAnTransportDienstleister(Transportauftrag transportauftrag) {
		dummyDiensleisterAdapter.sendNewTransportauftrag(transportauftrag);
	}

	public void lieferungErfolgt(Transportauftrag transportAuftrag) {
		transportAuftrag.setLieferungErfolgt();
	}
}
