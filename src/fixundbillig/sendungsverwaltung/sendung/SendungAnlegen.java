package fixundbillig.sendungsverwaltung.sendung;

import fixundbillig.sendungsverwaltung.control.PackstueckManager;
import fixundbillig.sendungsverwaltung.control.SendungManager;
import fixundbillig.sendungsverwaltung.dao.DAO_Sendung;
import fixundbillig.sendungsverwaltung.exceptions.ValidationException;
import fixundbillig.sendungsverwaltung.packstueck.PackstueckAnlegen;
import fixundbillig.sendungsverwaltung.to.PackstueckTO;
import fixundbillig.sendungsverwaltung.to.SendungTO;
import fixundbillig.sendungsverwaltung.utils.Adresse;
import fixundbillig.sendungsverwaltung.utils.Logger;

public class SendungAnlegen {

	public boolean adresseValidieren(Adresse adresse) {
		return adresse.isValid();
	}

	public void sendungsdatenSpeichern(SendungTO sendung) {
		// Create DAO
        DAO_Sendung dao_sendung = new DAO_Sendung(sendung);

        // Save DAO
        String s = dao_sendung.sendungsdatenAnlegen();
        Logger.log("SQL: " + s);
    }

	public boolean sendungAnlegen(SendungTO sendung) throws ValidationException {
	    // Validate address
		if(!adresseValidieren(sendung.zielort)) {
		    throw new ValidationException(sendung.zielort + " hat die Validierung nicht bestanden.");
        }

        // Get the usecase
        PackstueckAnlegen packstueckAnlegen = new PackstueckAnlegen();
		// Find the package
        for(PackstueckTO packstueck : sendung.packstuecke) {
            // Switch to package-usecase
            packstueckAnlegen.packstueckAnlegen(packstueck);
        }
        // Get the manager
        SendungManager sendungManager = SendungManager.getInstance();
        // Save order to manager
        sendungManager.sendungAnlegen(sendung);
        // Save order to DWH
        sendungsdatenSpeichern(sendung);

        // Report success
        return true;
    }
	
}
