package fixundbillig.sendungsverwaltung.core.config;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import fixundbillig.sendungsverwaltung.data.utils.Logger;
import org.junit.jupiter.api.Test;

import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.io.Reader;

/**
 * Created by Don on 07.01.2017.
 *
 * @author Don
 * @since 07.01.2017
 */
public class ConfiguratorTest {
    @Test
    public void getInstance() throws Exception {
        String path = "bin/fixundbillig/config.json";
        try (Reader reader = new InputStreamReader(new FileInputStream(path), "UTF-8")) {
            Gson gson = new GsonBuilder().create();
            Configurator config = gson.fromJson(reader, Configurator.class);
            Logger.info(config);
        }

        Configurator instance = Configurator.getInstance();
        Logger.info(instance);
    }

}