package fixundbillig.sendungsverwaltung.core.db;


import com.google.gson.Gson;
import fixundbillig.sendungsverwaltung.data.utils.Logger;

import java.io.IOException;
import java.nio.charset.Charset;
import java.nio.file.Files;
import java.nio.file.Paths;

/**
 * Created by Don on 06.01.2017.
 *
 * @author Don
 * @since 06.01.2017
 */
public class Configurator {
    private static Configurator ourInstance = new Configurator();

    public static Configurator getInstance() {
        return ourInstance;
    }

    private Gson gson;
    public final Configration config;

    private Configurator() {
        gson = new Gson();
        String jsonString = "{}";
        try {
            jsonString = new String(Files.readAllBytes(Paths.get("config.json")), Charset.defaultCharset());
        } catch (IOException e) {
            Logger.err(e.getMessage());
        }
        config = gson.fromJson(jsonString, Configration.class);
    }

    private static class Configration {
        // TODO POJO
    }
}
