package haw.ai.hes_rest_konnektor;

import haw.ai.transport_message_protocol.TransportauftragJSON;
import retrofit.http.Body;
import retrofit.http.PUT;
import retrofit.http.Path;

public interface HESRestApi {
	@PUT("/transportauftrag/{id}")
	void updateTransportauftrag(@Path("id") int id, @Body TransportauftragJSON transportauftrag);
}
