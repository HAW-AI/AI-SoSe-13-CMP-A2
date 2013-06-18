package haw.ai.server.liefer_komponente;

import retrofit.http.Body;
import retrofit.http.POST;

public interface DummyDienstleiserApi {
	@POST("/dummydienstleister/transportauftrag")
	void createNewTransportauftrag(@Body TransportauftragJSON transportauftrag);
}
