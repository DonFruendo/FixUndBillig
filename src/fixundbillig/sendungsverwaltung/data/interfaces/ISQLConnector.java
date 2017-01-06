package fixundbillig.sendungsverwaltung.data.interfaces;

import java.sql.ResultSet;

public interface ISQLConnector {
	//Connection getConnection(String url, String user, String password);
	void connect();
	ResultSet getQuery(String query);
	boolean executeStatement(String statement);
	void disconnect();

    void createTableIfNotExisting(String tabelle, String... felder);
}
