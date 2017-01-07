package fixundbillig.sendungsverwaltung.core.factory;

import fixundbillig.sendungsverwaltung.core.control.PackstueckManager;
import fixundbillig.sendungsverwaltung.core.control.SendungManager;
import fixundbillig.sendungsverwaltung.core.usecases.PackstueckAnlegen;
import fixundbillig.sendungsverwaltung.core.usecases.SendungAnlegen;
import fixundbillig.sendungsverwaltung.data.interfaces.IPackstueckAnlegen;
import fixundbillig.sendungsverwaltung.data.interfaces.ISendungAnlegen;
import fixundbillig.sendungsverwaltung.data.interfaces.ISendungsverwaltungFactory;

public class SendungsverwaltungFactory implements ISendungsverwaltungFactory {

	public SendungsverwaltungFactory() {
		// load data from database
		SendungManager instance = SendungManager.getInstance();
		PackstueckManager packstueckManager = PackstueckManager.getInstance();
	}

	public IPackstueckAnlegen getPackstueckAnlegen() {
		return new PackstueckAnlegen();
	}
	public ISendungAnlegen getSendungAnlegen() {
	    return new SendungAnlegen();
    }
}
