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
@SuppressWarnings("ALL")
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
        if(packstuecke != null) {
            for (PackstueckTO packstueckTO : packstuecke) {
                // add order to package
                Packstueck packstueck = packstueckManager.getPackstueck(packstueckTO);
                if (packstueck.getSendung() == null) {
                    packstueck.setSendung(this);
                } // TODO list not contains
                if(!list.contains(packstueck))
                    list.add(packstueck);
            }
        }
        this.packstuecke = list;
    }

    public boolean update(String sendungsnummer, LocalDate anlagedatum, Adresse zielort, String transportauftrag, String kundenNr, List<Packstueck> packstuecke) {
        boolean result = false;

        if(!this.sendungsnummer.equals(sendungsnummer)) result = true;
        if(!this.anlagedatum.equals(anlagedatum)) result = true;
        if(!this.zielort.equals(zielort)) result = true;
        if(!this.transportauftrag.equals(transportauftrag)) result = true;
        if(!this.kundenNr.equals(kundenNr)) result = true;
        if(!this.packstuecke.containsAll(packstuecke) || !packstuecke.containsAll(this.packstuecke)) result = true;

        this.sendungsnummer = sendungsnummer;
        this.anlagedatum = anlagedatum;
        this.zielort = zielort;
        this.transportauftrag = transportauftrag;
        this.kundenNr = kundenNr;
        this.packstuecke = packstuecke;

        return result;
    }

    public boolean update(Sendung sendung) {
        return update(sendung.getSendungsnummer(), sendung.getAnlagedatum(), sendung.getZielort(), sendung.getTransportauftrag(), sendung.getKundenNr(), sendung.getPackstuecke());
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

    public void setSendungsnummer(String sendungsnummer) {
        this.sendungsnummer = sendungsnummer;
    }

    public void setAnlagedatum(LocalDate anlagedatum) {
        this.anlagedatum = anlagedatum;
    }

    public void setZielort(Adresse zielort) {
        this.zielort = zielort;
    }

    public void setTransportauftrag(String transportauftrag) {
        this.transportauftrag = transportauftrag;
    }

    public void setKundenNr(String kundenNr) {
        this.kundenNr = kundenNr;
    }

    public void setPackstuecke(List<Packstueck> packstuecke) {
        this.packstuecke = packstuecke;
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
