package fixundbillig.sendungsverwaltung.data.interfaces;

import fixundbillig.sendungsverwaltung.data.sendung.SendungTO;

import java.sql.SQLException;

/**
 * @author Tobias Freund
 * @since 03.01.2017
 */
public interface IDAO_Sendung {
    void sendungsdatenAnlegen() throws SQLException;
    void sendungsdatenAendern(SendungTO daten);
    void sendungsdatenLoeschen();
    void sendungsdatenSuchenPerId(String id);
    void sendungsdatenSuchenPerRefNr(String refnr);
}
