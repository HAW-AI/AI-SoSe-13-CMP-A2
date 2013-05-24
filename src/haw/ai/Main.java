package haw.ai;

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
import haw.ai.server.liefer_komponente.LieferFassadeImpl;
import haw.ai.server.persistenz.HibernateUtil;
import haw.ai.server.rechnungs_komponente.RechnungsFassadeImpl;

import org.hibernate.Session;
import org.hibernate.SessionFactory;

public class Main {

	/**
	 * @param args
	 */
	private static Session session;

	public static void main(String[] args) {
		SessionFactory sessionFactory = HibernateUtil.getSessionFactory();
		session = sessionFactory.openSession();

//		Kunde kunde = KundenFassade.erstelleKunden("John Doe",
//				"Example Street 5");
//		System.out.println("Neuer Kunde: " + kunde.getId() + "\n");
//
//		Produkt produkt1 = LagerFassade.erstelleProdukt("Toaster", 5);
//		Produkt produkt2 = LagerFassade.erstelleProdukt("Mixer", 15);
//		Produkt produkt3 = LagerFassade.erstelleProdukt("Staubsauger", 10);
//
//		Map<Produkt, Integer> produkte = new HashMap<Produkt, Integer>();
//		produkte.put(produkt1, 1);
//		produkte.put(produkt3, 2);
//
//		Angebot angebot = BestellFassade.erstelleAngebot(kunde, produkte,
//				DateUtil.now(), DateUtil.daysFromNow(30), 50);
//
//		Auftrag auftrag = BestellFassade.erstelleAuftrag(angebot,
//				DateUtil.now());
//
//		LieferFassade.markiereTransportErfolgt(auftrag.getLieferung()
//				.getTransportauftrag());
//
//		RechnungsFassade.erstelleZahlungseingang(auftrag.getRechnung(),
//				DateUtil.now(), 40);
//		RechnungsFassade.erstelleZahlungseingang(auftrag.getRechnung(),
//				DateUtil.now(), 10);
//
//		BestellFassade.auftragAbschliessen(auftrag);

		session.close();
	}

}
