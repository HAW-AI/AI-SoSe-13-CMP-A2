package haw.ai.server.alive;

import haw.ai.server.HESServerImpl;
import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;

public class ServerController extends UnicastRemoteObject {
	
	private static final long serialVersionUID = 1L;
	private HESServerImpl server;
	private AliveBeacon beacon;

	public ServerController(HESServerImpl server) throws RemoteException {
		this.server = server;		
		this.beacon = new AliveBeacon(server);
		this.beacon.start();
	}
	
	public static ServerController createServerController(HESServerImpl hesServer) throws RemoteException {
		ServerController serverController = new ServerController(hesServer);
		hesServer.getServerRegistry().rebind(ServerController.class.getSimpleName(), serverController);
		return serverController;
	}
	
	// wird vom Dispatcher aufgerufen, um eine HES-Instanz online/offline zu schalten
	public void changeInstanceState(boolean state) {
		if (state == false) {
			this.beacon.interrupt();
		}
		else {
			if (!this.beacon.isAlive()) {
				this.beacon = new AliveBeacon(this.server);
			}
		}
	}
	
}
