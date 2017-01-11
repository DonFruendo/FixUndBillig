package fixundbillig.sendungsverwaltung.data.sendung;

import fixundbillig.sendungsverwaltung.core.control.SQLManager;
import fixundbillig.sendungsverwaltung.data.interfaces.IDAO_Sendung;
import fixundbillig.sendungsverwaltung.data.interfaces.ISQLConnector;
import fixundbillig.sendungsverwaltung.data.utils.Adresse;
import fixundbillig.sendungsverwaltung.data.utils.Logger;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;

import static fixundbillig.sendungsverwaltung.core.control.SendungManager.config;
import static fixundbillig.sendungsverwaltung.core.control.SendungManager.tabelle;

public class DAO_Sendung implements IDAO_Sendung {

    private SendungTO daten;
    private final ISQLConnector connector;

    public DAO_Sendung() {
        connector = SQLManager.getSQLConnector();
    }

    public DAO_Sendung(SendungTO sendungTO) {
        this();
        daten = sendungTO;
    }

    public void sendungsdatenAnlegen() {
        if (daten == null) {
            return;
        }

        // check if object is already existing
        boolean newObject = true;
        String statement = "SELECT " + config.id + " FROM " + tabelle;
        Logger.debug(statement);
        ResultSet resultSet = connector.getQuery(statement);
        try {
            while (resultSet.next()) {
                DAO_Sendung tempDAO = new DAO_Sendung();
                tempDAO.sendungsdatenSuchenPerId(daten.sendungsnummer);
                Logger.debug(tempDAO);
                if (tempDAO.toTO() != null && tempDAO.toTO().anlagedatum != null) {
                    // switching to update-case
                    newObject = false;
                    sendungsdatenAendern(daten);
                }
            }
        } catch (SQLException e) {
            Logger.err(e.getMessage());
        }
        if (newObject) {
            // insert data into table
            statement = "INSERT INTO " + tabelle + "(" +
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
    }

    @Override
    public void sendungsdatenAendern(SendungTO daten) {
        // update data in table
        String statement = "UPDATE " + tabelle + " SET "
                + config.anlagedatum + "='" + daten.anlagedatum + "', "
                + config.zielort + "='" + daten.zielort.toDB() + "', "
                + config.transportauftrag + "='" + daten.transportauftrag + "', "
                + config.kundennummer + "='" + daten.kundenNr + "'"
                + " WHERE "
                + config.id + "='" + daten.sendungsnummer
                + "';";
        connector.executeStatement(statement);
        Logger.debug(statement);
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
