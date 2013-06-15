package haw.ai.server.liefer_komponente;

import haw.ai.common.Log;
import haw.ai.server.HESServerImpl;
import haw.ai.server.bestell_komponente.Auftrag;

import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;
import java.util.Date;

public class LieferFassadeImpl extends UnicastRemoteObject implements LieferFassade {
	
	private static final long serialVersionUID = -691970946398847320L;
	private HESServerImpl hesServer;
	private LieferBusinessLogik lieferBusinessLogik;
	
	private LieferFassadeImpl(HESServerImpl hesServer) throws RemoteException {
		this.hesServer = hesServer;
		this.lieferBusinessLogik = new LieferBusinessLogik(hesServer);
	}

	public Lieferung erstelleLieferung(Auftrag auftrag) {
		return LieferRepository.erstelleLieferung(auftrag);
	}


	public Transportauftrag erstelleTransportauftrag(
			Lieferung lieferung, Date ausgangsDatum, boolean lieferungErfolgt,
			Date lieferDatum, String transportDienstleister) {
		Log.log(LieferFassadeImpl.class.getName(), hesServer.getInstanceName(), "erstelleTransportauftrag");
		Transportauftrag transportauftrag = LieferRepository.erstelleTransportauftrag(lieferung,
				ausgangsDatum, lieferungErfolgt, lieferDatum,
				transportDienstleister);
		lieferBusinessLogik.transportAuftragAnTransportDienstleister(transportauftrag);
		return transportauftrag;
	}

	public void markiereTransportErfolgt(
			Transportauftrag transportAuftrag) {
		if (transportAuftrag != null) {
			Log.log(LieferFassadeImpl.class.getName(), hesServer.getInstanceName(), "markiereTransportErfolgt");
			this.lieferBusinessLogik.lieferungErfolgt(transportAuftrag);
		}
	}

	public void save(Lieferung lieferung) {
		Log.log(LieferFassadeImpl.class.getName(), hesServer.getInstanceName(), "save", "lieferung", "" + lieferung.getId());
		LieferRepository.save(lieferung);
	}

	public void save(Transportauftrag transportauftrag) {
		Log.log(LieferFassadeImpl.class.getName(), hesServer.getInstanceName(), "save", "transportauftrag", "" + transportauftrag.getId());
		LieferRepository.save(transportauftrag);
	}
	
	public static LieferFassade createLieferFassade(HESServerImpl hesServer) throws RemoteException {
		Log.log(LieferFassadeImpl.class.getName(), hesServer.getInstanceName(), "createLieferFassade");
		LieferFassade lieferFassade = new LieferFassadeImpl(hesServer);
		Log.log(LieferFassadeImpl.class.getName(), hesServer.getInstanceName(), "createLieferFassade", "binding in serverregistry");
		hesServer.getServerRegistry().rebind(LieferFassade.class.getSimpleName(), lieferFassade);
		return lieferFassade;
	}
}
