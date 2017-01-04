package fixundbillig.sendungsverwaltung.dao;

import fixundbillig.sendungsverwaltung.control.SQLManager;
import fixundbillig.sendungsverwaltung.interfaces.IDAO_Sendung;
import fixundbillig.sendungsverwaltung.interfaces.ISQLConnector;
import fixundbillig.sendungsverwaltung.to.SendungTO;

public class DAO_Sendung implements IDAO_Sendung {
    private static final String tabelle = "SENDUNG";

    private SendungTO daten;

    public DAO_Sendung(SendungTO sendungTO) {
        daten = sendungTO;
    }

    public String sendungsdatenAnlegen() {
        // TODO stuff
        ISQLConnector connector = SQLManager.getInstance().getSQLConnector();
        connector.connect();
        // TODO
        String statement = "INSERT INTO BLA('STUFF', 'HOLOLENS') VALUES ("
                + daten.sendungsnummer + ", "
                + daten.kundenNr
                + ");";
        connector.executeStatement(statement);

        return statement;
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
