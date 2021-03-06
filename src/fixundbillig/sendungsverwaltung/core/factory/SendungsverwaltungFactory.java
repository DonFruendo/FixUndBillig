package fixundbillig.sendungsverwaltung.core.factory;

import fixundbillig.sendungsverwaltung.core.control.PackstueckManager;
import fixundbillig.sendungsverwaltung.core.control.SendungManager;
import fixundbillig.sendungsverwaltung.core.usecases.PackstueckAnlegen;
import fixundbillig.sendungsverwaltung.core.usecases.SendungAnlegen;
import fixundbillig.sendungsverwaltung.core.usecases.SendungSuchen;
import fixundbillig.sendungsverwaltung.data.interfaces.IPackstueckAnlegen;
import fixundbillig.sendungsverwaltung.data.interfaces.ISendungAnlegen;
import fixundbillig.sendungsverwaltung.data.interfaces.ISendungsverwaltungFactory;

@SuppressWarnings("ALL")
public class SendungsverwaltungFactory implements ISendungsverwaltungFactory {
    private static SendungsverwaltungFactory ourInstance;
    public static SendungsverwaltungFactory getInstance() {
        if(ourInstance == null) {
            ourInstance = new SendungsverwaltungFactory();
        }
        return ourInstance;
    }

    private final PackstueckManager packstueckManager;
    private final SendungManager sendungManager;

	private SendungsverwaltungFactory() {
		// load data from database
        sendungManager = SendungManager.getInstance();
        packstueckManager = PackstueckManager.getInstance();

		// initialise
        sendungManager.init();
        packstueckManager.init();
    }

	public IPackstueckAnlegen getPackstueckAnlegen() {
		return new PackstueckAnlegen();
	}
	public ISendungAnlegen getSendungAnlegen() {
	    return new SendungAnlegen();
    }
    public SendungSuchen getSendungSuchen() {
	    return new SendungSuchen();
    }

    @Override
    public void destroy() {
        packstueckManager.destroy();
        sendungManager.destroy();
    }
}
