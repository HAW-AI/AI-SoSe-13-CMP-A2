package haw.ai.komponenten.kunden_komponente;

import haw.ai.komponenten.common.KomponentenFassade;

public class KundenFassade implements KomponentenFassade {
	
	public Kunde erstelleKunden(String name, String adresse) {
		return KundenRepository.erstelleKunde(name, adresse);
	}

	public Kunde findeKunden(String name) {
		Kunde kunde = KundenRepository.findeKunden(name);
		return kunde;
	}
}
