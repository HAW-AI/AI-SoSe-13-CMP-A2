package haw.ai.client;

import haw.ai.client.gui.dashboard.Dashboard;
import haw.ai.common.Log;

import java.rmi.AccessException;
import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;

public class Starter {
	public final static String CLIENT_REGISTRY_HOST = "127.0.0.1";
	public final static Integer CLIENT_REGISTRY_PORT = 1099;
	private Registry registry;
	private HESHealthMonitor hesMonitor;
	private Dispatcher dispatcher;
	private Dashboard dashboard;

	public static void main(String[] args) {
		Starter starter = new Starter();
		try {
			starter.startRegistry();
			starter.startServer();
			starter.startGui();
			starter.startMonitor();
			starter.startTestClient();
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
		Log.log(Starter.class.getName(), "--- Dispatcher gestartet ---");
	}

	private void startGui() {
		dashboard = new Dashboard(dispatcher);
		Log.log(Starter.class.getName(), "--- Dashboard gestartet ---");
	}

	private void startMonitor() throws AccessException, RemoteException {
		hesMonitor = new HESHealthMonitorImpl(dispatcher, dashboard);
		registry.rebind(HESHealthMonitor.class.getSimpleName(), hesMonitor);
		((Thread) hesMonitor).start();
		Log.log(Starter.class.getName(), "--- Monitor gestartet ---");
	}

	private void startTestClient() {
		try {
			TestClient test = new TestClient(dispatcher);
			Log.log(Starter.class.getName(), "--- TestClient gestartet ---");
			Thread.sleep(10000);
			test.test();
		} catch (InterruptedException e) {
		}
	}
}
