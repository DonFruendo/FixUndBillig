package fixundbillig.sendungsverwaltung.core.usecases;

import fixundbillig.sendungsverwaltung.core.control.SendungManager;
import fixundbillig.sendungsverwaltung.data.sendung.SendungTO;

/**
 * Created by Don on 09.01.2017.
 *
 * @author Don
 * @since 09.01.2017
 */
public class SendungSuchen {
    public SendungTO sendungSuchen(String sendungsnummer) {
        SendungManager manager = SendungManager.getInstance();
        return manager.sendungSuchenPerSendungsNr(sendungsnummer).toTO();
    }
}
