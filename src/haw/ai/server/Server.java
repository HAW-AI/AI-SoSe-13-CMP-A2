package haw.ai.server;

import haw.ai.common.Log;

import java.rmi.RemoteException;

public class Server {
	private static HESServer hesServer;

	public static void main(String[] args) throws RemoteException {
		hesServer = HESServerImpl.create("localhost", 1099);
		Log.log(HESServer.class.getName(), "HESServer started");
	}
}
