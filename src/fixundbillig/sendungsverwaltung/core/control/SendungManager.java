package fixundbillig.sendungsverwaltung.core.control;

import fixundbillig.sendungsverwaltung.data.sendung.Sendung;
import fixundbillig.sendungsverwaltung.data.sendung.SendungTO;
import fixundbillig.sendungsverwaltung.data.utils.Logger;

import java.util.HashSet;
import java.util.Set;

/**
 * Created by Don on 02.01.2017.
 *
 * @author Don
 * @since 02.01.2017
 */
public class SendungManager {
    private static SendungManager ourInstance = new SendungManager();

    public static SendungManager getInstance() {
        return ourInstance;
    }

    private Set<Sendung> sendungen;

    private SendungManager() {
        sendungen = new HashSet<>();
    }

    public void sendungAnlegen(SendungTO sendungTO) {
        Sendung sendung = new Sendung(sendungTO);
        boolean setNotContaining = true;
        for(Sendung s: sendungen) {
            if(s.equals(sendung)) {
                setNotContaining = false;
                break;
            }
        }
        if(setNotContaining) {
            sendungen.add(sendung);
            Logger.log("Added: " + sendung);
        } else {
            Logger.log("Exists already: " + sendung);}
    }

    public Sendung sendungSuchenPerSendungsNr(String sendungsnummer) {
        if(sendungsnummer == null) {
            return null;
        }

        Sendung find = null;
        for(Sendung sendung : sendungen) {
            if(sendungsnummer.equals(sendung.getSendungsnummer())) {
                find = sendung;
                break;
            }
        }
        /*
        if(find == null) {
            return null;
        } //*/

        return find;
    }



}
