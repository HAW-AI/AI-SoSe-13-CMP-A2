package haw.ai.transport_dienstleister;

import java.util.List;

import haw.ai.server.liefer_komponente.TransportauftragJSON;

public class UpdateTransportauftraegeThread extends Thread {

	private HESApiAdapter adapter;
	
	public UpdateTransportauftraegeThread(HESApiAdapter adapter) {
		this.adapter = adapter;
	}
	
	public void run() {
		while (!this.isInterrupted()) {
			try {
				Thread.sleep(5000);
				List<TransportauftragJSON> auftraege = DummyDienstleisterKonnektor.getTransportauftraege();
				if (!auftraege.isEmpty()) {
					this.adapter.updateTransportauftrag(auftraege.get(0));
					auftraege.remove(0);
				}
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
			
		}
	}
	
}
