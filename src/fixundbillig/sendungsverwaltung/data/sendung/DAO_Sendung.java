package fixundbillig.sendungsverwaltung.data.sendung;

import fixundbillig.sendungsverwaltung.core.control.SQLManager;
import fixundbillig.sendungsverwaltung.data.interfaces.IDAO_Sendung;
import fixundbillig.sendungsverwaltung.data.interfaces.ISQLConnector;

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
