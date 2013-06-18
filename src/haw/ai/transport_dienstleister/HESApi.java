package haw.ai.transport_dienstleister;

import haw.ai.server.liefer_komponente.TransportauftragJSON;
import retrofit.http.Body;
import retrofit.http.PUT;
import retrofit.http.Path;

public interface HESApi {
	@PUT("/transportauftrag/{id}")
	void updateTransportauftrag(@Path("id") int id, @Body TransportauftragJSON transportauftrag);
}
