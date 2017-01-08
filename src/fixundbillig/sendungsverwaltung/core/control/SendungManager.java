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
    private static SendungManager ourInstance;

    public static SendungManager getInstance() {
        if (ourInstance == null) {
            ourInstance = new SendungManager();
        }
        return ourInstance;
    }

    private final Set<Sendung> sendungen;
    private final ISQLConnector connector;

    private SendungManager() {
        sendungen = new HashSet<>();
        connector = SQLManager.getSQLConnector();
    }

    public void init() {
        String query = "SELECT ID FROM " + DAO_Sendung.tabelle + ";";
        ResultSet resultSet = connector.getQuery(query);
        try {
            while (resultSet.next()) {
                String id = resultSet.getString("ID");
                DAO_Sendung dao = new DAO_Sendung();
                dao.sendungsdatenSuchenPerId(id);
                Sendung sendung = new Sendung(dao.toTO());
                sendungen.add(sendung);
            }
            Logger.info("Sendung Initialisierung abgeschlossen");
        } catch (Exception e) {
            Logger.err(e.getMessage());
        }
    }

    public void destroy() {
        connector.disconnect();
    }

    public boolean sendungAnlegen(SendungTO sendungTO) {
        Sendung sendung = new Sendung(sendungTO);
        //boolean setNotContaining = true;
        for (Sendung s : sendungen) {
            if (s.equals(sendung)) {
                boolean updated = s.update(sendung);
                if (updated) {
                    Logger.info("Updated: " + sendung);
                } else {
                    Logger.info("Exists already: " + sendung);
                }
                return updated;
            }
        }
        sendungen.add(sendung);
        Logger.info("Added: " + sendung);
        return true;
    }

    public Sendung sendungSuchenPerSendungsNr(String sendungsnummer) {
        if (sendungsnummer == null) {
            return null;
        }

        Sendung find = null;
        for (Sendung sendung : sendungen) {
            if (sendungsnummer.equals(sendung.getSendungsnummer())) {
                find = sendung;
                break;
            }
        }

        return find;
    }


}
