package fixundbillig.sendungsverwaltung.interfaces;

import fixundbillig.sendungsverwaltung.to.SendungTO;

/**
 * @author Tobias Freund
 * @since 03.01.2017
 */
public interface IDAO_Sendung {
    //void sendungsdatenAnlegen();
    void sendungsdatenAendern(SendungTO daten);
    void sendungsdatenLoeschen();
    void sendungsdatenSuchenPerId(int id);
    void sendungsdatenSuchenPerRefNr(int refnr);
}
