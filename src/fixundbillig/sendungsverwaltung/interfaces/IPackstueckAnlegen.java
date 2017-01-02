package fixundbillig.sendungsverwaltung.interfaces;

import fixundbillig.sendungsverwaltung.to.*;

public interface IPackstueckAnlegen {
	public void packstueckdatenSpeichern(PackstueckTO packstueck);
	public void packstueckAnlegen(PackstueckTO packstueck);
}
