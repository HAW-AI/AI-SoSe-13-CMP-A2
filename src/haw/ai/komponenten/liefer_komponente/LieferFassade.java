package haw.ai.komponenten.liefer_komponente;

import java.util.Date;

import haw.ai.komponenten.bestell_komponente.Auftrag;

public class LieferFassade {

	public static Lieferung erstelleLieferung(Auftrag auftrag) {
		return LieferRepository.erstelleLieferung(auftrag);
	}
	
	public static Transportauftrag erstelleTransportauftrag(Lieferung lieferung,
			Date ausgangsDatum, boolean lieferungErfolgt, Date lieferDatum,
			String transportDienstleister) {
		return LieferRepository.erstelleTransportauftrag(lieferung, ausgangsDatum, lieferungErfolgt, lieferDatum, transportDienstleister);
	}
	
}
