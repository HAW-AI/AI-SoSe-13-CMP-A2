package haw.ai.transport_dienstleister;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import com.sun.jersey.api.container.httpserver.HttpServerFactory;
import com.sun.net.httpserver.HttpServer;
import haw.ai.server.liefer_komponente.TransportauftragJSON;

public class DummyDienstleisterKonnektor {
	
	public static final String DummyDienstleisterApiUrl = "http://localhost:8080/";
	private HttpServer server;
	private HESApiAdapter adapter;
	private UpdateTransportauftraegeThread updateThread;
	private static List<TransportauftragJSON> transportauftraege = new ArrayList<TransportauftragJSON>();
	// Thread der alle n sekunden das naechste element aus der list nimmt und
	// an den HES Server per PUT zurueckschickt
	
	public DummyDienstleisterKonnektor() throws IllegalArgumentException, IOException {
		this.server = HttpServerFactory.create(DummyDienstleisterApiUrl);
		this.adapter = new HESApiAdapter();
		this.updateThread = new UpdateTransportauftraegeThread(adapter);
	}

	public void start() {
		this.server.start();
		this.updateThread.start();
	}

	public void stop() {
		this.server.stop(0);
	}
	
	public static List<TransportauftragJSON> getTransportauftraege() {
		return transportauftraege;
	}
	
	public static void addTransportauftrag(TransportauftragJSON ta) {
		transportauftraege.add(ta);
	}
}
