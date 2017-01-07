package fixundbillig.sendungsverwaltung.data.packstueck;

import fixundbillig.sendungsverwaltung.core.config.Configurator;
import fixundbillig.sendungsverwaltung.core.control.SQLManager;
import fixundbillig.sendungsverwaltung.data.interfaces.IDAO_Packstueck;
import fixundbillig.sendungsverwaltung.data.interfaces.ISQLConnector;
import fixundbillig.sendungsverwaltung.data.utils.Logger;
import fixundbillig.sendungsverwaltung.data.utils.Paketart;

import java.sql.ResultSet;
import java.sql.SQLException;

public class DAO_Packstueck implements IDAO_Packstueck {
    public static final String tabelle = "PACKSTUECK";
    @SuppressWarnings("ConstantConditions")
    private static final Configurator.PackstueckDB config = Configurator.getInstance().database.Packstueck;

    private PackstueckTO packstueck;
    private ISQLConnector connector;

    public DAO_Packstueck() {
        connector = SQLManager.getSQLConnector();
    }

    public DAO_Packstueck(PackstueckTO packstueck) {
        this();
        this.packstueck = packstueck;
    }

    public void packstueckdatenAnlegen() {
        if(packstueck == null) {
            return;
        }

        // make sure table exists
        connector.createTableIfNotExisting(tabelle,
                config.id + " int",
                config.volumen + " double",
                config.gewicht + " double",
                config.refnr + " int",
                config.sendungsnummer + " string",
                config.lagerort + " string",
                config.paketart + " string");
        // TODO
        String statement = "INSERT INTO " + tabelle + "("
                + config.id + ", "
                + config.volumen + ", "
                + config.gewicht + ", "
                + config.refnr + ", "
                + config.sendungsnummer + ", "
                + config.lagerort + ", "
                + config.paketart
                + ") VALUES ("
                + "'" + packstueck.id + "', "
                + "'" + packstueck.volumen + "', "
                + "'" + packstueck.gewicht + "', "
                + "'" + packstueck.refnr + "', "
                + "'" + packstueck.sendungsnummer + "', "
                + "'" + packstueck.lagerort + "', "
                + "'" + packstueck.paketart + "'"
                + ");";
        connector.executeStatement(statement);
        Logger.debug("SQL DEBUG: " + statement);
    }

    @Override
    public void packstueckdatenAendern(PackstueckTO daten) {

    }

    @Override
    public void packstueckdatenLoeschen(int id) {

    }

    @Override
    public void packstueckdatenSuchenPerId(int id) {
        //
        String query = "SELECT * FROM " + tabelle + " WHERE ID='" + id + "';";
        ResultSet resultSet = connector.getQuery(query);
        try {
            if (resultSet.next()) {
                PackstueckTO to = new PackstueckTO();
                to.id = resultSet.getInt(config.id);
                to.volumen = resultSet.getDouble(config.volumen);
                to.gewicht = resultSet.getDouble(config.gewicht);
                to.refnr = resultSet.getInt(config.refnr);
                to.sendungsnummer = resultSet.getString(config.sendungsnummer);
                to.lagerort = resultSet.getString(config.lagerort);
                to.paketart = Paketart.valueOf(resultSet.getString(config.paketart));
                packstueck = to;
            }
        } catch (SQLException e) {
            Logger.err(e.getMessage());
        }
    }

    @Override
    public void packstueckdatenSuchenPerRefNr(int refnr) {

    }

    public PackstueckTO toTO() {
        return packstueck;
    }
}
