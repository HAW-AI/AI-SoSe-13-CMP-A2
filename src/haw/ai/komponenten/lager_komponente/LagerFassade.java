package haw.ai.komponenten.lager_komponente;

import java.util.Date;
import java.util.Map;

import haw.ai.komponenten.common.KomponentenFassade;

public class LagerFassade implements KomponentenFassade {

	public static Produkt erstelleProdukt(String name, int lagerbestand) {
		return LagerRepository.erstelleProdukt(name, lagerbestand);
	}

	/**
	 * Wird vom Lageristen aufgrufen, wenn Waren das Lager verlassen.
	 * 
	 * @param produkt
	 * @param datum
	 * @param menge
	 * @return
	 */
	public static Warenausgangsmeldung erstelleWarenausgangsmeldung(
			Produkt produkt, Date datum, int menge) {
		return LagerRepository.erstelleWarenausgangsmeldung(produkt, datum,
				menge);
	}

	/**
	 * gibt momentan immer true zurueck, da Einkaufskomponente nicht
	 * implementiert ist
	 * 
	 * @return true
	 */
	public static boolean pruefeLagerbestand(Map<Produkt, Integer> produkte) {
		return LagerBusinessLogik.pruefeLagerbestand(produkte);
	}

	public static void save(Produkt produkt) {
		LagerRepository.save(produkt);
	}

	public static void save(Warenausgangsmeldung warenausgangsmeldung) {
		LagerRepository.save(warenausgangsmeldung);
	}

	public static Produkt findeProdukt(Integer produktId) {
		return LagerRepository.findeProdukt(produktId);
	}

	public static Produkt findeProdukt(String produktName) {
		return LagerRepository.findeProdukt(produktName);
	}

}
