package haw.ai.transport_dienstleister;

import haw.ai.common.Log;
import haw.ai.server.liefer_komponente.TransportauftragJSON;

import javax.ws.rs.Consumes;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

@Path("/dummydienstleister")
public class DummyDienstleisterServer {
		
	@POST
	@Path("/transportauftrag")
	@Consumes(MediaType.APPLICATION_JSON)
	public Response receiveNewTransportauftrag(TransportauftragJSON transportauftrag) {
		DummyDienstleisterKonnektor.addTransportauftrag(transportauftrag);
		Log.log(DummyDienstleisterServer.class.getSimpleName(), "receiveNewTransportauftrag", transportauftrag.toString());
		return Response.status(202).build();
	}	
}
