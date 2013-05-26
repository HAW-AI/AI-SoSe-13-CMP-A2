package haw.ai.server.lager_komponente;

import haw.ai.common.Log;
import haw.ai.server.HESServerImpl;

import java.rmi.RemoteException;
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
		Log.log(LagerFassadeImpl.class.getName(), hesServer.getInstanceName(), "erstelleProdukt");
		return LagerRepository.erstelleProdukt(name, lagerbestand);
	}

	public Warenausgangsmeldung erstelleWarenausgangsmeldung(Produkt produkt,
			Date datum, int menge) {
		Log.log(LagerFassadeImpl.class.getName(), hesServer.getInstanceName(), "erstelleWarenausgangsmeldung");
		return LagerRepository.erstelleWarenausgangsmeldung(produkt, datum,
				menge);
	}

	public boolean pruefeLagerbestand(Map<Produkt, Integer> produkte) {
		Log.log(LagerFassadeImpl.class.getName(), hesServer.getInstanceName(), "pruefeLagerbestand");
		return LagerBusinessLogik.pruefeLagerbestand(produkte);
	}

	public void save(Produkt produkt) {
		Log.log(LagerFassadeImpl.class.getName(), hesServer.getInstanceName(), "save", "produkt", produkt.getName());
		LagerRepository.save(produkt);
	}

	public void save(Warenausgangsmeldung warenausgangsmeldung) {
		Log.log(LagerFassadeImpl.class.getName(), hesServer.getInstanceName(), "save", "warenausgangsmeldung", "" + warenausgangsmeldung.getId());
		LagerRepository.save(warenausgangsmeldung);
	}

	public Produkt findeProdukt(Integer produktId) {
		Log.log(LagerFassadeImpl.class.getName(), hesServer.getInstanceName(), "findeProdukt", "" + produktId);
		return LagerRepository.findeProdukt(produktId);
	}

	public Produkt findeProdukt(String produktName) {
		Log.log(LagerFassadeImpl.class.getName(), hesServer.getInstanceName(), "findeProdukt", produktName);
		return LagerRepository.findeProdukt(produktName);
	}

	public static LagerFassade createLagerFassade(HESServerImpl hesServerImpl)
			throws RemoteException {
		Log.log(LagerFassadeImpl.class.getName(), hesServerImpl.getInstanceName(), "createLagerFassade");
		LagerFassade lagerFassade = new LagerFassadeImpl(hesServerImpl);
		Log.log(LagerFassadeImpl.class.getName(), hesServerImpl.getInstanceName(), "createLagerFassade", "binding in serverregistry");
		hesServerImpl.getServerRegistry().rebind(LagerFassade.class.getSimpleName(), lagerFassade);
		return lagerFassade;
	}
}
