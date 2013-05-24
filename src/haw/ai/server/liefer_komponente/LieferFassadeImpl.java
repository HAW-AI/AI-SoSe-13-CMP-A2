package haw.ai.server.liefer_komponente;

import haw.ai.server.HESServerImpl;
import haw.ai.server.bestell_komponente.Auftrag;

import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;
import java.util.Date;

public class LieferFassadeImpl extends UnicastRemoteObject implements LieferFassade {
	
	private static final long serialVersionUID = -691970946398847320L;
	private HESServerImpl hesServer;
	
	private LieferFassadeImpl(HESServerImpl hesServer) throws RemoteException {
		this.hesServer = hesServer;
	}

	public Lieferung erstelleLieferung(Auftrag auftrag) {
		return LieferRepository.erstelleLieferung(auftrag);
	}


	public Transportauftrag erstelleTransportauftrag(
			Lieferung lieferung, Date ausgangsDatum, boolean lieferungErfolgt,
			Date lieferDatum, String transportDienstleister) {
		return LieferRepository.erstelleTransportauftrag(lieferung,
				ausgangsDatum, lieferungErfolgt, lieferDatum,
				transportDienstleister);
	}

	public void markiereTransportErfolgt(
			Transportauftrag transportAuftrag) {
		if (transportAuftrag != null) {
			LieferBusinessLogik.lieferungErfolgt(transportAuftrag);
		}
	}

	public void save(Lieferung lieferung) {
		LieferRepository.save(lieferung);
	}

	public void save(Transportauftrag transportauftrag) {
		LieferRepository.save(transportauftrag);
	}
	
	public static LieferFassade createLieferFassade(HESServerImpl hesServer) throws RemoteException {
		LieferFassade lieferFassade = new LieferFassadeImpl(hesServer);
		hesServer.getServerRegistry().rebind(LieferFassade.class.getSimpleName(), lieferFassade);
		return lieferFassade;
	}
}
