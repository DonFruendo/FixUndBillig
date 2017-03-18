package fixundbillig.sendungsverwaltung.core.usecases;

import fixundbillig.sendungsverwaltung.core.control.SendungManager;
import fixundbillig.sendungsverwaltung.core.exceptions.ValidationException;
import fixundbillig.sendungsverwaltung.core.factory.SendungsverwaltungFactory;
import fixundbillig.sendungsverwaltung.data.interfaces.IPackstueckAnlegen;
import fixundbillig.sendungsverwaltung.data.interfaces.ISendungAnlegen;
import fixundbillig.sendungsverwaltung.data.packstueck.PackstueckTO;
import fixundbillig.sendungsverwaltung.data.sendung.DAO_Sendung;
import fixundbillig.sendungsverwaltung.data.sendung.SendungTO;
import fixundbillig.sendungsverwaltung.data.utils.Adresse;
import fixundbillig.sendungsverwaltung.data.utils.Logger;

/**
 * Created by Don on 07.01.2017.
 *
 * @author Don
 * @since 07.01.2017
 */
public class SendungAnlegen implements ISendungAnlegen {

	public boolean adresseValidieren(Adresse adresse) {
		return adresse.isValid();
	}

	public void sendungsdatenSpeichern(SendungTO sendung) {
		// Create DAO
        DAO_Sendung dao_sendung = new DAO_Sendung(sendung);

        // Save DAO
        dao_sendung.sendungsdatenAnlegen();
        //Logger.info("SQL: " + s);
    }

	public boolean sendungAnlegen(SendungTO sendungTO) {
	    // Validate address
		if(!adresseValidieren(sendungTO.zielort)) {
		    Logger.err(new ValidationException(sendungTO.zielort + " hat die Validierung nicht bestanden."));
		    return false;
        }

        // Get the usecase
        IPackstueckAnlegen packstueckAnlegen = SendungsverwaltungFactory.getInstance().getPackstueckAnlegen();
		// Find the package
        for(PackstueckTO packstueck : sendungTO.packstuecke) {
            // Switch to package-usecase
            packstueckAnlegen.packstueckAnlegen(packstueck);
        }
        // Get the manager
        SendungManager sendungManager = SendungManager.getInstance();
        // Save order to manager
        boolean success = sendungManager.sendungAnlegen(sendungTO);

        if(success) {
            // Save order to DWH
            sendungsdatenSpeichern(sendungTO);
        }

        // Report success
        return true;
    }
}
