package fixundbillig.sendungsverwaltung.data.interfaces;

import fixundbillig.sendungsverwaltung.data.packstueck.PackstueckTO;

public interface IPackstueckAnlegen {
	void packstueckdatenSpeichern(PackstueckTO packstueck);
	void packstueckAnlegen(PackstueckTO packstueck);
}
