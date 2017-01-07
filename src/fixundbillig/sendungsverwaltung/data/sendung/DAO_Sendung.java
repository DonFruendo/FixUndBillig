package fixundbillig.sendungsverwaltung.data.sendung;

import fixundbillig.sendungsverwaltung.core.config.Configurator;
import fixundbillig.sendungsverwaltung.core.control.SQLManager;
import fixundbillig.sendungsverwaltung.data.interfaces.IDAO_Sendung;
import fixundbillig.sendungsverwaltung.data.interfaces.ISQLConnector;
import fixundbillig.sendungsverwaltung.data.utils.Adresse;
import fixundbillig.sendungsverwaltung.data.utils.Logger;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;

public class DAO_Sendung implements IDAO_Sendung {
    public static final String tabelle = "SENDUNG";
    private static final Configurator.SendungDB config = Configurator.getInstance().database.Sendung;

    private SendungTO daten;
    private final ISQLConnector connector;

    public DAO_Sendung() {
        connector = SQLManager.getSQLConnector();
    }

    public DAO_Sendung(SendungTO sendungTO) {
        this();
        daten = sendungTO;
    }

    public void sendungsdatenAnlegen(){
        if(daten == null) {
            return;
        }

        // make sure table exists
        connector.createTableIfNotExisting(tabelle,
                config.id + " varchar(10)",
                config.anlagedatum + " date",
                config.zielort + " string",
                config.transportauftrag + " string",
                config.kundennummer + " string");
        // insert data into table
        String statement = "INSERT INTO " + tabelle + "(" +
                config.id + ", " +
                config.anlagedatum + ", " +
                config.zielort + ", " +
                config.transportauftrag + ", " +
                config.kundennummer
                + ") VALUES ("
                + "'" + daten.sendungsnummer + "', "
                + "'" + daten.anlagedatum + "', "
                + "'" + daten.zielort.toDB() + "', "
                + "'" + daten.transportauftrag + "', "
                + "'" + daten.kundenNr + "'"
                + ");";
        connector.executeStatement(statement);
        Logger.debug(statement);
    }

    @Override
    public void sendungsdatenAendern(SendungTO daten) {

    }

    @Override
    public void sendungsdatenLoeschen() {

    }

    @Override
    public void sendungsdatenSuchenPerId(String id) {
        //
        String query = "SELECT * FROM " + tabelle + " WHERE ID='" + id + "';";
        ResultSet resultSet = connector.getQuery(query);
        try {
            if(resultSet.next()) {
                SendungTO to = new SendungTO();
                to.sendungsnummer = resultSet.getString(config.id);
                to.anlagedatum = LocalDate.parse(resultSet.getString(config.anlagedatum));
                to.zielort = Adresse.fromDb(resultSet.getString(config.zielort));
                to.transportauftrag = resultSet.getString(config.transportauftrag);
                to.kundenNr = resultSet.getString(config.kundennummer);
                daten = to;
            }
        } catch (SQLException e) {
            Logger.err(e.getMessage());
        }
    }

    @Override
    public void sendungsdatenSuchenPerRefNr(String refnr) {

    }

    public SendungTO toTO() {
        return daten;
    }
}
