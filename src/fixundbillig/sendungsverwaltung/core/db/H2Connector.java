package fixundbillig.sendungsverwaltung.core.db;

import fixundbillig.sendungsverwaltung.data.interfaces.ISQLConnector;
import fixundbillig.sendungsverwaltung.data.utils.Logger;

import java.sql.ResultSet;

public class H2Connector implements ISQLConnector {

    @Override
    public void connect() {
        // TODO Establish database connection
        Logger.info("SQL connect");
    }

    @Override
    public ResultSet getQuery(String query) {
        Logger.info("SQL: " + query);
        return null;
    }

    @Override
    public int executeStatement(String statement) {
        // TODO Send statement to database
        Logger.info("SQL: " + statement);
        return 0;
    }

    @Override
    public void disconnect() {
        Logger.info("SQL disconnect");

    }

    @Override
    public void createTableIfNotExisting(String tabelle, String... felder) {
        String statement = "CREATE TABLE IF NOT EXISTS '" + tabelle
                + ";";
        Logger.info("SQL: " + statement);
        executeStatement(statement);
    }
}
