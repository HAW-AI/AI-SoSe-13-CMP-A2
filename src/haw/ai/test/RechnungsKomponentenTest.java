package haw.ai.test;

import static org.junit.Assert.*;
import haw.ai.server.bestell_komponente.Angebot;
import haw.ai.server.bestell_komponente.Auftrag;
import haw.ai.server.bestell_komponente.BestellFassadeImpl;
import haw.ai.server.common.DateUtil;
import haw.ai.server.kunden_komponente.Kunde;
import haw.ai.server.kunden_komponente.KundenFassadeImpl;
import haw.ai.server.lager_komponente.LagerFassadeImpl;
import haw.ai.server.lager_komponente.Produkt;
import haw.ai.server.persistenz.HibernateUtil;
import haw.ai.server.persistenz.PersistenzService;
import haw.ai.server.rechnungs_komponente.Rechnung;
import haw.ai.server.rechnungs_komponente.RechnungsFassadeImpl;
import haw.ai.server.rechnungs_komponente.Zahlungseingang;

import java.util.HashMap;
import java.util.Map;

import org.junit.Test;

public class RechnungsKomponentenTest {

	@Test
	public void test() {
		Kunde kunde = KundenFassadeImpl.erstelleKunden("Jeremy CLarkson",
				"Example Street 51");
		Produkt produkt1 = LagerFassadeImpl.erstelleProdukt("Motorbike", 5);
		Map<Produkt, Integer> produkte = new HashMap<Produkt, Integer>();
		produkte.put(produkt1, 1);
		Angebot angebot = BestellFassadeImpl.erstelleAngebot(kunde, produkte,
				DateUtil.now(), DateUtil.daysFromNow(30), 50);
		Auftrag auftrag = BestellFassadeImpl.erstelleAuftrag(angebot,
				DateUtil.now());

		Rechnung rechnung = RechnungsFassadeImpl.erstelleRechnung(DateUtil.now(),
				auftrag);
		assertNotNull(auftrag.getRechnung());
		assertEquals(rechnung, auftrag.getRechnung());

		assertNull(rechnung.getZahlungseingaenge());
		Zahlungseingang zahlung = RechnungsFassadeImpl.erstelleZahlungseingang(
				rechnung, DateUtil.now(), 20);
		assertTrue(rechnung.getZahlungseingaenge().contains(zahlung));

		assertFalse(rechnung.isIstBezahlt());
		RechnungsFassadeImpl.rechnungBezahltWennZahlungAusreichend(rechnung);
		assertFalse(rechnung.isIstBezahlt());

		Zahlungseingang zahlung2 = RechnungsFassadeImpl.erstelleZahlungseingang(
				rechnung, DateUtil.now(), 30);
		RechnungsFassadeImpl.rechnungBezahltWennZahlungAusreichend(rechnung);
		assertTrue(rechnung.isIstBezahlt());
		
		PersistenzService.closeSession();
	}

}
