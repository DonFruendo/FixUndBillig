package fixundbillig.sendungsverwaltung.data.interfaces;

import fixundbillig.sendungsverwaltung.data.packstueck.PackstueckTO;

/**
 * Created by Don on 21.12.2016.
 *
 * @author Don
 * @since 21.21.2016
 */
@SuppressWarnings("ALL")
public interface IPackstueckAnlegen {
	void packstueckdatenSpeichern(PackstueckTO packstueck);
	void packstueckAnlegen(PackstueckTO packstueck);
}
