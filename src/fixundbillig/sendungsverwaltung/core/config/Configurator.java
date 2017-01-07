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
public class Configurator {
    private static Configurator ourInstance = ConfiguratorBuilder();

    public static Configurator getInstance() {
        return ourInstance;
    }

    public final DB database = null;

    private static Configurator ConfiguratorBuilder() {
        String path = "bin/fixundbillig/config.json";
        Configurator config = null;
        try (Reader reader = new InputStreamReader(new FileInputStream(path), "UTF-8")) {
            Logger.info(reader);
            Gson gson = new GsonBuilder().create();
            config = gson.fromJson(reader, Configurator.class);
            //Logger.info(config);
        } catch (Exception e) {
            Logger.err(e.getMessage());
        }
        return config;
    }


    // ----------- Configuration classes ------------------
    public static class DB {
        public final String url = "";
        public final String user = "";
        public final String password = "";
        public final SendungDB Sendung;
        public final PackstueckDB Packstueck;

        public DB(SendungDB sendung, PackstueckDB packstueck) {
            Sendung = sendung;
            Packstueck = packstueck;
        }
    }

    public static class SendungDB {
        public final String id;
        public final String anlagedatum;
        public final String zielort;
        public final String transportauftrag;
        public final String kundennummer;

        public SendungDB(String id, String anlagedatum, String zielort, String transportauftrag, String kundennummer) {
            this.id = id;
            this.anlagedatum = anlagedatum;
            this.zielort = zielort;
            this.transportauftrag = transportauftrag;
            this.kundennummer = kundennummer;
        }
    }

    public static class PackstueckDB {
        public final String id;
        public final String volumen;
        public final String gewicht;
        public final String refnr;
        public final String sendungsnummer;
        public final String lagerort;
        public final String paketart;

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
