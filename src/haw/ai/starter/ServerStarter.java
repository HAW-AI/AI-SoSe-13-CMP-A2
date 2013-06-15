package haw.ai.starter;

import haw.ai.common.Log;
import haw.ai.server.HESServer;
import haw.ai.server.HESServerImpl;

import java.rmi.RemoteException;

public class ServerStarter {
	private static HESServer hesServer;

	public static void main(String[] args) throws RemoteException {
		hesServer = HESServerImpl.getInstance("localhost", 1099, args[0], args[1]);
		Log.log(HESServer.class.getName(), "HESServer started");
	}
}
