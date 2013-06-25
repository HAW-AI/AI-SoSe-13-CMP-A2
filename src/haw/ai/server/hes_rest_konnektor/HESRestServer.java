package haw.ai.server.hes_rest_konnektor;

import haw.ai.common.Log;
import haw.ai.server.HESServerImpl;
import haw.ai.server.liefer_komponente.Transportauftrag;
import haw.ai.server.liefer_komponente.TransportauftragJSON;

import java.rmi.RemoteException;

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
	public Response receiveUpdatedTransportauftrag(TransportauftragJSON transportauftragJson) throws RemoteException {
		Transportauftrag transportauftrag = HESServerImpl.getInstance().getLieferFassade().findTransportauftrag(transportauftragJson.getId()); 
		try {
			Log.log(HESRestServer.class.getSimpleName(), "receiveUpdatedTransportauftrag", transportauftrag.toString());
			HESServerImpl.getInstance().getLieferFassade().markiereTransportErfolgt(transportauftrag);
			HESServerImpl.getInstance().getLieferFassade().save(transportauftrag);
		} catch (Exception e) {
			Log.log(HESRestServer.class.getName(), "receiveUpdatedTransportauftrag", "RemoteException", e.getMessage());
		}
		return Response.status(200).build();
	}
}
