package haw.ai.komponenten.lager_komponente;

import java.util.Map;

import haw.ai.komponenten.common.KomponentenBusinessLogik;

public class LagerBusinessLogik implements KomponentenBusinessLogik {

	// gibt true zurück, da momentan Lagerbestand immer ausreichend
	public static boolean pruefeLagerbestand(Map<Produkt, Integer> produkte) {
		return true;
	}

}
