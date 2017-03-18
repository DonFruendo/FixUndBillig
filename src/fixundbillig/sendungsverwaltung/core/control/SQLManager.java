package fixundbillig.sendungsverwaltung.core.control;

import fixundbillig.sendungsverwaltung.core.db.H2Connector;
import fixundbillig.sendungsverwaltung.data.interfaces.ISQLConnector;

/**
 * Created by Don on 21.12.2016.
 *
 * @author Don
 * @since 21.12.2016
 */
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
