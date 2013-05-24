package haw.ai.server.liefer_komponente;

import haw.ai.server.bestell_komponente.Auftrag;

import java.rmi.RemoteException;
import java.rmi.registry.Registry;
import java.rmi.server.UnicastRemoteObject;
import java.util.Date;

public class LieferFassadeImpl extends UnicastRemoteObject implements LieferFassade {
	
	private static final long serialVersionUID = -691970946398847320L;
	private String instanceName;
	private Registry registry;
	public final static String bindFassadenName = LieferFassade.class.getName();
	
	private LieferFassadeImpl(Registry registry, String instanceName) throws RemoteException {
		this.instanceName = instanceName;
		this.registry = registry;
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
	
	public static LieferFassade createLieferFassade(Registry registry, String instanceName) throws RemoteException {
		LieferFassade lieferFassade = new LieferFassadeImpl(registry, instanceName);
		registry.rebind(lieferFassade.bindName(), lieferFassade);
		return lieferFassade;
	}

	@Override
	public String bindName() {
		return instanceName + " - " + bindFassadenName;
	}

}
