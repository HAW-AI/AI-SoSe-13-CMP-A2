package haw.ai.komponenten.kunden_komponente;

import haw.ai.komponenten.common.KomponentenFassade;

public class KundenFassade implements KomponentenFassade {

	public static Kunde erstelleKunden(String name, String adresse) {
		return KundenRepository.erstelleKunde(name, adresse);
	}

	public static Kunde findeKunden(String name) {
		Kunde kunde = KundenRepository.findeKunden(name);
		return kunde;
	}

	public static void save(Kunde kunde) {
		if (kunde != null) {
			KundenRepository.save(kunde);
		}
	}
}
