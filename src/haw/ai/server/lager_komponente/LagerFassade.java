package haw.ai.server.lager_komponente;

import haw.ai.server.common.KomponentenFassade;

import java.rmi.Remote;
import java.util.Date;
import java.util.Map;

public interface LagerFassade extends KomponentenFassade, Remote {
	public Produkt erstelleProdukt(String name, int lagerbestand);
	
	/**
	 * Wird vom Lageristen aufgrufen, wenn Waren das Lager verlassen.
	 * 
	 * @param produkt
	 * @param datum
	 * @param menge
	 * @return
	 */
	public Warenausgangsmeldung erstelleWarenausgangsmeldung(Produkt produkt,
			Date datum, int menge);
	
	/**
	 * gibt momentan immer true zurueck, da Einkaufskomponente nicht
	 * implementiert ist
	 * 
	 * @return true
	 */
	public boolean pruefeLagerbestand(Map<Produkt, Integer> produkte);
	public void save(Produkt produkt);
	public void save(Warenausgangsmeldung warenausgangsmeldung);
	public Produkt findeProdukt(Integer produktId);
	public Produkt findeProdukt(String produktName);
}
