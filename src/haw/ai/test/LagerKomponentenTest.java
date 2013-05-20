package haw.ai.test;

import static org.junit.Assert.*;
import haw.ai.hibernate.HibernateUtil;
import haw.ai.komponenten.common.DateUtil;
import haw.ai.komponenten.lager_komponente.LagerFassade;
import haw.ai.komponenten.lager_komponente.Produkt;
import haw.ai.komponenten.lager_komponente.Warenausgangsmeldung;
import haw.ai.komponenten.persistenz.PersistenzService;

import org.junit.Test;

public class LagerKomponentenTest {

	@Test
	public void test() {
		Produkt produkt1 = LagerFassade.erstelleProdukt("Computer", 500);
		Produkt produkt2 = LagerFassade.erstelleProdukt("Besenstiel", 20000);

		assertNotNull(produkt1.getId());
		assertNotNull(produkt2.getId());

		assertEquals(produkt1, LagerFassade.findeProdukt(produkt1.getId()));
		assertEquals(produkt2, LagerFassade.findeProdukt(produkt2.getName()));

		assertNull(produkt1.getWarenausgangsmeldungen());

		Warenausgangsmeldung wa1 = LagerFassade.erstelleWarenausgangsmeldung(
				produkt1, DateUtil.now(), 50);
		Warenausgangsmeldung wa2 = LagerFassade.erstelleWarenausgangsmeldung(
				produkt1, DateUtil.now(), 100);
		
		assertNotNull(produkt1.getWarenausgangsmeldungen());
		assertEquals(2,produkt1.getWarenausgangsmeldungen().size());
		assertEquals(produkt1, wa1.getProdukt());
		assertNotNull(wa2.getProdukt());

		PersistenzService.closeSession();
	}
}
