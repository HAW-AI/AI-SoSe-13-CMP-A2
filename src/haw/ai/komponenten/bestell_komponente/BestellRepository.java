package haw.ai.komponenten.bestell_komponente;

import java.util.Date;
import java.util.Map;

import org.hibernate.Session;

import haw.ai.hibernate.HibernateUtil;
import haw.ai.komponenten.kunden_komponente.Kunde;
import haw.ai.komponenten.lager_komponente.Produkt;

public class BestellRepository {

	public static Angebot erstelleAngebot(Kunde kunde, Map<Produkt, Integer> produkte, Date gueltigAb,
		Date gueltigBis, int gesamtPreis) {
		Angebot angebot = new Angebot(kunde, produkte, gueltigAb, gueltigBis, gesamtPreis);
		Session session = HibernateUtil.getSession();
		session.beginTransaction();
		session.save(angebot);
		session.getTransaction().commit();
		return angebot;
	}

	public static Auftrag erstelleAuftrag(Angebot angebot,Date beauftragtAm) {
		Auftrag auftrag = new Auftrag(angebot, false, beauftragtAm);
		Session session = HibernateUtil.getSession();
		session.beginTransaction();
		session.save(auftrag);
		session.getTransaction().commit();
		return auftrag;
	}
}
