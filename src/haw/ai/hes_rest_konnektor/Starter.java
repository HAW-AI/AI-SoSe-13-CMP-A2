package haw.ai.hes_rest_konnektor;

import java.io.IOException;

public class Starter {

	public static void main(String[] args) throws IllegalArgumentException, IOException {
		HESRestKonnektor hesRestKonnektor = new HESRestKonnektor();
		hesRestKonnektor.start();
	}

}
