package haw.ai.server;

import haw.ai.server.kunden_komponente.KundenFassade;
import haw.ai.server.kunden_komponente.KundenFassadeImpl;

import java.rmi.RemoteException;
import java.util.UUID;

public class Server {
	private String instanceName;
	private KundenFassade kundenFassade;

	public static void main(String[] args) throws RemoteException {
		Server starter = new Server();
		starter.kundenFassade = KundenFassadeImpl.createKundenFassade(starter.instanceName);
	}
	
	private Server() {
		this.instanceName = UUID.randomUUID().toString();
	}
}
