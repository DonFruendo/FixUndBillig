package fixundbillig.sendungsverwaltung.core.config;


import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import fixundbillig.sendungsverwaltung.data.utils.Logger;

import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.io.Reader;

/**
 * Created by Don on 06.01.2017.
 *
 * @author Don
 * @since 06.01.2017
 */
@SuppressWarnings("ALL")
public class Configurator {
    private static Configurator ourInstance;

    public static Configurator getInstance() {
        if(ourInstance == null) {
            ourInstance = configuratorBuilder();
        }
        return ourInstance;
    }

    public final DB database = null;

    private static Configurator configuratorBuilder() {
        String path = "bin/fixundbillig/config.json";
        Configurator config = null;
        try (Reader reader = new InputStreamReader(new FileInputStream(path), "UTF-8")) {
            Logger.info(reader);
            Gson gson = new GsonBuilder().create();
            config = gson.fromJson(reader, Configurator.class);
            Logger.info(config);
        } catch (Exception e) {
            Logger.err(e.getMessage());
        }
        return config;
    }


    // ----------- Configuration classes ------------------
    @SuppressWarnings("CanBeFinal")
    public static class DB {
        public String url = "";
        public String user = "";
        public String password = "";
        public SendungDB Sendung;
        public PackstueckDB Packstueck;

        public DB(SendungDB sendung, PackstueckDB packstueck) {
            Sendung = sendung;
            Packstueck = packstueck;
        }
    }

    @SuppressWarnings("CanBeFinal")
    public static class SendungDB {
        public String id;
        public String anlagedatum;
        public String zielort;
        public String transportauftrag;
        public String kundennummer;

        public SendungDB(String id, String anlagedatum, String zielort, String transportauftrag, String kundennummer) {
            this.id = id;
            this.anlagedatum = anlagedatum;
            this.zielort = zielort;
            this.transportauftrag = transportauftrag;
            this.kundennummer = kundennummer;
        }
    }

    @SuppressWarnings("CanBeFinal")
    public static class PackstueckDB {
        public String id;
        public String volumen;
        public String gewicht;
        public String refnr;
        public String sendungsnummer;
        public String lagerort;
        public String paketart;

        public PackstueckDB(String id, String volumen, String gewicht, String refnr, String sendungsnummer, String lagerort, String paketart) {
            this.id = id;
            this.volumen = volumen;
            this.gewicht = gewicht;
            this.refnr = refnr;
            this.sendungsnummer = sendungsnummer;
            this.lagerort = lagerort;
            this.paketart = paketart;
        }
    }
}
