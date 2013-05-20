package haw.ai.test;

import static org.junit.Assert.*;

import java.util.HashMap;
import java.util.Map;

import haw.ai.hibernate.HibernateUtil;
import haw.ai.komponenten.bestell_komponente.Angebot;
import haw.ai.komponenten.bestell_komponente.Auftrag;
import haw.ai.komponenten.bestell_komponente.BestellFassade;
import haw.ai.komponenten.common.DateUtil;
import haw.ai.komponenten.kunden_komponente.Kunde;
import haw.ai.komponenten.kunden_komponente.KundenFassadeImpl;
import haw.ai.komponenten.lager_komponente.LagerFassade;
import haw.ai.komponenten.lager_komponente.Produkt;
import haw.ai.komponenten.persistenz.PersistenzService;

import org.junit.Test;

public class BestellKomponentenTest {

	@Test
	public void test() {
		Kunde kunde = KundenFassadeImpl.erstelleKunden("Maria Meier",
				"Auf der Wiese 1");
		
		Produkt produkt1 = LagerFassade.erstelleProdukt("Fernseher", 500);
		Produkt produkt2 = LagerFassade.erstelleProdukt("Bananenblitzschneider", 150);

		Map<Produkt, Integer> produkte = new HashMap<Produkt, Integer>();
		produkte.put(produkt1, 1);
		produkte.put(produkt2, 2);
		
		Angebot angebot = BestellFassade.erstelleAngebot(kunde, produkte,
				DateUtil.now(), DateUtil.daysFromNow(30), 3500);
		
		assertNotNull(angebot.getId());
		assertTrue(kunde.getAngebote().contains(angebot));
		
		assertTrue(KundenFassadeImpl.findeKunden("Maria Meier").getAngebote().contains(angebot));

		Auftrag auftrag = BestellFassade.erstelleAuftrag(angebot,
				DateUtil.now());
		
		assertNotNull(auftrag.getId());
		assertEquals(angebot, auftrag.getAngebot());
		assertNotNull(auftrag.getBeauftragtAm());
		
		assertEquals(false, auftrag.isIstAbgeschlossen());
		BestellFassade.auftragAbschliessen(auftrag);
		assertEquals(true, auftrag.isIstAbgeschlossen());
		
		PersistenzService.closeSession();
	}

}
