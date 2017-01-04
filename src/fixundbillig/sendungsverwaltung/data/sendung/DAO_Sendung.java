package fixundbillig.sendungsverwaltung.data.sendung;

import fixundbillig.sendungsverwaltung.core.control.SQLManager;
import fixundbillig.sendungsverwaltung.data.interfaces.IDAO_Sendung;
import fixundbillig.sendungsverwaltung.data.interfaces.ISQLConnector;

public class DAO_Sendung implements IDAO_Sendung {
    private static final String tabelle = "SENDUNG";

    private SendungTO daten;
    private ISQLConnector connector;

    public DAO_Sendung(SendungTO sendungTO) {
        connector = SQLManager.getSQLConnector();
        daten = sendungTO;
    }

    public void sendungsdatenAnlegen() {
        connector.connect();

        // make sure table exists
        connector.createTableIfNotExisting(tabelle, "sendnr", "kdnnr");
        // TODO
        String statement = "INSERT INTO " + tabelle + "('sendnr', 'kdnnr') VALUES ("
                + daten.sendungsnummer + ", "
                + daten.kundenNr
                + ");";
        connector.executeStatement(statement);
        connector.disconnect();
    }

    @Override
    public void sendungsdatenAendern(SendungTO daten) {

    }

    @Override
    public void sendungsdatenLoeschen() {

    }

    @Override
    public void sendungsdatenSuchenPerId(int id) {

    }

    @Override
    public void sendungsdatenSuchenPerRefNr(int refnr) {

    }
}
