package fixundbillig.sendungsverwaltung.data.interfaces;

import fixundbillig.sendungsverwaltung.data.packstueck.PackstueckTO;

@SuppressWarnings("ALL")
public interface IPackstueckAnlegen {
	void packstueckdatenSpeichern(PackstueckTO packstueck);
	void packstueckAnlegen(PackstueckTO packstueck);
}
