package fixundbillig.sendungsverwaltung.sendung;

import fixundbillig.sendungsverwaltung.control.PackstueckManager;
import fixundbillig.sendungsverwaltung.packstueck.Packstueck;
import fixundbillig.sendungsverwaltung.to.PackstueckTO;
import fixundbillig.sendungsverwaltung.to.SendungTO;
import fixundbillig.sendungsverwaltung.utils.Adresse;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * Created by Don on 02.01.2017.
 *
 * @author Don
 * @since 02.01.2017
 */
public class Sendung {

    private String sendungsnummer;
    private Date anlagedatum;
    private Adresse zielort;
    private String transportauftrag;
    private String kundenNr;
    private List<Packstueck> packstuecke;

    public Sendung(SendungTO sendung) {
        this(sendung.sendungsnummer, sendung.anlagedatum, sendung.zielort, sendung.transportauftrag, sendung.kundenNr, sendung.packstuecke);
    }

    public Sendung(String sendungsnummer, Date anlagedatum, Adresse zielort, String transportauftrag, String kundenNr, List<PackstueckTO> packstuecke) {
        this.sendungsnummer = sendungsnummer;
        this.anlagedatum = anlagedatum;
        this.zielort = zielort;
        this.transportauftrag = transportauftrag;
        this.kundenNr = kundenNr;
        List<Packstueck> list = new ArrayList<>();
        PackstueckManager packstueckManager = PackstueckManager.getInstance();
        for(PackstueckTO packstueckTO : packstuecke) {
            list.add(packstueckManager.getPackstueck(packstueckTO));
        }
        this.packstuecke = list;
    }

    public SendungTO toTO() {
        SendungTO to = new SendungTO();
        to.sendungsnummer = sendungsnummer;
        to.anlagedatum = anlagedatum;
        to.zielort = zielort;
        to.transportauftrag = transportauftrag;
        to.kundenNr = kundenNr;
        List<PackstueckTO> list = new ArrayList<>();
        for(Packstueck packstueck : packstuecke) {
            list.add(packstueck.toTO());
        }
        to.packstuecke = list;

        return to;
    }

    public String getSendungsnummer() {
        return sendungsnummer;
    }

    public Date getAnlagedatum() {
        return anlagedatum;
    }

    public Adresse getZielort() {
        return zielort;
    }

    public String getTransportauftrag() {
        return transportauftrag;
    }

    public String getKundenNr() {
        return kundenNr;
    }

    public List<Packstueck> getPackstuecke() {
        return packstuecke;
    }
}
