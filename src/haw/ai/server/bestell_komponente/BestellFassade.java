package haw.ai.server.bestell_komponente;

import haw.ai.server.kunden_komponente.Kunde;
import haw.ai.server.lager_komponente.Produkt;

import java.rmi.Remote;
import java.rmi.RemoteException;
import java.util.Date;
import java.util.Map;

import haw.ai.server.common.KomponentenFassade;

public interface BestellFassade extends KomponentenFassade, Remote {
	
	public Angebot erstelleAngebot(Kunde kunde,
			Map<Produkt, Integer> produkte, Date gueltigAb, Date gueltigBis,
			int gesamtPreis) throws RemoteException;
	public Auftrag erstelleAuftrag(Angebot angebot, Date beauftragtAm) throws RemoteException;
	
	/**
	 * Auftrag wird vom Buchhalter als abgeschlossen markiert: Erst wird
	 * geprueft, ob Rechnung bezahlt wurde. Wenn ja, wird die Rechnung als
	 * bezahlt markiert. Dann wird geprueft, ob Rechnung als bezahlt markiert
	 * wurde. Wenn ja, wird der Auftrag als abgeschlossen markiert. Ist die
	 * Rechnung nicht bezahlt, passiert nichts (Auftrag wird nicht als
	 * abgeschlossen markiert).
	 */
	public void auftragAbschliessen(Auftrag auftrag) throws RemoteException;
	public void save(Auftrag auftrag) throws RemoteException;
	public void save(Angebot angebot) throws RemoteException;
	
}
