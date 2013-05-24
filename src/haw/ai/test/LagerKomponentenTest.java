package haw.ai.test;

import static org.junit.Assert.*;
import haw.ai.server.common.DateUtil;
import haw.ai.server.lager_komponente.LagerFassadeImpl;
import haw.ai.server.lager_komponente.Produkt;
import haw.ai.server.lager_komponente.Warenausgangsmeldung;
import haw.ai.server.persistenz.HibernateUtil;
import haw.ai.server.persistenz.PersistenzService;

import org.junit.Test;

public class LagerKomponentenTest {

	@Test
	public void test() {
		Produkt produkt1 = LagerFassadeImpl.erstelleProdukt("Computer", 500);
		Produkt produkt2 = LagerFassadeImpl.erstelleProdukt("Besenstiel", 20000);

		assertNotNull(produkt1.getId());
		assertNotNull(produkt2.getId());

		assertEquals(produkt1, LagerFassadeImpl.findeProdukt(produkt1.getId()));
		assertEquals(produkt2, LagerFassadeImpl.findeProdukt(produkt2.getName()));

		assertNull(produkt1.getWarenausgangsmeldungen());

		Warenausgangsmeldung wa1 = LagerFassadeImpl.erstelleWarenausgangsmeldung(
				produkt1, DateUtil.now(), 50);
		Warenausgangsmeldung wa2 = LagerFassadeImpl.erstelleWarenausgangsmeldung(
				produkt1, DateUtil.now(), 100);
		
		assertNotNull(produkt1.getWarenausgangsmeldungen());
		assertEquals(2,produkt1.getWarenausgangsmeldungen().size());
		assertEquals(produkt1, wa1.getProdukt());
		assertNotNull(wa2.getProdukt());

		PersistenzService.closeSession();
	}
}
