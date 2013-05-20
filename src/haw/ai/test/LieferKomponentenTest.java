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
import haw.ai.komponenten.liefer_komponente.Lieferung;
import haw.ai.komponenten.liefer_komponente.Transportauftrag;
import haw.ai.komponenten.persistenz.PersistenzService;

import java.util.HashMap;
import java.util.Map;

import org.junit.Test;

public class LieferKomponentenTest {

	@Test
	public void test() {
		Kunde kunde = KundenFassadeImpl.erstelleKunden("John Dover",
				"Example Street 5");
		Produkt produkt1 = LagerFassade.erstelleProdukt("ToasterOven", 5);
		Map<Produkt, Integer> produkte = new HashMap<Produkt, Integer>();
		produkte.put(produkt1, 1);
		Angebot angebot = BestellFassade.erstelleAngebot(kunde, produkte,
				DateUtil.now(), DateUtil.daysFromNow(30), 50);
		Auftrag auftrag = BestellFassade.erstelleAuftrag(angebot,
				DateUtil.now());

		Lieferung lieferung = LieferFassade.erstelleLieferung(auftrag);
		assertNotNull(lieferung);
		assertNotNull(lieferung.getId());
		assertNull(lieferung.getTransportauftrag());

		Transportauftrag transportauftrag = LieferFassade
				.erstelleTransportauftrag(lieferung, DateUtil.now(), false,
						null, "DHL");
		assertEquals(transportauftrag, lieferung.getTransportauftrag());
		assertEquals(lieferung, transportauftrag.getLieferung());

		assertFalse(transportauftrag.isLieferungErfolgt());
		LieferFassade.markiereTransportErfolgt(transportauftrag);
		assertTrue(transportauftrag.isLieferungErfolgt());

		PersistenzService.closeSession();
	}

}
