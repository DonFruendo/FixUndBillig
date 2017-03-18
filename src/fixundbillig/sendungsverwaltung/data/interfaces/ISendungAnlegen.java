package fixundbillig.sendungsverwaltung.data.interfaces;

import fixundbillig.sendungsverwaltung.data.sendung.SendungTO;
import fixundbillig.sendungsverwaltung.data.utils.Adresse;

/**
 * Created by Don on 21.12.2016.
 *
 * @author Don
 * @since 21.21.2016
 */
@SuppressWarnings("ALL")
public interface ISendungAnlegen {
	boolean adresseValidieren(Adresse adresse);
	void sendungsdatenSpeichern(SendungTO sendung);
	boolean sendungAnlegen(SendungTO sendung);
}
