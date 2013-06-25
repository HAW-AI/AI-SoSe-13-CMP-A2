package haw.ai.transport_dienstleister;

import haw.ai.server.liefer_komponente.TransportauftragJSON;
import retrofit.RestAdapter;

public class HESApiAdapter {
	private static final String HESRestApiUrl = "http://localhost:8090/";

	private static final RestAdapter restAdapter = new RestAdapter.Builder()
			.setServer(HESRestApiUrl).build();

	private HESApi hesRestApi;

	public HESApiAdapter() {
		// Create an instance of our HESRestApi interface.
		this.hesRestApi = restAdapter.create(HESApi.class);
	}

	public void updateTransportauftrag(TransportauftragJSON transportauftrag, HESApiCallback hesApiCallback) {
		TransportauftragJSON json = new TransportauftragJSON(
				transportauftrag.getId(), transportauftrag.getAusgangsDatum(),
				true,
				transportauftrag.getLieferDatum());
		System.out.println(HESApiAdapter.class.getSimpleName() + ": send updateTransportauftrag");
		System.out.println("transportauftragID: " + transportauftrag.getId());
		hesRestApi.updateTransportauftrag(transportauftrag.getId(), json, hesApiCallback);
	}
}
