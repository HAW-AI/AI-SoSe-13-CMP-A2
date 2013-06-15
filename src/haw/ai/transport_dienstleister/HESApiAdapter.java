package haw.ai.transport_dienstleister;

import haw.ai.hes_rest_konnektor.HESRestApi;
import haw.ai.server.liefer_komponente.Transportauftrag;
import haw.ai.transport_message_protocol.TransportauftragJSON;
import retrofit.RestAdapter;

public class HESApiAdapter {
	private static final String HESRestApiUrl = "http://localhost:8090/";

	private static final RestAdapter restAdapter = new RestAdapter.Builder()
			.setServer(HESRestApiUrl).build();

	private HESRestApi hesRestApi;

	public HESApiAdapter() {
		// Create an instance of our HESRestApi interface.
		this.hesRestApi = restAdapter.create(HESRestApi.class);
	}

	public void updateTransportauftrag(Transportauftrag transportauftrag) {
		TransportauftragJSON json = new TransportauftragJSON(
				transportauftrag.getId(), transportauftrag.getAusgangsDatum(),
				true,
				transportauftrag.getLieferDatum());
		hesRestApi.updateTransportauftrag(transportauftrag.getId(), json);
	}
}
