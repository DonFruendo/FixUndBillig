package fixundbillig.sendungsverwaltung.interfaces;

import java.sql.ResultSet;

public interface ISQLConnector {
	//public Connection getConnection(String url, String user, String password);
	public void connect();
	public ResultSet getQuery(String query);
	public int executeStatement(String statement);
}
