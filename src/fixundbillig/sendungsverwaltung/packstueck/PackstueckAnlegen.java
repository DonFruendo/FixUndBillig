package fixundbillig.sendungsverwaltung.packstueck;

import fixundbillig.sendungsverwaltung.control.PackstueckManager;
import fixundbillig.sendungsverwaltung.dao.DAO_Packstueck;
import fixundbillig.sendungsverwaltung.interfaces.IPackstueckAnlegen;
import fixundbillig.sendungsverwaltung.to.PackstueckTO;

public class PackstueckAnlegen implements IPackstueckAnlegen {

	public void packstueckdatenSpeichern(PackstueckTO packstueck) {
		// DB ACCESSENATOR
		DAO_Packstueck dao = new DAO_Packstueck(packstueck);
		dao.packstueckdatenAnlegen();
	}

	public void packstueckAnlegen(PackstueckTO packstueck) {
        // Get the manager
        PackstueckManager pManager = PackstueckManager.getInstance();

        // Save package to the manager
        pManager.addPackstueck(packstueck);

        // Save package to DWH
		packstueckdatenSpeichern(packstueck);
		
	}
	
}
