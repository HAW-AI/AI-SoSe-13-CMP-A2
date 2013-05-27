//package haw.ai.test;
//
//import static org.junit.Assert.*;
//import haw.ai.server.kunden_komponente.Kunde;
//import haw.ai.server.kunden_komponente.KundenFassadeImpl;
//import haw.ai.server.persistenz.HibernateUtil;
//import haw.ai.server.persistenz.PersistenzService;
//
//import org.junit.Test;
//
//public class KundenKomponentenTest {
//
//	@Test
//	public void test() {		
//		Kunde kunde = KundenFassadeImpl.erstelleKunden("Max Peter",
//				"Neue Stra§e 25");
//		Kunde kunde2 = KundenFassadeImpl.erstelleKunden("Isaac Newton", "Nirgendwo");
//		
//		assertNotNull(kunde.getId());
//		assertNotNull(kunde2.getId());
//		
//		assertEquals(kunde, KundenFassadeImpl.findeKunden("Max Peter"));
//		assertEquals(kunde2, KundenFassadeImpl.findeKunden("Isaac Newton"));
//		
//		PersistenzService.closeSession();
//	}
//
//}
