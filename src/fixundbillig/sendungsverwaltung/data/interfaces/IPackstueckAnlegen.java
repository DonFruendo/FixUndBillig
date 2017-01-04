package fixundbillig.sendungsverwaltung.data.interfaces;

import fixundbillig.sendungsverwaltung.data.packstueck.PackstueckTO;

public interface IPackstueckAnlegen {
	public void packstueckdatenSpeichern(PackstueckTO packstueck);
	public void packstueckAnlegen(PackstueckTO packstueck);
}
