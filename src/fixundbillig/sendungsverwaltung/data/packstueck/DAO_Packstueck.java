package fixundbillig.sendungsverwaltung.data.packstueck;

import fixundbillig.sendungsverwaltung.core.control.SQLManager;
import fixundbillig.sendungsverwaltung.data.interfaces.IDAO_Packstueck;
import fixundbillig.sendungsverwaltung.data.interfaces.ISQLConnector;
import fixundbillig.sendungsverwaltung.data.utils.Logger;
import fixundbillig.sendungsverwaltung.data.utils.Paketart;

import java.sql.ResultSet;
import java.sql.SQLException;

import static fixundbillig.sendungsverwaltung.core.control.PackstueckManager.config;
import static fixundbillig.sendungsverwaltung.core.control.PackstueckManager.tabelle;

/**
 * Created by Don on 21.12.2016.
 *
 * @author Don
 * @since 21.21.2016
 */
public class DAO_Packstueck implements IDAO_Packstueck {

    private PackstueckTO packstueck;
    private final ISQLConnector connector;

    @SuppressWarnings("WeakerAccess")
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

        // insert data into database
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
    public void packstueckdatenLoeschen(String id) {

    }

    @Override
    public void packstueckdatenSuchenPerId(String id) {
        //
        String query = "SELECT * FROM " + tabelle + " WHERE ID='" + id + "';";
        ResultSet resultSet = connector.getQuery(query);
        try {
            if (resultSet.next()) {
                PackstueckTO to = new PackstueckTO();
                to.id = resultSet.getString(config.id);
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
