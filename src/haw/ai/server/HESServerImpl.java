package haw.ai.server;

import haw.ai.common.Log;
import haw.ai.server.alive.ServerController;
import haw.ai.server.alive.ServerControllerImpl;
import haw.ai.server.background_processing.HapsarPaymentProcessing;
import haw.ai.server.bestell_komponente.BestellFassade;
import haw.ai.server.bestell_komponente.BestellFassadeImpl;
import haw.ai.server.hes_rest_konnektor.HESRestKonnektor;
import haw.ai.server.kunden_komponente.KundenFassade;
import haw.ai.server.kunden_komponente.KundenFassadeImpl;
import haw.ai.server.lager_komponente.LagerFassade;
import haw.ai.server.lager_komponente.LagerFassadeImpl;
import haw.ai.server.liefer_komponente.LieferFassade;
import haw.ai.server.liefer_komponente.LieferFassadeImpl;
import haw.ai.server.rechnungs_komponente.RechnungsFassade;
import haw.ai.server.rechnungs_komponente.RechnungsFassadeImpl;

import java.io.IOException;
import java.net.InetAddress;
import java.net.UnknownHostException;
import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;
import java.util.UUID;

public class HESServerImpl implements HESServer {
	private String instanceName;
	public final static String REGISTRY_HOST = "127.0.0.1";
	public final static Integer REGISTRY_PORT = 1100;
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
	private Integer hesRestServerPort;
	private HESRestKonnektor hesRestKonnektor;
	private HapsarPaymentProcessing hapsarPaymentProcessing;
	private static HESServerImpl hesServer;

	public HESServerImpl(String clientRegistryHostname, Integer clientRegistryPort, Integer serverRegistryPort, Integer hesRestServerPort) {
		this.setInstanceName(UUID.randomUUID().toString());
		this.clientRegistryHostname = clientRegistryHostname;
		this.clientRegistryPort = clientRegistryPort;
		this.serverRegistryPort = serverRegistryPort;
		this.hesRestServerPort = hesRestServerPort;
		createServerRegistry();
	}

	public static HESServer getInstance(String clientRegistryHostname,
			Integer clientRegistryPort, String serverRegistryPort, String hesRestServerPort) {
		if (HESServerImpl.hesServer == null) {
			Integer serverRegistryPortFromString = Integer.valueOf(serverRegistryPort);
			Integer hesRestServerPortFromString = Integer.valueOf(hesRestServerPort);
			HESServerImpl hesServer = new HESServerImpl(clientRegistryHostname,
					clientRegistryPort, serverRegistryPortFromString, hesRestServerPortFromString);
	
			try {
				Log.log(HESServerImpl.class.getName(), hesServer.getInstanceName(), "create", "setzen aller Fassaden");
				hesServer.setBestellFassade(BestellFassadeImpl.createBestellFassade(hesServer));
				hesServer.setKundenFassade(KundenFassadeImpl.createKundenFassade(hesServer));
				hesServer.setLagerFassade(LagerFassadeImpl.createLagerFassade(hesServer));
				hesServer.setLieferFassade(LieferFassadeImpl.createLieferFassade(hesServer));
				hesServer.setRechnungsFassade(RechnungsFassadeImpl.createRechnungsFassade(hesServer));
				hesServer.setServerController(ServerControllerImpl.createServerController(hesServer));
				hesServer.setHesRestServer(new HESRestKonnektor(hesServer.hesRestServerPort));
				hesServer.setHapsarPaymentProcessing(new HapsarPaymentProcessing(hesServer));
				hesServer.hesRestKonnektor.start();
				hesServer.hapsarPaymentProcessing.start();
			} catch (RemoteException e) {
				Log.log(HESServerImpl.class.getName(), hesServer.getInstanceName(), "create", "RemoteException", e.getMessage());
			} catch (IllegalArgumentException e) {
				Log.log(HESServerImpl.class.getName(), hesServer.getInstanceName(), "create", "IllegalArgumentException", e.getMessage());
			} catch (IOException e) {
				Log.log(HESServerImpl.class.getName(), hesServer.getInstanceName(), "create", "IOException", e.getMessage());
			}
			HESServerImpl.hesServer = hesServer;
		}
		return HESServerImpl.hesServer;
	}
	
	private void setHapsarPaymentProcessing(HapsarPaymentProcessing hapsarPaymentProcessing) {
		this.hapsarPaymentProcessing = hapsarPaymentProcessing;
	}

	public static HESServer getInstance() {
		return HESServerImpl.hesServer;
	}

	private void setHesRestServer(HESRestKonnektor hesRestKonnektor) {
		this.hesRestKonnektor = hesRestKonnektor;
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
			Log.log(HESServerImpl.class.getName(), getInstanceName(), "getClientRegistry");
			try {
				this.clientRegistry = LocateRegistry.getRegistry(
						clientRegistryHostname, clientRegistryPort);
				Log.log(HESServerImpl.class.getName(), getInstanceName(), "getClientRegistry", "success");
			} catch (RemoteException e) {
				Log.log(HESServerImpl.class.getName(), getInstanceName(), "getClientRegistry", "RemoteException", e.getMessage());
			}
		}
		return this.clientRegistry;
	}

	private void createServerRegistry() {
		if (this.serverRegistry == null) {
			Log.log(HESServerImpl.class.getName(), getInstanceName(), "createServerRegistry");
			try {
				this.serverRegistryHostname = InetAddress.getLocalHost()
						.getHostName();
				this.serverRegistry = LocateRegistry
						.createRegistry(getServerRegistryPort());
				Log.log(HESServerImpl.class.getName(), getInstanceName(), "createServerRegistry", getServerRegistryHostname(), "" + getServerRegistryPort());
			} catch (RemoteException e) {
				Log.log(HESServerImpl.class.getName(), getInstanceName(), "createServerRegistry", "RemoteException", e.getMessage());
			} catch (UnknownHostException e) {
				Log.log(HESServerImpl.class.getName(), getInstanceName(), "createServerRegistry", "UnknownHostException", e.getMessage());
			}
		}
	}
}
