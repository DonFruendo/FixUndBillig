package fixundbillig.sendungsverwaltung.interfaces;

import fixundbillig.sendungsverwaltung.to.*;

public interface IDAO_Packstueck {
	void packstueckdatenAnlegen();
	void packstueckdatenAendern(PackstueckTO daten);
	void packstueckdatenLoeschen();
	void packstueckdatenSuchenPerId(int id);
	void packstueckdatenSuchenPerRefNr(int refnr);
}
