package fixundbillig.sendungsverwaltung.dao.db;

import fixundbillig.sendungsverwaltung.interfaces.ISQLConnector;

import java.sql.ResultSet;

public class H2Connector implements ISQLConnector {

    @Override
    public void connect() {

    }

    @Override
    public ResultSet getQuery(String query) {
        return null;
    }

    @Override
    public int executeStatement(String statement) {
        return 0;
    }
}
