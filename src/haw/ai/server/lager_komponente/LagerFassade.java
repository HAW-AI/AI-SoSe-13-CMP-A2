package haw.ai.server.lager_komponente;

import haw.ai.server.common.KomponentenFassade;

import java.rmi.Remote;
import java.rmi.RemoteException;
import java.util.Date;
import java.util.Map;

public interface LagerFassade extends KomponentenFassade, Remote {
	public Produkt erstelleProdukt(String name, int lagerbestand) throws RemoteException;
	
	/**
	 * Wird vom Lageristen aufgrufen, wenn Waren das Lager verlassen.
	 * 
	 * @param produkt
	 * @param datum
	 * @param menge
	 * @return
	 */
	public Warenausgangsmeldung erstelleWarenausgangsmeldung(Produkt produkt,
			Date datum, int menge) throws RemoteException;
	
	/**
	 * gibt momentan immer true zurueck, da Einkaufskomponente nicht
	 * implementiert ist
	 * 
	 * @return true
	 */
	public boolean pruefeLagerbestand(Map<Produkt, Integer> produkte) throws RemoteException;
	public void save(Produkt produkt) throws RemoteException;
	public void save(Warenausgangsmeldung warenausgangsmeldung) throws RemoteException;
	public Produkt findeProdukt(Integer produktId) throws RemoteException;
	public Produkt findeProdukt(String produktName) throws RemoteException;
}
