package haw.ai.server.liefer_komponente;

import haw.ai.server.bestell_komponente.Auftrag;
import haw.ai.server.common.KomponentenFassade;

import java.rmi.Remote;
import java.util.Date;

public interface LieferFassade extends KomponentenFassade, Remote {
	public Lieferung erstelleLieferung(Auftrag auftrag);
	public Transportauftrag erstelleTransportauftrag(Lieferung lieferung,
			Date ausgangsDatum, boolean lieferungErfolgt, Date lieferDatum,
			String transportDienstleister);
	
	/** Transportauftrag wird vom Transportdienstleister als erfolgt markiert 
	 * 
	 * @param transportAuftrag
	 */
	public void markiereTransportErfolgt(Transportauftrag transportAuftrag);
	public void save(Lieferung lieferung);
	public void save(Transportauftrag transportauftrag);
}
