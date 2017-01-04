package fixundbillig.sendungsverwaltung.core.db;

import fixundbillig.sendungsverwaltung.data.interfaces.ISQLConnector;

import java.sql.ResultSet;

public class H2Connector implements ISQLConnector {

    @Override
    public void connect() {
        // TODO Establish database connection
    }

    @Override
    public ResultSet getQuery(String query) {
        return null;
    }

    @Override
    public int executeStatement(String statement) {
        // TODO Send statement to database
        return 0;
    }
}
