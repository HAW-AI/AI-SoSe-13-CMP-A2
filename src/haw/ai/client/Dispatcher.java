package haw.ai.client;

public interface Dispatcher {

	// Dispatcher erhaelt "I Am Alive"-Nachricht vom Monitor
	public void iAmAlive(RemoteHESInstance hesInstance);
	// Dispatcher erhaelt "I Am Not Alive"-Nachricht vom Monitor
	public void iAmNotAlive(RemoteHESInstance hesInstance);
	// Round-Robin-Algorithmus, gibt naechste Instanz zurueck
	public RemoteHESInstance chooseNextInstance();
	// Zustand eines HES-Prozesses aendern, wird vom Dashboard aufgerufen
	public void changeInstanceState(String instanzname, boolean state);
	
}
