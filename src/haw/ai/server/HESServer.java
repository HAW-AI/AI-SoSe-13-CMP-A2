package haw.ai.server;

import haw.ai.server.bestell_komponente.BestellFassade;
import haw.ai.server.kunden_komponente.KundenFassade;
import haw.ai.server.lager_komponente.LagerFassade;
import haw.ai.server.liefer_komponente.LieferFassade;
import haw.ai.server.rechnungs_komponente.RechnungsFassade;

import java.rmi.Remote;

public interface HESServer extends Remote {
	public BestellFassade getBestellFassade();
	public KundenFassade getKundenFassade();
	public LagerFassade getLagerFassade();
	public LieferFassade getLieferFassade();
	public RechnungsFassade getRechnungsFassade();
}
