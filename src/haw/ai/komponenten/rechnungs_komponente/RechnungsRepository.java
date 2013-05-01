package haw.ai.komponenten.rechnungs_komponente;

import haw.ai.hibernate.HibernateUtil;
import haw.ai.komponenten.bestell_komponente.Angebot;
import haw.ai.komponenten.bestell_komponente.Auftrag;
import haw.ai.komponenten.kunden_komponente.Kunde;

import java.util.Date;

import org.hibernate.Session;

public class RechnungsRepository {

	public static Rechnung erstelleRechnung(Date rechnungsDatum, boolean istBezahlt, Auftrag auftrag) {
			Rechnung rechnung = new Rechnung(rechnungsDatum, istBezahlt, auftrag);
			Session session = HibernateUtil.getSession();
			session.beginTransaction();
			session.save(rechnung);
			session.getTransaction().commit();
			return rechnung;
		}

		public static Zahlungseingang erstelleZahlungseingang(Rechnung rechnung, Date eingangsDatum, int betrag) {
			Zahlungseingang zahlungseingang = new Zahlungseingang(rechnung, eingangsDatum, betrag);
			Session session = HibernateUtil.getSession();
			session.beginTransaction();
			session.save(zahlungseingang);
			session.getTransaction().commit();
			return zahlungseingang;
		}
	
}
