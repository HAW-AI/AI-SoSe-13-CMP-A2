package haw.ai.server.liefer_komponente;

import haw.ai.common.Log;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.text.Format;
import java.text.SimpleDateFormat;

public class DummyDiensleisterAdapter {
	private static final String DummyDienstleisterApiUrl = "http://localhost:8080";
	private static final String CREATE_NEW_TRANSPORTAUFTRAG_ENDPOINT = "/dummydienstleister/transportauftrag";

	public DummyDiensleisterAdapter() {
	}

	public void sendNewTransportauftrag(Transportauftrag transportauftrag) {
		try {

			URL url = new URL(DummyDienstleisterApiUrl
					+ CREATE_NEW_TRANSPORTAUFTRAG_ENDPOINT);
			HttpURLConnection conn = (HttpURLConnection) url.openConnection();
			conn.setDoOutput(true);
			conn.setRequestMethod("POST");
			conn.setRequestProperty("Content-Type", "application/json");

			Format formatter = new SimpleDateFormat("yyyy-MM-dd");
			// String input =
			// "{\"ausgangsDatum\": \"2012-12-12\",\"id\": \"12\",\"lieferDatum\": \"2012-12-13\",\"lieferungErfolgt\": \"false\"}";
			String input = "{\"ausgangsDatum\": \""
					+ formatter.format(transportauftrag.getAusgangsDatum())
					+ "\",\"id\":\"" + transportauftrag.getId()
					+ "\",\"lieferDatum\": \""
					+ formatter.format(transportauftrag.getLieferDatum())
					+ "\",\"lieferungErfolgt\": \"false\"}";
			
			Log.log(DummyDiensleisterAdapter.class.getSimpleName(), "json message:", input);

			OutputStream os = conn.getOutputStream();
			os.write(input.getBytes());
			os.flush();

			if (conn.getResponseCode() != HttpURLConnection.HTTP_ACCEPTED) {
				throw new RuntimeException("Failed : HTTP error code : "
						+ conn.getResponseCode());
			}

			BufferedReader br = new BufferedReader(new InputStreamReader(
					(conn.getInputStream())));

			String output;
			System.out.println("Output from Server .... \n");
			while ((output = br.readLine()) != null) {
				System.out.println(output);
			}

			conn.disconnect();

		} catch (MalformedURLException e) {
			Log.log(DummyDiensleisterAdapter.class.getSimpleName(), "MalformedURLException", e.getMessage());
		} catch (IOException e) {
			Log.log(DummyDiensleisterAdapter.class.getSimpleName(), "IOException", e.getMessage());
		}
	}
}