package fixundbillig.sendungsverwaltung.core.control;

import fixundbillig.sendungsverwaltung.data.interfaces.ISQLConnector;
import fixundbillig.sendungsverwaltung.data.sendung.DAO_Sendung;
import fixundbillig.sendungsverwaltung.data.sendung.Sendung;
import fixundbillig.sendungsverwaltung.data.sendung.SendungTO;
import fixundbillig.sendungsverwaltung.data.utils.Logger;

import java.sql.ResultSet;
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
        ISQLConnector connector = SQLManager.getSQLConnector();
        String query = "SELECT ID FROM " + DAO_Sendung.tabelle + ";";
        ResultSet resultSet = connector.getQuery(query);
        try {
            while(resultSet.next()) {
                String id = resultSet.getString("ID");
                DAO_Sendung dao = new DAO_Sendung();
                dao.sendungsdatenSuchenPerId(id);
                Sendung sendung = new Sendung(dao.toTO());
                sendungen.add(sendung);
            }
            Logger.info("Initialisierung abgeschlossen");
        } catch (Exception e) {
            Logger.err(e.getMessage());
        }
    }

    public boolean sendungAnlegen(SendungTO sendungTO) {
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
            Logger.info("Added: " + sendung);
            return true;
        } else {
            Logger.info("Exists already: " + sendung);}
            return false;
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
