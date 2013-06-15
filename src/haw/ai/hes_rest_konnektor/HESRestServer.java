package haw.ai.hes_rest_konnektor;

import haw.ai.transport_message_protocol.TransportauftragJSON;

import javax.ws.rs.Consumes;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

@Path("/hes")
public class HESRestServer {
	@PUT
	@Path("/transportauftrag/{id}")
	@Consumes(MediaType.APPLICATION_JSON)
	public Response receiveUpdatedTransportauftrag(TransportauftragJSON transportauftrag) {
		return Response.status(200).build();
	}
}
