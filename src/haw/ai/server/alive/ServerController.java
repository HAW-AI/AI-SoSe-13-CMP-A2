package haw.ai.server.alive;

import java.rmi.Remote;
import java.rmi.RemoteException;

public interface ServerController extends Remote {
	// wird vom Dispatcher aufgerufen, um eine HES-Instanz online/offline zu schalten
	// wenn State = OFF: AliveBeacon wird unterbrochen, Nachrichten werden nicht mehr gesendet
	// wenn State = ON: AliveBeacon wird neu erzeugt, wenn nicht bereits existent
	public void changeInstanceState(boolean state) throws RemoteException;
}
