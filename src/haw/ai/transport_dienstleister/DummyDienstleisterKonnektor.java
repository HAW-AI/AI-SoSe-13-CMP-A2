package haw.ai.transport_dienstleister;

import java.io.IOException;

import com.sun.jersey.api.container.httpserver.HttpServerFactory;
import com.sun.net.httpserver.HttpServer;

public class DummyDienstleisterKonnektor {
	public static final String DummyDienstleisterApiUrl = "http://localhost:8080/";
	private HttpServer server;
	
	public DummyDienstleisterKonnektor() throws IllegalArgumentException, IOException {
		this.server = HttpServerFactory.create(DummyDienstleisterApiUrl);
	}

	public void start() {
		this.server.start();
	}

	public void stop() {
		this.server.stop(0);
	}
}
