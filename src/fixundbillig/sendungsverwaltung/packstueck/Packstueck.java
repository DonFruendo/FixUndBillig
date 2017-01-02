package fixundbillig.sendungsverwaltung.packstueck;

import fixundbillig.sendungsverwaltung.sendung.Sendung;
import fixundbillig.sendungsverwaltung.to.PackstueckTO;
import fixundbillig.sendungsverwaltung.utils.Paketart;

public class Packstueck {

    private int volumen;
    private int id;
    private int refnr;
    private int gewicht;
    private Sendung sendung;
    private String lagerort;
    private Paketart paketart;


	public Packstueck(int volumen, int id, int refnr, int gewicht,
                      Sendung sendung, String lagerort, Paketart paketart) {
        this.volumen = volumen;
        this.id = id;
        this.refnr = refnr;
        this.gewicht = gewicht;
        this.sendung = sendung;
        this.lagerort = lagerort;
        this.paketart = paketart;
	}
	
	public Packstueck(PackstueckTO packstueck) {

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

    public int getVolumen() {
        return volumen;
    }

    public int getId() {
        return id;
    }

    public int getRefnr() {
        return refnr;
    }

    public int getGewicht() {
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
}
