package fixundbillig;

import fixundbillig.sendungsverwaltung.exceptions.ValidationException;
import fixundbillig.sendungsverwaltung.sendung.SendungAnlegen;
import fixundbillig.sendungsverwaltung.to.PackstueckTO;
import fixundbillig.sendungsverwaltung.to.SendungTO;
import fixundbillig.sendungsverwaltung.utils.Adresse;
import fixundbillig.sendungsverwaltung.utils.Paketart;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class Launcher {
	public static void main(String[] args) {
		System.out.println("Hello World!");

		SendungTO sendung = new SendungTO();
		sendung.Anlagedatum = new Date();
		sendung.kundenNr = "HelloWorld1232";
		sendung.sendungsnummer = "blabla44";
		sendung.transportauftrag = "getitthereornot";
		sendung.zielort = new Adresse("Kruxhalfen", "4827", "234lkj");
		List<PackstueckTO> liste = new ArrayList<>();
		liste.add(new PackstueckTO(10,1, 42, 10, sendung, "Kempen", Paketart.Karton));
		sendung.packstuecke = liste;

		// TODO Factory
		SendungAnlegen sendungAnlegen = new SendungAnlegen();
		try {
			sendungAnlegen.sendungAnlegen(sendung);
		} catch (ValidationException e) {
			e.printStackTrace();
		}
	}
}
