package haw.ai.hes_rest_konnektor;

import java.io.IOException;

import com.sun.jersey.api.container.httpserver.HttpServerFactory;
import com.sun.net.httpserver.HttpServer;

public class HESRestKonnektor {
	public static final String HESRestApiUrl = "http://localhost:8090/";
	private HttpServer server;

	public HESRestKonnektor() throws IllegalArgumentException, IOException {
		this.server = HttpServerFactory.create(HESRestApiUrl);
	}

	public void start() {
		this.server.start();
	}

	public void stop() {
		this.server.stop(0);
	}
}
