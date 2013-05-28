package haw.ai.client;

import haw.ai.client.gui.dashboard.Dashboard;
import haw.ai.server.bestell_komponente.BestellFassade;
import haw.ai.server.kunden_komponente.KundenFassade;
import haw.ai.server.lager_komponente.LagerFassade;
import haw.ai.server.liefer_komponente.LieferFassade;
import haw.ai.server.rechnungs_komponente.RechnungsFassade;

import java.io.Serializable;

public interface Dispatcher extends Serializable {

	public void setDashboard(Dashboard dashboard);
	// Dispatcher erhaelt "I Am Alive"-Nachricht vom Monitor
	// Instanz wird in die Liste aller lebenden Instanzen eingetragen
	public void iAmAlive(RemoteHESInstance hesInstance);
	// Dispatcher erhaelt "I Am Not Alive"-Nachricht vom Monitor
	// Instanz wird aus der Liste aller lebenden Instanzen geloescht
	public void iAmNotAlive(RemoteHESInstance hesInstance);
	// Zustand eines HES-Prozesses aendern, wird vom Dashboard aufgerufen
	// Befehl des Umschaltens auf ON oder OFF wird an ServerController weitergeleitet
	public void changeInstanceState(String instanzname, boolean state);
	
	// sucht nach Round-Robin die naechste Instanz aus und holt die gewuenschte Fassade
	// dieser Instanz aus der Registry
	public BestellFassade getBestellFassade();
	public KundenFassade getKundenFassade();
	public LagerFassade getLagerFassade();
	public LieferFassade getLieferFassade();
	public RechnungsFassade getRechnungsFassade();
}
