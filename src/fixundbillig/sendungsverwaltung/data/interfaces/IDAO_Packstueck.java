package fixundbillig.sendungsverwaltung.data.interfaces;

import fixundbillig.sendungsverwaltung.data.packstueck.PackstueckTO;

public interface IDAO_Packstueck {
	void packstueckdatenAnlegen();
	void packstueckdatenAendern(PackstueckTO daten);
	void packstueckdatenLoeschen();
	void packstueckdatenSuchenPerId(int id);
	void packstueckdatenSuchenPerRefNr(int refnr);
}
