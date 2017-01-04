package fixundbillig.sendungsverwaltung.control;

import fixundbillig.sendungsverwaltung.dao.db.H2Connector;
import fixundbillig.sendungsverwaltung.interfaces.ISQLConnector;

public class SQLManager {
	private static SQLManager instance;
	public static SQLManager getInstance() {
		if(instance == null) {
			instance = new SQLManager();
		}
		return instance;
	}
	
	public ISQLConnector getSQLConnector()  {
		return new H2Connector();
		//return null;
	}
}
