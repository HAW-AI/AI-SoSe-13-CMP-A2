//package haw.ai.test;
//
//import static org.junit.Assert.*;
//import haw.ai.server.bestell_komponente.Angebot;
//import haw.ai.server.bestell_komponente.Auftrag;
//import haw.ai.server.bestell_komponente.BestellFassadeImpl;
//import haw.ai.server.common.DateUtil;
//import haw.ai.server.kunden_komponente.Kunde;
//import haw.ai.server.kunden_komponente.KundenFassadeImpl;
//import haw.ai.server.lager_komponente.LagerFassadeImpl;
//import haw.ai.server.lager_komponente.Produkt;
//import haw.ai.server.liefer_komponente.LieferFassadeImpl;
//import haw.ai.server.persistenz.HibernateUtil;
//import haw.ai.server.persistenz.PersistenzService;
//import haw.ai.server.rechnungs_komponente.RechnungsFassadeImpl;
//
//import java.util.HashMap;
//import java.util.Map;
//
//import org.hibernate.Session;
//import org.hibernate.SessionFactory;
//import org.junit.Test;
//
//public class Szenario {
//
//	@Test
//	public void szenarioTest() {
//		Kunde kunde = KundenFassadeImpl.erstelleKunden("John Doe",
//				"Example Street 5");
//		System.out.println("Neuer Kunde: " + kunde.getId() + "\n");
//		System.out.println("Gefundener Kunde: " + KundenFassadeImpl.findeKunden("John Doe").getId() + "\n");
//		assertEquals(kunde, KundenFassadeImpl.findeKunden("John Doe"));
//
//		Produkt produkt1 = LagerFassadeImpl.erstelleProdukt("Toaster", 5);
//		Produkt produkt2 = LagerFassadeImpl.erstelleProdukt("Mixer", 15);
//		Produkt produkt3 = LagerFassadeImpl.erstelleProdukt("Staubsauger", 10);
//		assertEquals(produkt1, LagerFassadeImpl.findeProdukt(produkt1.getId()));
//		assertEquals(produkt2, LagerFassadeImpl.findeProdukt(produkt2.getName()));
//
//		Map<Produkt, Integer> produkte = new HashMap<Produkt, Integer>();
//		produkte.put(produkt1, 1);
//		produkte.put(produkt3, 2);
//
//		Angebot angebot = BestellFassadeImpl.erstelleAngebot(kunde, produkte,
//				DateUtil.now(), DateUtil.daysFromNow(30), 50);
//		assertTrue(kunde.getAngebote().contains(angebot));
//		
//		assertTrue(KundenFassadeImpl.findeKunden("John Doe").getAngebote().contains(angebot));
//
//		Auftrag auftrag = BestellFassadeImpl.erstelleAuftrag(angebot,
//				DateUtil.now());
//		assertEquals(angebot, auftrag.getAngebot());
//		assertNotNull(auftrag.getBeauftragtAm());
//
//		LieferFassadeImpl.markiereTransportErfolgt(auftrag.getLieferung()
//				.getTransportauftrag());
//		assertTrue(auftrag.getLieferung().getTransportauftrag().isLieferungErfolgt());
//
//		RechnungsFassadeImpl.erstelleZahlungseingang(auftrag.getRechnung(),
//				DateUtil.now(), 40);
//		RechnungsFassadeImpl.erstelleZahlungseingang(auftrag.getRechnung(),
//				DateUtil.now(), 10);
//		assertEquals(2, auftrag.getRechnung().getZahlungseingaenge().size());
//
//		assertFalse(auftrag.isIstAbgeschlossen());
//		BestellFassadeImpl.auftragAbschliessen(auftrag);
//		assertTrue(auftrag.isIstAbgeschlossen());
//
//		PersistenzService.closeSession();
//	}
//
//}
