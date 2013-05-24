package haw.ai.test;

import static org.junit.Assert.*;

import java.util.HashMap;
import java.util.Map;

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

import org.junit.Test;

public class BestellKomponentenTest {

	@Test
	public void test() {
		Kunde kunde = KundenFassadeImpl.erstelleKunden("Maria Meier",
				"Auf der Wiese 1");
		
		Produkt produkt1 = LagerFassadeImpl.erstelleProdukt("Fernseher", 500);
		Produkt produkt2 = LagerFassadeImpl.erstelleProdukt("Bananenblitzschneider", 150);

		Map<Produkt, Integer> produkte = new HashMap<Produkt, Integer>();
		produkte.put(produkt1, 1);
		produkte.put(produkt2, 2);
		
		Angebot angebot = BestellFassadeImpl.erstelleAngebot(kunde, produkte,
				DateUtil.now(), DateUtil.daysFromNow(30), 3500);
		
		assertNotNull(angebot.getId());
		assertTrue(kunde.getAngebote().contains(angebot));
		
		assertTrue(KundenFassadeImpl.findeKunden("Maria Meier").getAngebote().contains(angebot));

		Auftrag auftrag = BestellFassadeImpl.erstelleAuftrag(angebot,
				DateUtil.now());
		
		assertNotNull(auftrag.getId());
		assertEquals(angebot, auftrag.getAngebot());
		assertNotNull(auftrag.getBeauftragtAm());
		
		assertEquals(false, auftrag.isIstAbgeschlossen());
		BestellFassadeImpl.auftragAbschliessen(auftrag);
		assertEquals(true, auftrag.isIstAbgeschlossen());
		
		PersistenzService.closeSession();
	}

}
