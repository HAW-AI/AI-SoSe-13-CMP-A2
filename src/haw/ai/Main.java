package haw.ai;

import java.util.HashMap;
import java.util.Map;

import haw.ai.hibernate.HibernateUtil;
import haw.ai.komponenten.bestell_komponente.Angebot;
import haw.ai.komponenten.bestell_komponente.Auftrag;
import haw.ai.komponenten.bestell_komponente.BestellFassade;
import haw.ai.komponenten.common.DateUtil;
import haw.ai.komponenten.kunden_komponente.Kunde;
import haw.ai.komponenten.kunden_komponente.KundenFassade;
import haw.ai.komponenten.lager_komponente.LagerFassade;
import haw.ai.komponenten.lager_komponente.Produkt;
import haw.ai.komponenten.liefer_komponente.LieferFassade;
import haw.ai.komponenten.rechnungs_komponente.RechnungsFassade;

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
