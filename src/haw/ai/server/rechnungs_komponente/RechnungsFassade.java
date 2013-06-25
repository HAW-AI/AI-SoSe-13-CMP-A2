package haw.ai.server.rechnungs_komponente;

import haw.ai.server.bestell_komponente.Auftrag;
import haw.ai.server.common.KomponentenFassade;

import java.rmi.Remote;
import java.rmi.RemoteException;
import java.util.Date;

public interface RechnungsFassade extends KomponentenFassade, Remote {
	
	public Rechnung erstelleRechnung(Date rechnungsDatum, Auftrag auftrag) throws RemoteException;
	public Zahlungseingang erstelleZahlungseingang(Rechnung rechnung,
			Date eingangsDatum, int betrag) throws RemoteException;
	
	/**
	 * Rechnung wird vom Buchhalter nur dann als bezahlt markiert, wenn sie
	 * bezahlt ist (Summe aller Zahlungseingaenge fuer diese Rechnung entspricht
	 * dem Rechnungsbetrag). Ist sie nicht bezahlt, passiert nichts (Rechnung
	 * wird nicht als bezahlt markiert). Wichtig: Die Methode wird nie direkt
	 * aufgerufen, der Methodenaufruf ist bereits in der Methode
	 * "auftragAbschliessen" in der Bestellfassade integriert!
	 * 
	 * @param rechnung
	 */
	public void rechnungBezahltWennZahlungAusreichend(Rechnung rechnung) throws RemoteException;
	public void save(Rechnung rechnung) throws RemoteException;
	public void save(Zahlungseingang zahlungseingang) throws RemoteException;
	public Rechnung findRechnungByRechnungsnummer(Integer rechnungsNummer) throws RemoteException;

}
