package haw.ai.transport_dienstleister;

import haw.ai.transport_message_protocol.TransportauftragJSON;
import retrofit.http.Body;
import retrofit.http.POST;

public interface DummyDienstleiserApi {
	@POST("/transportauftrag")
	void createNewTransportauftrag(@Body TransportauftragJSON transportauftrag);
}
