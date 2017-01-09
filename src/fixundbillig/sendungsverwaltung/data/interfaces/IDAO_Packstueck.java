package fixundbillig.sendungsverwaltung.data.interfaces;

import fixundbillig.sendungsverwaltung.data.packstueck.PackstueckTO;

@SuppressWarnings("ALL")
public interface IDAO_Packstueck {
	void packstueckdatenAnlegen();
	void packstueckdatenAendern(PackstueckTO daten);
	void packstueckdatenLoeschen(int id);
	void packstueckdatenSuchenPerId(int id);
	void packstueckdatenSuchenPerRefNr(int refnr);
}
