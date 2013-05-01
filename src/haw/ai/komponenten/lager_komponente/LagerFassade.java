package haw.ai.komponenten.lager_komponente;

import java.util.Date;

import haw.ai.komponenten.common.KomponentenFassade;

public class LagerFassade implements KomponentenFassade {
	public static Produkt erstelleProdukt(String name, int lagerbestand) {
		return LagerRepository.erstelleProdukt(name, lagerbestand);
	}
	
	public static Warenausgangsmeldung erstelleWarenausgangsmeldung(Produkt produkt, Date datum, int menge) {
		return LagerRepository.erstelleWarenausgangsmeldung(produkt, datum, menge);
	}
}
