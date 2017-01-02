package fixundbillig.sendungsverwaltung.sendung;

import fixundbillig.sendungsverwaltung.control.PackstueckManager;
import fixundbillig.sendungsverwaltung.control.SendungManager;
import fixundbillig.sendungsverwaltung.exceptions.ValidationException;
import fixundbillig.sendungsverwaltung.to.PackstueckTO;
import fixundbillig.sendungsverwaltung.to.SendungTO;
import fixundbillig.sendungsverwaltung.utils.Adresse;

public class SendungAnlegen {

	public boolean adresseValidieren(Adresse adresse) {
		// TODO
		return adresse.isValid();
	}

	public void sendungsdatenSpeichern(SendungTO sendung) {
		// TODO Create DAO


        // TODO Save DAO
	}

	public boolean sendungAnlegen(SendungTO sendung) throws ValidationException {
		if(!adresseValidieren(sendung.zielort)) {
		    throw new ValidationException(sendung.zielort + " hat die Validierung nicht bestanden.");
        }

        PackstueckManager pManager = PackstueckManager.getInstance();
        for(PackstueckTO packstueck : sendung.packstuecke) {
            pManager.addPackstueck(packstueck);
        }
        SendungManager sendungManager = SendungManager.getInstance();
        sendungManager.sendungAnlegen(sendung);
        sendungsdatenSpeichern(sendung);

        return true;
    }
	
}
