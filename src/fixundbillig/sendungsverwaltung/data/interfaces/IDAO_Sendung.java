package fixundbillig.sendungsverwaltung.data.interfaces;

import fixundbillig.sendungsverwaltung.data.sendung.SendungTO;

/**
 * @author Tobias Freund
 * @since 03.01.2017
 */
public interface IDAO_Sendung {
    void sendungsdatenAnlegen();
    void sendungsdatenAendern(SendungTO daten);
    void sendungsdatenLoeschen();
    void sendungsdatenSuchenPerId(String id);
    void sendungsdatenSuchenPerRefNr(String refnr);
}
