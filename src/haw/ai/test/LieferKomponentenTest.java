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
import haw.ai.server.liefer_komponente.LieferFassadeImpl;
import haw.ai.server.liefer_komponente.Lieferung;
import haw.ai.server.liefer_komponente.Transportauftrag;
import haw.ai.server.persistenz.HibernateUtil;
import haw.ai.server.persistenz.PersistenzService;

import java.util.HashMap;
import java.util.Map;

import org.junit.Test;

public class LieferKomponentenTest {

	@Test
	public void test() {
		Kunde kunde = KundenFassadeImpl.erstelleKunden("John Dover",
				"Example Street 5");
		Produkt produkt1 = LagerFassadeImpl.erstelleProdukt("ToasterOven", 5);
		Map<Produkt, Integer> produkte = new HashMap<Produkt, Integer>();
		produkte.put(produkt1, 1);
		Angebot angebot = BestellFassadeImpl.erstelleAngebot(kunde, produkte,
				DateUtil.now(), DateUtil.daysFromNow(30), 50);
		Auftrag auftrag = BestellFassadeImpl.erstelleAuftrag(angebot,
				DateUtil.now());

		Lieferung lieferung = LieferFassadeImpl.erstelleLieferung(auftrag);
		assertNotNull(lieferung);
		assertNotNull(lieferung.getId());
		assertNull(lieferung.getTransportauftrag());

		Transportauftrag transportauftrag = LieferFassadeImpl
				.erstelleTransportauftrag(lieferung, DateUtil.now(), false,
						null, "DHL");
		assertEquals(transportauftrag, lieferung.getTransportauftrag());
		assertEquals(lieferung, transportauftrag.getLieferung());

		assertFalse(transportauftrag.isLieferungErfolgt());
		LieferFassadeImpl.markiereTransportErfolgt(transportauftrag);
		assertTrue(transportauftrag.isLieferungErfolgt());

		PersistenzService.closeSession();
	}

}
