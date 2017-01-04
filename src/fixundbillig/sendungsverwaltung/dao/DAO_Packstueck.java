package fixundbillig.sendungsverwaltung.dao;

import fixundbillig.sendungsverwaltung.control.SQLManager;
import fixundbillig.sendungsverwaltung.interfaces.IDAO_Packstueck;
import fixundbillig.sendungsverwaltung.interfaces.ISQLConnector;
import fixundbillig.sendungsverwaltung.to.PackstueckTO;

public class DAO_Packstueck implements IDAO_Packstueck {
	private static final String tabelle = "PACKSTUECK";

	private PackstueckTO packstueck;
	
	public DAO_Packstueck(PackstueckTO packstueck) {
		this.packstueck = packstueck;
	}

	public void packstueckdatenAnlegen() {
		ISQLConnector connector = SQLManager.getInstance().getSQLConnector();
		connector.connect();
		// TODO
		connector.executeStatement("INSERT INTO " + tabelle + "('ID') VALUES ("
                + packstueck.id
                + ");");
	}

	public void packstueckdatenAendern(PackstueckTO daten) {
		// TODO Auto-generated method stub
		
	}

	public void packstueckdatenLoeschen() {
		// TODO Auto-generated method stub
		
	}

	public void packstueckdatenSuchenPerId(int id) {
		// TODO Auto-generated method stub
		
	}

	public void packstueckdatenSuchenPerRefNr(int refnr) {
		// TODO Auto-generated method stub
		
	}

}
