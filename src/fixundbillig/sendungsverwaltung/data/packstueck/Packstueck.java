package fixundbillig.sendungsverwaltung.data.packstueck;

import fixundbillig.sendungsverwaltung.core.control.SendungManager;
import fixundbillig.sendungsverwaltung.data.sendung.Sendung;
import fixundbillig.sendungsverwaltung.data.utils.Paketart;

import java.util.Objects;

@SuppressWarnings("ALL")
public class Packstueck {

    private double volumen;
    private String id;
    private int refnr;
    private double gewicht;
    private Sendung sendung;
    private String lagerort;
    private Paketart paketart;


    public Packstueck(PackstueckTO packstueck) {
        this(packstueck.volumen, packstueck.id, packstueck.refnr, packstueck.gewicht, SendungManager.getInstance().sendungSuchenPerSendungsNr(packstueck.sendungsnummer), packstueck.lagerort, packstueck.paketart);
    }

	public Packstueck(double volumen, String id, int refnr, double gewicht,
                      Sendung sendung, String lagerort, Paketart paketart) {
        this.volumen = volumen;
        this.id = id;
        this.refnr = refnr;
        this.gewicht = gewicht;
        this.sendung = sendung;
        this.lagerort = lagerort;
        this.paketart = paketart;
	}

	public boolean update(double volumen, String id, int refnr, double gewicht,
                          Sendung sendung, String lagerort, Paketart paketart) {
        boolean result = false;

        if(!(this.volumen == volumen)) result = true;
        if(!(this.id.equals(id))) result = true;
        if(!(this.refnr == refnr)) result = true;
        if(!(this.gewicht == gewicht)) result = true;
        if(!this.sendung.equals(sendung)) result = true;
        if(!this.lagerort.equals(lagerort)) result = true;
        if(!this.paketart.equals(paketart)) result = true;

        this.volumen = volumen;
        this.id = id;
        this.refnr = refnr;
        this.gewicht = gewicht;
        this.sendung = sendung;
        this.lagerort = lagerort;
        this.paketart = paketart;

        return result;
    }

    public PackstueckTO toTO() {
	    PackstueckTO to =  new PackstueckTO();
        to.volumen = volumen;
        to.id = id;
        to.refnr = refnr;
        to.gewicht = gewicht;
        to.sendungsnummer = sendung.getSendungsnummer();
        to.lagerort = lagerort;
        to.paketart = paketart;
        return to;
    }

    public double getVolumen() {
        return volumen;
    }

    public String getId() {
        return id;
    }

    public int getRefnr() {
        return refnr;
    }

    public double getGewicht() {
        return gewicht;
    }

    public Sendung getSendung() {
        return sendung;
    }

    public String getLagerort() {
        return lagerort;
    }

    public Paketart getPaketart() {
        return paketart;
    }

    public void setSendung(Sendung sendung) {
        this.sendung = sendung;
    }

    public void setVolumen(double volumen) {
        this.volumen = volumen;
    }

    public void setId(String id) {
        this.id = id;
    }

    public void setRefnr(int refnr) {
        this.refnr = refnr;
    }

    public void setGewicht(double gewicht) {
        this.gewicht = gewicht;
    }

    public void setLagerort(String lagerort) {
        this.lagerort = lagerort;
    }

    public void setPaketart(Paketart paketart) {
        this.paketart = paketart;
    }

    @Override
    public String toString() {
        return "Packstueck{" +
                "volumen=" + volumen +
                ", id=" + id +
                ", refnr=" + refnr +
                ", gewicht=" + gewicht +
                ", sendung=" + (sendung==null?"null":sendung.getSendungsnummer()) +
                ", lagerort='" + lagerort + '\'' +
                ", paketart=" + paketart +
                '}';
    }

    @Override
    public boolean equals(Object other) {
        return other.getClass() == this.getClass() && Objects.equals(((Packstueck) other).getId(), this.id);
    }
}
