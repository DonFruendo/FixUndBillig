package fixundbillig.sendungsverwaltung;

import fixundbillig.sendungsverwaltung.core.control.SQLManager;
import fixundbillig.sendungsverwaltung.data.interfaces.ISQLConnector;

/**
 * Created by Don on 10.01.2017.
 *
 * @author Don
 * @since 10.01.2017
 */
public class DBDeleter {
    public static void main(String[] args) {
        ISQLConnector connector = SQLManager.getSQLConnector();
        connector.executeStatement("DROP TABLE IF EXISTS PACKSTUECK;");
        connector.executeStatement("DROP TABLE IF EXISTS SENDUNG;");
        connector.disconnect();
    }
}
