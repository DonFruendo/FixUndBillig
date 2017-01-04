package fixundbillig;

import fixundbillig.sendungsverwaltung.core.exceptions.ValidationException;
import fixundbillig.sendungsverwaltung.core.usecases.SendungAnlegen;
import fixundbillig.sendungsverwaltung.data.packstueck.PackstueckTO;
import fixundbillig.sendungsverwaltung.data.sendung.SendungTO;
import fixundbillig.sendungsverwaltung.data.utils.Adresse;
import fixundbillig.sendungsverwaltung.data.utils.Logger;
import fixundbillig.sendungsverwaltung.data.utils.Paketart;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class Launcher {
	public static void main(String[] args) {
		Logger.log("Hello World!");

		Logger.log("Neue Sendung wird angelegt");
		SendungTO sendung = new SendungTO();
		sendung.anlagedatum = new Date();
		sendung.kundenNr = "HelloWorld1232";
		sendung.sendungsnummer = "blabla44";
		sendung.transportauftrag = "getitthereornot";
		sendung.zielort = new Adresse("Fritz-Wägeler-Str.", "482a", "42567", "Berlin Hafen");
		List<PackstueckTO> liste = new ArrayList<>();
		liste.add(new PackstueckTO(10,1, 42, 10, sendung.sendungsnummer, "Kempen", Paketart.Karton));
		liste.add(new PackstueckTO(100,1, 420, 100, sendung.sendungsnummer+"sadflkj", "Kempenasd", Paketart.Palette));
		sendung.packstuecke = liste;

		Logger.log(sendung + " soll angelegt werden");


		// TODO Factory
		SendungAnlegen sendungAnlegen = new SendungAnlegen();
		try {
			boolean success = sendungAnlegen.sendungAnlegen(sendung);
			Logger.log("Sendung wurde" + (success?" ":" nicht ") + "erfolgreich angelegt");
		} catch (ValidationException e) {
			Logger.err(e.getMessage());
		}
	}

}
