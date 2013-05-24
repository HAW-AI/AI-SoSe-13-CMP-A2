package haw.ai.server.lager_komponente;

import haw.ai.server.HESServerImpl;
import haw.ai.server.bestell_komponente.BestellFassade;
import haw.ai.server.bestell_komponente.BestellFassadeImpl;

import java.rmi.RemoteException;
import java.rmi.registry.Registry;
import java.rmi.server.UnicastRemoteObject;
import java.util.Date;
import java.util.Map;

public class LagerFassadeImpl extends UnicastRemoteObject implements
		LagerFassade {

	private static final long serialVersionUID = 8714263161717928558L;
	private HESServerImpl hesServer;

	private LagerFassadeImpl(HESServerImpl hesServer) throws RemoteException {
		this.hesServer = hesServer;
	}

	public Produkt erstelleProdukt(String name, int lagerbestand) {
		return LagerRepository.erstelleProdukt(name, lagerbestand);
	}

	public Warenausgangsmeldung erstelleWarenausgangsmeldung(Produkt produkt,
			Date datum, int menge) {
		return LagerRepository.erstelleWarenausgangsmeldung(produkt, datum,
				menge);
	}

	public boolean pruefeLagerbestand(Map<Produkt, Integer> produkte) {
		return LagerBusinessLogik.pruefeLagerbestand(produkte);
	}

	public void save(Produkt produkt) {
		LagerRepository.save(produkt);
	}

	public void save(Warenausgangsmeldung warenausgangsmeldung) {
		LagerRepository.save(warenausgangsmeldung);
	}

	public Produkt findeProdukt(Integer produktId) {
		return LagerRepository.findeProdukt(produktId);
	}

	public Produkt findeProdukt(String produktName) {
		return LagerRepository.findeProdukt(produktName);
	}

	public static LagerFassade createLagerFassade(HESServerImpl hesServerImpl)
			throws RemoteException {
		LagerFassade lagerFassade = new LagerFassadeImpl(hesServerImpl);
		hesServerImpl.getServerRegistry().rebind(LagerFassade.class.getSimpleName(), lagerFassade);
		return lagerFassade;
	}
}
