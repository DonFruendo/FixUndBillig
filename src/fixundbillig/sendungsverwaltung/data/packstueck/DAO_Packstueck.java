package fixundbillig.sendungsverwaltung.data.packstueck;

import fixundbillig.sendungsverwaltung.core.control.SQLManager;
import fixundbillig.sendungsverwaltung.data.interfaces.IDAO_Packstueck;
import fixundbillig.sendungsverwaltung.data.interfaces.ISQLConnector;
import fixundbillig.sendungsverwaltung.data.utils.Logger;
import fixundbillig.sendungsverwaltung.data.utils.Paketart;

import java.sql.ResultSet;
import java.sql.SQLException;

public class DAO_Packstueck implements IDAO_Packstueck {
    private static final String tabelle = "PACKSTUECK";

    private PackstueckTO packstueck;
    private ISQLConnector connector;

    public DAO_Packstueck(PackstueckTO packstueck) {
        this.packstueck = packstueck;
        connector = SQLManager.getSQLConnector();
    }

    public void packstueckdatenAnlegen() {
        if(packstueck == null) {
            return;
        }

        connector.createTableIfNotExisting(tabelle,
                "");
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
        //
        String query = "SELECT * FORM " + tabelle + " WHERE ID='" + id + "';";
        ResultSet resultSet = connector.getQuery(query);
        try {
            if (resultSet.next()) {
                PackstueckTO to = new PackstueckTO();
                to.id = resultSet.getInt("ID");
                to.volumen = resultSet.getDouble("VOLUMEN");
                to.gewicht = resultSet.getDouble("GEWICHT");
                to.refnr = resultSet.getInt("REFNR");
                to.sendungsnummer = resultSet.getString("SENDUNGSNUMMER");
                to.lagerort = resultSet.getString("LAGERORT");
                to.paketart = Paketart.valueOf(resultSet.getString("PAKETART"));
                packstueck = to;
            }
        } catch (SQLException e) {
            Logger.err(e.getMessage());
        }
    }

    @Override
    public void packstueckdatenSuchenPerRefNr(int refnr) {

    }


}
