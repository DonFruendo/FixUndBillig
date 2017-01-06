package fixundbillig.sendungsverwaltung.data.packstueck;

import fixundbillig.sendungsverwaltung.core.control.SQLManager;
import fixundbillig.sendungsverwaltung.data.interfaces.IDAO_Packstueck;
import fixundbillig.sendungsverwaltung.data.interfaces.ISQLConnector;

public class DAO_Packstueck implements IDAO_Packstueck {
	private static final String tabelle = "PACKSTUECK";

	private PackstueckTO packstueck;
	private ISQLConnector connector;
	
	public DAO_Packstueck(PackstueckTO packstueck) {
		this.packstueck = packstueck;
		connector = SQLManager.getInstance().getSQLConnector();
	}

	public void packstueckdatenAnlegen() {
		connector.connect();
		// TODO
		connector.executeStatement("INSERT INTO " + tabelle + "('ID') VALUES ("
                + packstueck.id
                + ");");
	}

    @Override
    public void packstueckdatenAendern(PackstueckTO daten) {

    }

    @Override
    public void packstueckdatenLoeschen(int id) {

    }

    @Override
    public void packstueckdatenSuchenPerId(int id) {

    }

    @Override
    public void packstueckdatenSuchenPerRefNr(int refnr) {

    }


}
