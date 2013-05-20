package haw.ai.test;

import static org.junit.Assert.*;
import haw.ai.hibernate.HibernateUtil;
import haw.ai.komponenten.kunden_komponente.Kunde;
import haw.ai.komponenten.kunden_komponente.KundenFassadeImpl;
import haw.ai.komponenten.persistenz.PersistenzService;

import org.junit.Test;

public class KundenKomponentenTest {

	@Test
	public void test() {		
		Kunde kunde = KundenFassadeImpl.erstelleKunden("Max Peter",
				"Neue Stra§e 25");
		Kunde kunde2 = KundenFassadeImpl.erstelleKunden("Isaac Newton", "Nirgendwo");
		
		assertNotNull(kunde.getId());
		assertNotNull(kunde2.getId());
		
		assertEquals(kunde, KundenFassadeImpl.findeKunden("Max Peter"));
		assertEquals(kunde2, KundenFassadeImpl.findeKunden("Isaac Newton"));
		
		PersistenzService.closeSession();
	}

}
