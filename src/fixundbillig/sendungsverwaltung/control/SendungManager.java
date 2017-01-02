package fixundbillig.sendungsverwaltung.control;

import fixundbillig.sendungsverwaltung.sendung.Sendung;
import fixundbillig.sendungsverwaltung.to.SendungTO;

import java.util.ArrayList;
import java.util.List;

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

    private List<Sendung> list;

    private SendungManager() {
        list = new ArrayList<>();
    }

    public void sendungAnlegen(SendungTO sendung) {
        list.add(new Sendung(sendung));
    }

    public SendungTO sendungSuchenPerSendungsNr(String sendungsnummer) {
        Sendung find = null;
        for(Sendung sendung : list) {
            if(sendungsnummer.equals(sendung.getSendungsnummer())) {
                find = sendung;
                break;
            }
        }
        if(find == null) {
            return null;
        }

        return find.toTO();
    }



}
