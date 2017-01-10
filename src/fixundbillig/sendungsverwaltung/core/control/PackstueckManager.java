package fixundbillig.sendungsverwaltung.core.control;

import fixundbillig.sendungsverwaltung.core.config.Configurator;
import fixundbillig.sendungsverwaltung.data.interfaces.ISQLConnector;
import fixundbillig.sendungsverwaltung.data.packstueck.DAO_Packstueck;
import fixundbillig.sendungsverwaltung.data.packstueck.Packstueck;
import fixundbillig.sendungsverwaltung.data.packstueck.PackstueckTO;
import fixundbillig.sendungsverwaltung.data.utils.Logger;

import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

/**
 * Created by Don on 02.01.2017.
 *
 * @author Don
 * @since 02.01.2017
 */
public class PackstueckManager {

    private final Set<Packstueck> packstuecke;
    public static final String tabelle = "PACKSTUECK";

    private static PackstueckManager ourInstance;
    @SuppressWarnings("ConstantConditions")
    public static Configurator.PackstueckDB config = Configurator.getInstance().database.Packstueck;

    public static PackstueckManager getInstance() {
        if (ourInstance == null) {
            ourInstance = new PackstueckManager();
        }
        return ourInstance;
    }

    public static String generatePackstueckID() {
        Set<String> set = new HashSet<>();
        PackstueckManager m = PackstueckManager.getInstance();
        for(Packstueck p : m.getPackstuecke()) {
            set.add(p.getId());
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

    private final ISQLConnector connector;

    private PackstueckManager() {
        packstuecke = new HashSet<>();
        connector = SQLManager.getSQLConnector();
    }

    public void init() {
        // make sure table exists
        connector.createTableIfNotExisting(tabelle,
                config.id + " string",
                config.volumen + " double",
                config.gewicht + " double",
                config.refnr + " int",
                config.sendungsnummer + " string",
                config.lagerort + " string",
                config.paketart + " string");

        String query = "SELECT ID FROM " + tabelle + ";";
        ResultSet resultSet = connector.getQuery(query);
        try {
            ArrayList<DAO_Packstueck> daos = new ArrayList<>();
            while (resultSet.next()) {
                String id = resultSet.getString("ID");
                PackstueckTO to = new PackstueckTO();
                to.id = id;
                daos.add(new DAO_Packstueck(to));
            }

            for (DAO_Packstueck dao : daos) {
                dao.packstueckdatenSuchenPerId(dao.toTO().id);
                Packstueck packstueck = new Packstueck(dao.toTO());
                packstuecke.add(packstueck);
            }
            Logger.info("Packstück Initialisierung abgeschlossen");
        } catch (Exception e) {
            Logger.err(e.getMessage());
        }
    }

    public void destroy() {
        connector.disconnect();
    }


    public boolean addPackstueck(PackstueckTO packstueckTO) {
        Packstueck packstueck = new Packstueck(packstueckTO);
        boolean setNotContaining = true;
        for (Packstueck p : packstuecke) {
            if (p.equals(packstueck)) {
                setNotContaining = false;
                break;
            }
        }
        if (setNotContaining) {
            packstuecke.add(packstueck);
            Logger.info("Added: " + packstueck);
            return true;
        } else {
            Logger.info("Exists already: " + packstueck);
            return false;
        }
    }

    public Packstueck getPackstueck(PackstueckTO packstueckTO) {
        Packstueck find = null;
        for (Packstueck packstueck : packstuecke) {
            if (packstueckTO.id == packstueck.getId()) {
                find = packstueck;
                break;
            }
        }
        return find;
    }

    public List<Packstueck> getPackstueckePerSendungsnummer(String sendungsnummer) {
        List<Packstueck> find = new ArrayList<>();
        for (Packstueck packstueck : packstuecke) {
            if(packstueck.getSendung() == null) {
                continue;
            }
            String s = packstueck.getSendung().getSendungsnummer();
            if (sendungsnummer.equals(s)) {
                find.add(packstueck);
                break;
            }
        }
        return find;
    }

    public Set<Packstueck> getPackstuecke() {
        return packstuecke;
    }
}