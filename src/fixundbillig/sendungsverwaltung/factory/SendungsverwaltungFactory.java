package fixundbillig.sendungsverwaltung.factory;
import fixundbillig.sendungsverwaltung.packstueck.PackstueckAnlegen;
import fixundbillig.sendungsverwaltung.interfaces.*;

public class SendungsverwaltungFactory implements ISendungsverwaltungFactory {

	public IPackstueckAnlegen getPackstueckAnlegen() {
		return new PackstueckAnlegen();
	}

}
