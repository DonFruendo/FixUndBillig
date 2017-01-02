package fixundbillig.sendungsverwaltung.interfaces;

import fixundbillig.sendungsverwaltung.to.*;

public interface IDAO_Packstueck {
	public void packstueckdatenAnlegen();
	public void packstueckdatenAendern(PackstueckTO daten);
	public void packstueckdatenLoeschen();
	public void packstueckdatenSuchenPerId(int id);
	public void packstueckdatenSuchenPerRefNr(int refnr);
}
