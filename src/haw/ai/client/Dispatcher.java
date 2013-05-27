package haw.ai.client;

import java.io.Serializable;

import haw.ai.server.bestell_komponente.BestellFassade;
import haw.ai.server.kunden_komponente.KundenFassade;
import haw.ai.server.lager_komponente.LagerFassade;
import haw.ai.server.liefer_komponente.LieferFassade;
import haw.ai.server.rechnungs_komponente.RechnungsFassade;

public interface Dispatcher extends Serializable {

	// Dispatcher erhaelt "I Am Alive"-Nachricht vom Monitor
	public void iAmAlive(RemoteHESInstance hesInstance);
	// Dispatcher erhaelt "I Am Not Alive"-Nachricht vom Monitor
	public void iAmNotAlive(RemoteHESInstance hesInstance);
	// Zustand eines HES-Prozesses aendern, wird vom Dashboard aufgerufen
	public void changeInstanceState(String instanzname, boolean state);
	public BestellFassade getBestellFassade();
	public KundenFassade getKundenFassade();
	public LagerFassade getLagerFassade();
	public LieferFassade getLieferFassade();
	public RechnungsFassade getRechnungsFassade();
}
