package fixundbillig;

import fixundbillig.sendungsverwaltung.core.control.SendungManager;
import fixundbillig.sendungsverwaltung.core.factory.SendungsverwaltungFactory;
import fixundbillig.sendungsverwaltung.data.interfaces.ISendungAnlegen;
import fixundbillig.sendungsverwaltung.data.packstueck.PackstueckTO;
import fixundbillig.sendungsverwaltung.data.sendung.SendungTO;
import fixundbillig.sendungsverwaltung.data.utils.Adresse;
import fixundbillig.sendungsverwaltung.data.utils.Logger;
import fixundbillig.sendungsverwaltung.data.utils.Paketart;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

class Launcher {
    public static void main(String[] args) {
        Logger.info("Hello World!");
        SendungsverwaltungFactory fact = SendungsverwaltungFactory.getInstance();

        Logger.info("Neue Sendung wird angelegt");
        SendungTO sendung = new SendungTO();
        sendung.anlagedatum = LocalDate.now();
        sendung.kundenNr = "HelloWorld1232";
        sendung.sendungsnummer = "blabla44";
        sendung.transportauftrag = "getitthereornot";
        sendung.zielort = new Adresse("Fritz-Wägeler-Str.", "482a", "42567", "Berlin Hafen");
        List<PackstueckTO> liste = new ArrayList<>();
        liste.add(new PackstueckTO("1", 10, 10, 42, sendung.sendungsnummer, "Kempen", Paketart.Karton));
        liste.add(new PackstueckTO("1", 100, 100, 420, sendung.sendungsnummer + "sadflkj", "Kempenasd", Paketart.Palette));
        sendung.packstuecke = liste;

        Logger.info(sendung + " soll angelegt werden");


        ISendungAnlegen sendungAnlegen = fact.getSendungAnlegen();

        boolean success = sendungAnlegen.sendungAnlegen(sendung);
        Logger.info("Sendung wurde " + (success ? "erfolgreich" : "nicht") + " angelegt");

        success = sendungAnlegen.sendungAnlegen(sendung);
        Logger.info("Sendung wurde " + (success ? "erfolgreich" : "nicht") + " angelegt");

        SendungTO sendung1 = new SendungTO(sendung);
        sendung1.sendungsnummer = "sldk";
        success = sendungAnlegen.sendungAnlegen(sendung1);
        Logger.info("Sendung wurde " + (success ? "erfolgreich" : "nicht") + " angelegt");

        Logger.info(fact.getSendungSuchen().sendungSuchen(sendung1.sendungsnummer));

        for(int i = 0; i < 50; i++) {
            Logger.debug("Ihre Sendungsnummer ist: " + SendungManager.generateSendungsnummer());
        }

        fact.destroy();
        Logger.info("Program complete");
    }

}
