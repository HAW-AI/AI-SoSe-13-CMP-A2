package haw.ai.starter;

import haw.ai.client.Dispatcher;
import haw.ai.client.DispatcherImpl;
import haw.ai.client.HESHealthMonitor;
import haw.ai.client.HESHealthMonitorImpl;
import haw.ai.client.HESHealthMonitorThread;
import haw.ai.client.TestClient;
import haw.ai.client.gui.dashboard.Dashboard;
import haw.ai.common.Log;

import java.rmi.AccessException;
import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;
import java.rmi.server.RemoteStub;
import java.rmi.server.UnicastRemoteObject;

public class Client {
	public final static String CLIENT_REGISTRY_HOST = "localhost";
	public final static Integer CLIENT_REGISTRY_PORT = 1099;
	private Registry registry;
	private HESHealthMonitorThread hesMonitor;
	private Dispatcher dispatcher;
	private Dashboard dashboard;

	public static void main(String[] args) {
		Client client = new Client();
		try {
			client.startRegistry();
			client.startServer();
			client.startGui();
			client.startMonitor();
			client.startTestClient();
		} catch (AccessException e) {
		} catch (RemoteException e) {
		}
	}

	private void startRegistry() {
		try {
			// create on port 1099
			registry = LocateRegistry.createRegistry(1099);
		} catch (Exception e) {
		}
	}

	private void startServer() {
		// The Dispatcher starts a new server process if non exists.
		dispatcher = new DispatcherImpl(registry);
		Log.log(Client.class.getName(), "--- Dispatcher gestartet ---");
	}

	private void startGui() {
		dashboard = new Dashboard(dispatcher);
		Log.log(Client.class.getName(), "--- Dashboard gestartet ---");
	}

	private void startMonitor() throws AccessException, RemoteException {
		hesMonitor = new HESHealthMonitorThread(dispatcher, dashboard, registry);
		((Thread) hesMonitor).start();
		Log.log(Client.class.getName(), "--- Monitor gestartet ---");
	}

	private void startTestClient() {
		try {
			TestClient test = new TestClient(dispatcher);
			Log.log(Client.class.getName(), "--- TestClient gestartet ---");
			Thread.sleep(10000);
			test.test();
		} catch (InterruptedException e) {
		}
	}
}
