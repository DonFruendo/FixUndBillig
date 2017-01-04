package fixundbillig.sendungsverwaltung.data.interfaces;

import fixundbillig.sendungsverwaltung.core.exceptions.ValidationException;
import fixundbillig.sendungsverwaltung.data.sendung.SendungTO;
import fixundbillig.sendungsverwaltung.data.utils.Adresse;

public interface ISendungAnlegen {
	boolean adresseValidieren(Adresse adresse);
	void sendungsdatenSpeichern(SendungTO sendung);
	boolean sendungAnlegen(SendungTO sendung) throws ValidationException;
}
