package haw.ai.server;

import java.rmi.RemoteException;

public class Server {
	private static HESServer hesServer;

	public static void main(String[] args) throws RemoteException {
		hesServer = HESServerImpl.create();
	}
}