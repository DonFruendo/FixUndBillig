package fixundbillig.sendungsverwaltung.data.interfaces;

import java.sql.ResultSet;

/**
 * Created by Don on 21.12.2016.
 *
 * @author Don
 * @since 21.21.2016
 */
@SuppressWarnings("ALL")
public interface ISQLConnector {
	//Connection getConnection(String url, String user, String password);
	void connect();
	ResultSet getQuery(String query);
	boolean executeStatement(String statement);
	void disconnect();

    void createTableIfNotExisting(String tabelle, String... felder);
}
