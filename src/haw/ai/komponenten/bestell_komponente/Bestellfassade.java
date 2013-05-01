package haw.ai.komponenten.bestell_komponente;

import haw.ai.komponenten.kunden_komponente.Kunde;
import haw.ai.komponenten.lager_komponente.Produkt;

import java.util.Date;
import java.util.Map;

public class Bestellfassade {

	public Angebot erstelleAngebot(Kunde kunde, Map<Produkt, Integer> produkte, Date gueltigAb, Date gueltigBis, int gesamtPreis) {
		return BestellRepository.erstelleAngebot(kunde, produkte, gueltigAb, gueltigBis, gesamtPreis);
	}
	
	public Auftrag erstelleAuftrag(Angebot angebot,Date beauftragtAm) {
		return BestellRepository.erstelleAuftrag(angebot, beauftragtAm);
	}
	
}
