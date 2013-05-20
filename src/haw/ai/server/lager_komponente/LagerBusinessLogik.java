package haw.ai.server.lager_komponente;

import java.util.Map;

import haw.ai.server.common.KomponentenBusinessLogik;

public class LagerBusinessLogik implements KomponentenBusinessLogik {

	// gibt true zurück, da momentan Lagerbestand immer ausreichend
	public static boolean pruefeLagerbestand(Map<Produkt, Integer> produkte) {
		return true;
	}

}
