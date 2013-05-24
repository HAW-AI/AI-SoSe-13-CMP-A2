package haw.ai.server;

import haw.ai.server.alive.ServerController;
import haw.ai.server.bestell_komponente.BestellFassade;
import haw.ai.server.bestell_komponente.BestellFassadeImpl;
import haw.ai.server.kunden_komponente.KundenFassade;
import haw.ai.server.kunden_komponente.KundenFassadeImpl;
import haw.ai.server.lager_komponente.LagerFassade;
import haw.ai.server.lager_komponente.LagerFassadeImpl;
import haw.ai.server.liefer_komponente.LieferFassade;
import haw.ai.server.liefer_komponente.LieferFassadeImpl;
import haw.ai.server.rechnungs_komponente.RechnungsFassade;
import haw.ai.server.rechnungs_komponente.RechnungsFassadeImpl;

import java.net.InetAddress;
import java.net.UnknownHostException;
import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;
import java.util.UUID;

public class HESServerImpl implements HESServer {
	private String instanceName;
	public final static String REGISTRY_HOST = "127.0.0.1";
	public final static Integer REGISTRY_PORT = 1099;
	private BestellFassade bestellFassade;
	private KundenFassade kundenFassade;
	private LagerFassade lagerFassade;
	private LieferFassade lieferFassade;
	private RechnungsFassade rechnungsFassade;
	private ServerController serverController;
	private String serverRegistryHostname;
	private Integer serverRegistryPort;
	public final String clientRegistryHostname;
	public final Integer clientRegistryPort;
	private Registry clientRegistry = null;
	private Registry serverRegistry = null;

	public HESServerImpl(String clientRegistryHostname, Integer clientRegistryPort) {
		this.setInstanceName(UUID.randomUUID().toString());
		this.clientRegistryHostname = clientRegistryHostname;
		this.clientRegistryPort = clientRegistryPort;
		createServerRegistry();
		
	}

	public static HESServer create(String clientRegistryHostname,
			Integer clientRegistryPort) {
		HESServerImpl hesServer = new HESServerImpl(clientRegistryHostname,
				clientRegistryPort);

		try {
			hesServer.setBestellFassade(BestellFassadeImpl.createBestellFassade(hesServer));
			hesServer.setKundenFassade(KundenFassadeImpl.createKundenFassade(hesServer));
			hesServer.setLagerFassade(LagerFassadeImpl.createLagerFassade(hesServer));
			hesServer.setLieferFassade(LieferFassadeImpl.createLieferFassade(hesServer));
			hesServer.setRechnungsFassade(RechnungsFassadeImpl.createRechnungsFassade(hesServer));
			hesServer.setServerController(ServerController.createServerController(hesServer));
		} catch (RemoteException e) {
		}
		return hesServer;
	}

	public Registry getServerRegistry() {
		return this.serverRegistry;
	}

	private void setBestellFassade(BestellFassade bestellFassade) {
		this.bestellFassade = bestellFassade;
	}

	public void setKundenFassade(KundenFassade kundenFassade) {
		this.kundenFassade = kundenFassade;
	}

	private void setLagerFassade(LagerFassade lagerFassade) {
		this.lagerFassade = lagerFassade;
	}

	private void setLieferFassade(LieferFassade lieferFassade) {
		this.lieferFassade = lieferFassade;
	}

	private void setRechnungsFassade(RechnungsFassade rechnungsFassade) {
		this.rechnungsFassade = rechnungsFassade;
	}

	private void setServerController(ServerController serverController) {
		this.serverController = serverController;
	}
	
	public ServerController getServerController() {
		return this.serverController;
	}
	
	public BestellFassade getBestellFassade() {
		return this.bestellFassade;
	}

	public KundenFassade getKundenFassade() {
		return this.kundenFassade;
	}

	public LagerFassade getLagerFassade() {
		return this.lagerFassade;
	}

	public LieferFassade getLieferFassade() {
		return this.lieferFassade;
	}

	public RechnungsFassade getRechnungsFassade() {
		return this.rechnungsFassade;
	}

	public String getInstanceName() {
		return instanceName;
	}

	private void setInstanceName(String instanceName) {
		this.instanceName = instanceName;
	}

	public String getServerRegistryHostname() {
		return this.serverRegistryHostname;
	}

	public Integer getServerRegistryPort() {
		return this.serverRegistryPort;
	}

	public Registry getClientRegistry() {
		if (this.clientRegistry == null) {
			try {
				this.clientRegistry = LocateRegistry.getRegistry(
						clientRegistryHostname, clientRegistryPort);
			} catch (RemoteException e) {
			}
		}
		return this.clientRegistry;
	}

	private void createServerRegistry() {
		if (this.serverRegistry == null) {
			try {
				this.serverRegistryHostname = InetAddress.getLocalHost()
						.getHostName();
				this.serverRegistryPort = REGISTRY_PORT; // FIXME needs to be
															// increased somehow
				this.serverRegistry = LocateRegistry
						.createRegistry(getServerRegistryPort());
			} catch (RemoteException e) {
			} catch (UnknownHostException e) {
			}
		}
	}
}
