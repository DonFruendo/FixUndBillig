package fixundbillig.sendungsverwaltung.core.usecases;

import fixundbillig.sendungsverwaltung.core.control.PackstueckManager;
import fixundbillig.sendungsverwaltung.data.interfaces.IPackstueckAnlegen;
import fixundbillig.sendungsverwaltung.data.packstueck.DAO_Packstueck;
import fixundbillig.sendungsverwaltung.data.packstueck.PackstueckTO;

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
