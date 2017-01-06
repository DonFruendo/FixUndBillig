package fixundbillig.sendungsverwaltung.data.sendung;

import fixundbillig.sendungsverwaltung.core.control.PackstueckManager;
import fixundbillig.sendungsverwaltung.data.packstueck.Packstueck;
import fixundbillig.sendungsverwaltung.data.packstueck.PackstueckTO;
import fixundbillig.sendungsverwaltung.data.utils.Adresse;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

/**
 * Created by Don on 02.01.2017.
 *
 * @author Don
 * @since 02.01.2017
 */
public class Sendung {

    private String sendungsnummer; // TODO Generate
    private LocalDate anlagedatum;
    private Adresse zielort;
    private String transportauftrag;
    private String kundenNr;
    private List<Packstueck> packstuecke;

    public Sendung(SendungTO sendung) {
        this(sendung.sendungsnummer, sendung.anlagedatum, sendung.zielort, sendung.transportauftrag, sendung.kundenNr, sendung.packstuecke);
    }

    public Sendung(String sendungsnummer, LocalDate anlagedatum, Adresse zielort, String transportauftrag, String kundenNr, List<PackstueckTO> packstuecke) {
        this.sendungsnummer = sendungsnummer;
        this.anlagedatum = anlagedatum;
        this.zielort = zielort;
        this.transportauftrag = transportauftrag;
        this.kundenNr = kundenNr;
        List<Packstueck> list = new ArrayList<>();
        PackstueckManager packstueckManager = PackstueckManager.getInstance();
        if(packstuecke != null)
        for(PackstueckTO packstueckTO : packstuecke) {
            // add order to package
            Packstueck packstueck = packstueckManager.getPackstueck(packstueckTO);
            if(packstueck.getSendung() == null) {
                packstueck.setSendung(this);
            }
            list.add(packstueck);
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

    public LocalDate getAnlagedatum() {
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

    @Override
    public String toString() {
        return "Sendung{" +
                "sendungsnummer='" + sendungsnummer + '\'' +
                ", anlagedatum=" + anlagedatum +
                ", zielort=" + zielort +
                ", transportauftrag='" + transportauftrag + '\'' +
                ", kundenNr='" + kundenNr + '\'' +
                ", packstuecke=" + packstuecke +
                '}';
    }

    public boolean equals(Object other) {
        return other.getClass() == this.getClass() && Objects.equals(((Sendung) other).getSendungsnummer(), this.sendungsnummer);
    }
}
