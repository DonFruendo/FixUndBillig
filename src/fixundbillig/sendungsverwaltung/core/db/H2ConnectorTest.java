package fixundbillig.sendungsverwaltung.core.db;

import fixundbillig.sendungsverwaltung.core.config.Configurator;
import fixundbillig.sendungsverwaltung.data.utils.Logger;

import java.sql.Connection;
import java.sql.DriverManager;

/**
 * Created by Don on 07.01.2017.
 *
 * @author Don
 * @since 07.01.2017
 */
public class H2ConnectorTest {

    public static void main(String[] args) {
        test();
    }

    @SuppressWarnings("ConstantConditions")
    private static void test() {
        try {
            Configurator config = Configurator.getInstance();
            Configurator.DB db = config.database;
            Class.forName("org.h2.Driver");
            Logger.info("url: " + db.url);
            Logger.info("user: " + db.user);
            Logger.info("password: " + db.password);
            Connection connection = DriverManager.getConnection(db.url, db.user, db.password);
            Logger.info("Connection successful");
        } catch (Exception e) {
            Logger.err(e.getMessage());
        }
    }
}