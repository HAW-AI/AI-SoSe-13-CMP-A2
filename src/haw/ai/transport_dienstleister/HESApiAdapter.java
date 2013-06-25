package haw.ai.transport_dienstleister;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.text.Format;
import java.text.SimpleDateFormat;

import haw.ai.common.Log;
import haw.ai.server.common.DateUtil;
import haw.ai.server.liefer_komponente.TransportauftragJSON;

public class HESApiAdapter {
	private static final String HESRestApiUrl = "http://localhost:8090";
	private static final String UPDATE_TRANSPORTAUFTRAG_ENDPOINT = "/hes/transportauftrag";


	public HESApiAdapter() {
	}

	public void updateTransportauftrag(TransportauftragJSON transportauftrag) {
		Log.log(HESApiAdapter.class.getSimpleName(), "send updateTransportauftrag");
		Log.log(HESApiAdapter.class.getSimpleName(), "transportauftragID: " + transportauftrag.getId());
		try {

			URL url = new URL(HESRestApiUrl
					+ UPDATE_TRANSPORTAUFTRAG_ENDPOINT + "/" + transportauftrag.getId());
			HttpURLConnection conn = (HttpURLConnection) url.openConnection();
			conn.setDoOutput(true);
			conn.setRequestMethod("PUT");
			conn.setRequestProperty("Content-Type", "application/json");

			Format formatter = new SimpleDateFormat("yyyy-MM-dd");
			String input = "{\"ausgangsDatum\": \""
					+ formatter.format(transportauftrag.getAusgangsDatum())
					+ "\",\"id\":\"" + transportauftrag.getId()
					+ "\",\"lieferDatum\": \""
					+ formatter.format(DateUtil.now())
					+ "\",\"lieferungErfolgt\": \"true\"}";

			Log.log(HESApiAdapter.class.getSimpleName(), "sending request to url:", url.toString());
			Log.log(HESApiAdapter.class.getSimpleName(), "json message:", input);

			OutputStream os = conn.getOutputStream();
			os.write(input.getBytes());
			os.flush();

			BufferedReader br = new BufferedReader(new InputStreamReader(
					(conn.getInputStream())));

			String output;
			System.out.println("Output from Server .... \n");
			while ((output = br.readLine()) != null) {
				System.out.println(output);
			}

			conn.disconnect();

		} catch (MalformedURLException e) {
			Log.log(HESApiAdapter.class.getSimpleName(), "MalformedURLException", e.getMessage());
		} catch (IOException e) {
		}
	}
}
