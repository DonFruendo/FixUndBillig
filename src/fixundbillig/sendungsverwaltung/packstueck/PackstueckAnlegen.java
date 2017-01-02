package fixundbillig.sendungsverwaltung.packstueck;

import fixundbillig.sendungsverwaltung.dao.DAO_Packstueck;
import fixundbillig.sendungsverwaltung.interfaces.IPackstueckAnlegen;
import fixundbillig.sendungsverwaltung.to.PackstueckTO;

public class PackstueckAnlegen implements IPackstueckAnlegen {

	public void packstueckdatenSpeichern(PackstueckTO packstueck) {
		// DB ACESSENATOR
		DAO_Packstueck dao = new DAO_Packstueck(packstueck);
		dao.packstueckdatenAnlegen();
	}

	public void packstueckAnlegen(PackstueckTO packstueck) {
		
		packstueckdatenSpeichern(packstueck);
		
	}
	
}
