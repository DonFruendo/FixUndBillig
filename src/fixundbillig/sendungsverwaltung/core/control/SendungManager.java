package fixundbillig.sendungsverwaltung.core.control;

import fixundbillig.sendungsverwaltung.core.config.Configurator;
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

    public static String generateSendungsnummer() {
        Set<String> set = new HashSet<>();
        SendungManager m = SendungManager.getInstance();
        for(Sendung s : m.getSendungen()) {
            set.add(s.getSendungsnummer());
        }
        String randomString;
        boolean existing = false;
        do {
            randomString = "";
            for(int i = 0; i < 10; i++) {
                int random = (int) (Math.random() * 61);
                if (random < 10) {
                    randomString += random;
                    continue;
                }
                if (random >= 10 && random < 36) {
                    randomString += (char) (random + 55);
                    continue;
                }
                randomString += (char) (random + 61);
            }
            for(String s : set) if (s.equals(randomString)) existing = true;
        } while(existing);


        return randomString;
    }

    public static final String tabelle = "SENDUNG";
    @SuppressWarnings("ConstantConditions")
    public static final Configurator.SendungDB config = Configurator.getInstance().database.Sendung;

    private Set<Sendung> getSendungen() {
        return sendungen;
    }

    private final Set<Sendung> sendungen;
    private final ISQLConnector connector;

    private SendungManager() {
        sendungen = new HashSet<>();
        connector = SQLManager.getSQLConnector();
    }

    public void init() {
        // make sure table exists
        connector.createTableIfNotExisting(tabelle,
                config.id + " varchar(10)",
                config.anlagedatum + " date",
                config.zielort + " string",
                config.transportauftrag + " string",
                config.kundennummer + " string");


        String query = "SELECT ID FROM " + tabelle + ";";
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

        // validate sendung



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
