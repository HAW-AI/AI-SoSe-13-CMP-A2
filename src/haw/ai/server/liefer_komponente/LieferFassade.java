package haw.ai.server.liefer_komponente;

import haw.ai.server.bestell_komponente.Auftrag;
import haw.ai.server.common.KomponentenFassade;

import java.rmi.Remote;
import java.rmi.RemoteException;
import java.util.Date;

public interface LieferFassade extends KomponentenFassade, Remote {
	public Lieferung erstelleLieferung(Auftrag auftrag)  throws RemoteException;
	public Transportauftrag erstelleTransportauftrag(Lieferung lieferung,
			Date ausgangsDatum, boolean lieferungErfolgt, Date lieferDatum,
			String transportDienstleister) throws RemoteException;
	
	/** Transportauftrag wird vom Transportdienstleister als erfolgt markiert 
	 * 
	 * @param transportAuftrag
	 */
	public void markiereTransportErfolgt(Transportauftrag transportAuftrag) throws RemoteException;
	public void save(Lieferung lieferung) throws RemoteException;
	public void save(Transportauftrag transportauftrag) throws RemoteException;
}
