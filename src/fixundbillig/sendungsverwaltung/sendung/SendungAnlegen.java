package fixundbillig.sendungsverwaltung.sendung;

import fixundbillig.sendungsverwaltung.control.PackstueckManager;
import fixundbillig.sendungsverwaltung.exceptions.ValidationException;
import fixundbillig.sendungsverwaltung.to.*;
import fixundbillig.sendungsverwaltung.utils.Adresse;

public class SendungAnlegen {

	public boolean adresseValidieren(Adresse adresse) {
		// TODO 
		return false;
	}

	public void sendungsdatenSpeichern(SendungTO sendung) {
		// TODO
	}

	public void sendungAnlegen(SendungTO sendung) throws ValidationException {
		if(!adresseValidieren(sendung.zielort)) {
		    throw new ValidationException("Adresse(" + sendung.zielort + ") fehlerhaft");
        }

        PackstueckManager pManager = PackstueckManager.getInstance();
        for(PackstueckTO packstueck : sendung.packstuecke) {
            pManager.addPackstueck(packstueck);
        }
        sendungsdatenSpeichern(sendung);
    }
	
}
