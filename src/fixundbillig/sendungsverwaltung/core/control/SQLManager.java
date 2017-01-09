package fixundbillig.sendungsverwaltung.core.control;

import fixundbillig.sendungsverwaltung.core.db.H2Connector;
import fixundbillig.sendungsverwaltung.data.interfaces.ISQLConnector;

@SuppressWarnings("ALL")
public class SQLManager {
	private static SQLManager instance;
	public static SQLManager getInstance() {
		if(instance == null) {
			instance = new SQLManager();
		}
		return instance;
	}
	
	public static ISQLConnector getSQLConnector()  {
		return new H2Connector();
		//return null;
	}
}
