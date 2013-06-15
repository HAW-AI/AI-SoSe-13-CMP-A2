package haw.ai.server.hes_rest_konnektor;

import java.io.IOException;

import com.sun.jersey.api.container.httpserver.HttpServerFactory;
import com.sun.net.httpserver.HttpServer;

public class HESRestKonnektor {
	public static final String HESRestApiUrl = "http://localhost:";
	private HttpServer server;

	public HESRestKonnektor(Integer hesRestServerPort) throws IllegalArgumentException, IOException {
		this.server = HttpServerFactory.create(HESRestApiUrl + hesRestServerPort + "/");
	}

	public void start() {
		this.server.start();
	}

	public void stop() {
		this.server.stop(0);
	}
}
