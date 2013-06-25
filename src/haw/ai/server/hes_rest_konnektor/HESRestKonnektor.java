package haw.ai.server.hes_rest_konnektor;

import haw.ai.common.Log;

import java.io.IOException;

import com.sun.jersey.api.container.httpserver.HttpServerFactory;
import com.sun.net.httpserver.HttpServer;

public class HESRestKonnektor {
	public static final String HESRestApiUrl = "http://localhost:";
	private HttpServer server;

	public HESRestKonnektor(Integer hesRestServerPort) throws IllegalArgumentException, IOException {
		Log.log(HESRestKonnektor.class.getSimpleName(), "creating rest server at address:", HESRestApiUrl + hesRestServerPort);
		this.server = HttpServerFactory.create(HESRestApiUrl + hesRestServerPort + "/");
	}

	public void start() {
		Log.log(HESRestKonnektor.class.getSimpleName(), "starting rest server at address:", HESRestApiUrl + server.getAddress().toString());
		this.server.start();
	}

	public void stop() {
		this.server.stop(0);
	}
}
