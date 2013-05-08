package haw.ai.test;

import static org.junit.Assert.*;
import haw.ai.hibernate.HibernateUtil;
import haw.ai.komponenten.bestell_komponente.Angebot;
import haw.ai.komponenten.bestell_komponente.Auftrag;
import haw.ai.komponenten.bestell_komponente.BestellFassade;
import haw.ai.komponenten.common.DateUtil;
import haw.ai.komponenten.kunden_komponente.Kunde;
import haw.ai.komponenten.kunden_komponente.KundenFassade;
import haw.ai.komponenten.lager_komponente.LagerFassade;
import haw.ai.komponenten.lager_komponente.Produkt;
import haw.ai.komponenten.rechnungs_komponente.Rechnung;
import haw.ai.komponenten.rechnungs_komponente.RechnungsFassade;
import haw.ai.komponenten.rechnungs_komponente.Zahlungseingang;

import java.util.HashMap;
import java.util.Map;

import org.junit.Test;

public class RechnungsKomponentenTest {

	@Test
	public void test() {
		Kunde kunde = KundenFassade.erstelleKunden("Jeremy CLarkson",
				"Example Street 51");
		Produkt produkt1 = LagerFassade.erstelleProdukt("Motorbike", 5);
		Map<Produkt, Integer> produkte = new HashMap<Produkt, Integer>();
		produkte.put(produkt1, 1);
		Angebot angebot = BestellFassade.erstelleAngebot(kunde, produkte,
				DateUtil.now(), DateUtil.daysFromNow(30), 50);
		Auftrag auftrag = BestellFassade.erstelleAuftrag(angebot,
				DateUtil.now());

		Rechnung rechnung = RechnungsFassade.erstelleRechnung(DateUtil.now(),
				auftrag);
		assertNotNull(auftrag.getRechnung());
		assertEquals(rechnung, auftrag.getRechnung());

		assertNull(rechnung.getZahlungseingaenge());
		Zahlungseingang zahlung = RechnungsFassade.erstelleZahlungseingang(
				rechnung, DateUtil.now(), 20);
		assertTrue(rechnung.getZahlungseingaenge().contains(zahlung));

		assertFalse(rechnung.isIstBezahlt());
		RechnungsFassade.rechnungBezahltWennZahlungAusreichend(rechnung);
		assertFalse(rechnung.isIstBezahlt());

		Zahlungseingang zahlung2 = RechnungsFassade.erstelleZahlungseingang(
				rechnung, DateUtil.now(), 30);
		RechnungsFassade.rechnungBezahltWennZahlungAusreichend(rechnung);
		assertTrue(rechnung.isIstBezahlt());
		
		HibernateUtil.closeSession();
	}

}
