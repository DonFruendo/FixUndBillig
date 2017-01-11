package fixundbillig.sendungsverwaltung.data.interfaces;

import fixundbillig.sendungsverwaltung.data.packstueck.PackstueckTO;

@SuppressWarnings("ALL")
public interface IDAO_Packstueck {
	void packstueckdatenAnlegen();
	void packstueckdatenAendern(PackstueckTO daten);
	void packstueckdatenLoeschen(String id);
	void packstueckdatenSuchenPerId(String id);
	void packstueckdatenSuchenPerRefNr(int refnr);
}
