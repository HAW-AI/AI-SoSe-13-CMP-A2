package haw.ai.server.liefer_komponente;

import haw.ai.common.Log;
import retrofit.RestAdapter;
import retrofit.client.Response;

public class DummyDiensleisterAdapter {
	private static final String DummyDienstleisterApiUrl = "http://localhost:8080/";

	private static final RestAdapter restAdapter = new RestAdapter.Builder()
			.setServer(DummyDienstleisterApiUrl).build();
	
	private DummyDienstleiserApi dummyDienstleisterApi;

	public DummyDiensleisterAdapter() {
		// Create an instance of our FakeDHLApi interface.
		this.dummyDienstleisterApi = restAdapter.create(DummyDienstleiserApi.class);
	}

	public void sendNewTransportauftrag(Transportauftrag transportauftrag) {
		TransportauftragJSON json = new TransportauftragJSON(
				transportauftrag.getId(), transportauftrag.getAusgangsDatum(),
				transportauftrag.isLieferungErfolgt(),
				transportauftrag.getLieferDatum());
		Log.log(DummyDiensleisterAdapter.class.getSimpleName(), "sendNewTransportauftrag", json.toString());
		dummyDienstleisterApi.createNewTransportauftrag(json, new DummyDienstleisterCallback<Response>());
	}
}