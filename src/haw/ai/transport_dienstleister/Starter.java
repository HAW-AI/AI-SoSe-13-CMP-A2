package haw.ai.transport_dienstleister;

import java.io.IOException;

import javax.swing.JOptionPane;
import com.sun.jersey.api.container.httpserver.HttpServerFactory;
import com.sun.net.httpserver.HttpServer;

public class Starter {

	public static void main(String[] args) throws IllegalArgumentException, IOException {
		DummyDienstleisterKonnektor dummyDienstleisterKonnektor = new DummyDienstleisterKonnektor();
		dummyDienstleisterKonnektor.start();
	}
	
}
