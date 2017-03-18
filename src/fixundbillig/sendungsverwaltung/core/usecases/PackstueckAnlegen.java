package fixundbillig.sendungsverwaltung.core.usecases;

import fixundbillig.sendungsverwaltung.core.control.PackstueckManager;
import fixundbillig.sendungsverwaltung.data.interfaces.IPackstueckAnlegen;
import fixundbillig.sendungsverwaltung.data.packstueck.DAO_Packstueck;
import fixundbillig.sendungsverwaltung.data.packstueck.PackstueckTO;

/**
 * Created by Don on 07.01.2017.
 *
 * @author Don
 * @since 07.01.2017
 */
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
		boolean success = pManager.addPackstueck(packstueck);

		if(success) {
			// Save package to DWH
			packstueckdatenSpeichern(packstueck);
		}
		
	}
	
}
