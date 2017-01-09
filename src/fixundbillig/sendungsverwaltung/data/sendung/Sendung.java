package fixundbillig.sendungsverwaltung.data.sendung;

import fixundbillig.sendungsverwaltung.core.control.PackstueckManager;
import fixundbillig.sendungsverwaltung.data.packstueck.Packstueck;
import fixundbillig.sendungsverwaltung.data.utils.Adresse;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

/**
 * Created by Don on 02.01.2017.
 *
 * @author Don
 * @since 02.01.2017
 */
@SuppressWarnings("WeakerAccess")
public class Sendung {

    private String sendungsnummer; // TODO Generate
    private LocalDate anlagedatum;
    private Adresse zielort;
    private String transportauftrag;
    private String kundenNr;


    public Sendung(SendungTO sendung) {
        this(sendung.sendungsnummer, sendung.anlagedatum, sendung.zielort, sendung.transportauftrag, sendung.kundenNr);
    }

    public Sendung(String sendungsnummer, LocalDate anlagedatum, Adresse zielort, String transportauftrag, String kundenNr) {
        this.sendungsnummer = sendungsnummer;
        this.anlagedatum = anlagedatum;
        this.zielort = zielort;
        this.transportauftrag = transportauftrag;
        this.kundenNr = kundenNr;
    }

    public boolean update(String sendungsnummer, LocalDate anlagedatum, Adresse zielort, String transportauftrag, String kundenNr) {
        boolean result = false;

        if(!this.sendungsnummer.equals(sendungsnummer)) result = true;
        if(!this.anlagedatum.equals(anlagedatum)) result = true;
        if(!this.zielort.equals(zielort)) result = true;
        if(!this.transportauftrag.equals(transportauftrag)) result = true;
        if(!this.kundenNr.equals(kundenNr)) result = true;

        this.sendungsnummer = sendungsnummer;
        this.anlagedatum = anlagedatum;
        this.zielort = zielort;
        this.transportauftrag = transportauftrag;
        this.kundenNr = kundenNr;

        return result;
    }

    public boolean update(Sendung sendung) {
        return update(sendung.getSendungsnummer(), sendung.getAnlagedatum(), sendung.getZielort(), sendung.getTransportauftrag(), sendung.getKundenNr());
    }

    public SendungTO toTO() {
        SendungTO to = new SendungTO();
        to.sendungsnummer = sendungsnummer;
        to.anlagedatum = anlagedatum;
        to.zielort = zielort;
        to.transportauftrag = transportauftrag;
        to.kundenNr = kundenNr;
        to.packstuecke = getPackstuecke().stream().map(Packstueck::toTO).collect(Collectors.toList());
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
        List<Packstueck> result = new ArrayList<>();

        PackstueckManager packstueckManager = PackstueckManager.getInstance();
        List<Packstueck> packstuecke = packstueckManager.getPackstueckePerSendungsnummer(this.sendungsnummer);
        if(packstuecke != null) {
            result.addAll(packstuecke);
        }
        return result;
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

    @Override
    public String toString() {
        return "Sendung{" +
                "sendungsnummer='" + sendungsnummer + '\'' +
                ", anlagedatum=" + anlagedatum +
                ", zielort=" + zielort +
                ", transportauftrag='" + transportauftrag + '\'' +
                ", kundenNr='" + kundenNr + '\'' +
                ", packstuecke=" + getPackstuecke() +
                '}';
    }

    public boolean equals(Object other) {
        return other.getClass() == this.getClass() && Objects.equals(((Sendung) other).getSendungsnummer(), this.sendungsnummer);
    }
}
