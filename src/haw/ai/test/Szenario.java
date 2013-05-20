package haw.ai.test;

import static org.junit.Assert.*;
import haw.ai.hibernate.HibernateUtil;
import haw.ai.komponenten.bestell_komponente.Angebot;
import haw.ai.komponenten.bestell_komponente.Auftrag;
import haw.ai.komponenten.bestell_komponente.BestellFassade;
import haw.ai.komponenten.common.DateUtil;
import haw.ai.komponenten.kunden_komponente.Kunde;
import haw.ai.komponenten.kunden_komponente.KundenFassadeImpl;
import haw.ai.komponenten.lager_komponente.LagerFassade;
import haw.ai.komponenten.lager_komponente.Produkt;
import haw.ai.komponenten.liefer_komponente.LieferFassade;
import haw.ai.komponenten.persistenz.PersistenzService;
import haw.ai.komponenten.rechnungs_komponente.RechnungsFassade;

import java.util.HashMap;
import java.util.Map;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.junit.Test;

public class Szenario {

	@Test
	public void szenarioTest() {
		Kunde kunde = KundenFassadeImpl.erstelleKunden("John Doe",
				"Example Street 5");
		System.out.println("Neuer Kunde: " + kunde.getId() + "\n");
		System.out.println("Gefundener Kunde: " + KundenFassadeImpl.findeKunden("John Doe").getId() + "\n");
		assertEquals(kunde, KundenFassadeImpl.findeKunden("John Doe"));

		Produkt produkt1 = LagerFassade.erstelleProdukt("Toaster", 5);
		Produkt produkt2 = LagerFassade.erstelleProdukt("Mixer", 15);
		Produkt produkt3 = LagerFassade.erstelleProdukt("Staubsauger", 10);
		assertEquals(produkt1, LagerFassade.findeProdukt(produkt1.getId()));
		assertEquals(produkt2, LagerFassade.findeProdukt(produkt2.getName()));

		Map<Produkt, Integer> produkte = new HashMap<Produkt, Integer>();
		produkte.put(produkt1, 1);
		produkte.put(produkt3, 2);

		Angebot angebot = BestellFassade.erstelleAngebot(kunde, produkte,
				DateUtil.now(), DateUtil.daysFromNow(30), 50);
		assertTrue(kunde.getAngebote().contains(angebot));
		
		assertTrue(KundenFassadeImpl.findeKunden("John Doe").getAngebote().contains(angebot));

		Auftrag auftrag = BestellFassade.erstelleAuftrag(angebot,
				DateUtil.now());
		assertEquals(angebot, auftrag.getAngebot());
		assertNotNull(auftrag.getBeauftragtAm());

		LieferFassade.markiereTransportErfolgt(auftrag.getLieferung()
				.getTransportauftrag());
		assertTrue(auftrag.getLieferung().getTransportauftrag().isLieferungErfolgt());

		RechnungsFassade.erstelleZahlungseingang(auftrag.getRechnung(),
				DateUtil.now(), 40);
		RechnungsFassade.erstelleZahlungseingang(auftrag.getRechnung(),
				DateUtil.now(), 10);
		assertEquals(2, auftrag.getRechnung().getZahlungseingaenge().size());

		assertFalse(auftrag.isIstAbgeschlossen());
		BestellFassade.auftragAbschliessen(auftrag);
		assertTrue(auftrag.isIstAbgeschlossen());

		PersistenzService.closeSession();
	}

}
