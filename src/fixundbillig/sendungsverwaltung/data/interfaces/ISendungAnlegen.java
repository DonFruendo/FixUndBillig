package fixundbillig.sendungsverwaltung.data.interfaces;

import fixundbillig.sendungsverwaltung.data.sendung.SendungTO;
import fixundbillig.sendungsverwaltung.data.utils.Adresse;

@SuppressWarnings("ALL")
public interface ISendungAnlegen {
	boolean adresseValidieren(Adresse adresse);
	void sendungsdatenSpeichern(SendungTO sendung);
	boolean sendungAnlegen(SendungTO sendung);
}
