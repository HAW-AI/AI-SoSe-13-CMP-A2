package haw.ai.test;

import static org.junit.Assert.*;
import haw.ai.hibernate.HibernateUtil;
import haw.ai.komponenten.kunden_komponente.Kunde;
import haw.ai.komponenten.kunden_komponente.KundenFassade;

import org.junit.Test;

public class KundenKomponentenTest {

	@Test
	public void test() {		
		Kunde kunde = KundenFassade.erstelleKunden("Max Peter",
				"Neue Stra§e 25");
		Kunde kunde2 = KundenFassade.erstelleKunden("Isaac Newton", "Nirgendwo");
		
		assertNotNull(kunde.getId());
		assertNotNull(kunde2.getId());
		
		assertEquals(kunde, KundenFassade.findeKunden("Max Peter"));
		assertEquals(kunde2, KundenFassade.findeKunden("Isaac Newton"));
		
		HibernateUtil.closeSession();
	}

}
