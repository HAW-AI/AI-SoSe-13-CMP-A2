package haw.ai.server.alive;

import haw.ai.common.Log;
import haw.ai.server.HESServerImpl;
import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;

public class ServerControllerImpl extends UnicastRemoteObject implements ServerController {
	
	private static final long serialVersionUID = 1L;
	private HESServerImpl server;
	private AliveBeacon beacon;

	private ServerControllerImpl(HESServerImpl server) throws RemoteException {
		this.server = server;		
		this.beacon = new AliveBeacon(server);
		this.beacon.start();
		Log.log(ServerControllerImpl.class.getName(), server.getInstanceName(), "initialized");
	}
	
	public static ServerController createServerController(HESServerImpl hesServer) throws RemoteException {
		Log.log(ServerControllerImpl.class.getName(), hesServer.getInstanceName(), "createSeverController");
		ServerController serverController = new ServerControllerImpl(hesServer);
		hesServer.getServerRegistry().rebind(ServerController.class.getSimpleName(), serverController);
		return serverController;
	}
	
	public void changeInstanceState(boolean state) {
		Log.log(ServerControllerImpl.class.getName(), server.getInstanceName(), "changeInstanceState", "" + state);
		if (state == false) {
			Log.log(ServerControllerImpl.class.getName(), server.getInstanceName(), "changeInstanceState", "interupting beacon");
			this.beacon.interrupt();
		}
		else {
			if (!this.beacon.isAlive()) {
				Log.log(ServerControllerImpl.class.getName(), server.getInstanceName(), "changeInstanceState", "starting new beacon");
				this.beacon = new AliveBeacon(this.server);
				this.beacon.start();
			}
		}
	}
	
}
