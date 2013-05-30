package haw.ai.starter;

import haw.ai.common.Log;
import haw.ai.server.HESServer;
import haw.ai.server.HESServerImpl;

import java.rmi.RemoteException;

public class ServerStarter {
	private static HESServer hesServer;

	public static void main(String[] args) throws RemoteException {
		hesServer = HESServerImpl.create("localhost", 1099, args[0]);
		Log.log(HESServer.class.getName(), "HESServer started");
	}
}
