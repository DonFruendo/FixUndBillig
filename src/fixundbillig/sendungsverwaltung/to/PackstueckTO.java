package fixundbillig.sendungsverwaltung.to;

import fixundbillig.sendungsverwaltung.utils.Paketart;

public class PackstueckTO {
	protected int volumen;
	protected int id;
	protected int refnr;
	protected int gewicht;
	protected SendungTO sendung;
	protected String lagerort;
	protected Paketart paketart;
	
	
	
	public PackstueckTO(int volumen, int id, int refnr, int gewicht,
			SendungTO sendung, String lagerort, Paketart paketart) {
		super();
		this.volumen = volumen;
		this.id = id;
		this.refnr = refnr;
		this.gewicht = gewicht;
		this.sendung = sendung;
		this.lagerort = lagerort;
		this.paketart = paketart;
	}
	
	protected PackstueckTO(PackstueckTO other) {
		this.volumen = other.getVolumen();
		this.id = other.getId();
		this.refnr = other.getRefnr();
		this.gewicht = other.getGewicht();
		this.sendung = other.getSendung();
		this.lagerort = other.getLagerort();
		this.paketart = other.getPaketart();
	}
	
	public int getVolumen() {
		return volumen;
	}
	public void setVolumen(int volumen) {
		this.volumen = volumen;
	}
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public int getRefnr() {
		return refnr;
	}
	public void setRefnr(int refnr) {
		this.refnr = refnr;
	}
	public int getGewicht() {
		return gewicht;
	}
	public void setGewicht(int gewicht) {
		this.gewicht = gewicht;
	}
	public SendungTO getSendung() {
		return sendung;
	}
	public void setSendung(SendungTO sendung) {
		this.sendung = sendung;
	}
	public String getLagerort() {
		return lagerort;
	}
	public void setLagerort(String lagerort) {
		this.lagerort = lagerort;
	}
	public Paketart getPaketart() {
		return paketart;
	}
	public void setPaketart(Paketart paketart) {
		this.paketart = paketart;
	}
}