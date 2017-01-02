package fixundbillig.sendungsverwaltung.to;

import fixundbillig.sendungsverwaltung.utils.Paketart;

public class PackstueckTO {
	public int volumen;
	public int id;
	public int refnr;
	public int gewicht;
	public String sendungsnummer;
	public String lagerort;
	public Paketart paketart;
	
	public PackstueckTO() {
    }
	
	public PackstueckTO(int volumen, int id, int refnr, int gewicht,
			String sendungsnummer, String lagerort, Paketart paketart) {
		super();
		this.volumen = volumen;
		this.id = id;
		this.refnr = refnr;
		this.gewicht = gewicht;
		this.sendungsnummer = sendungsnummer;
		this.lagerort = lagerort;
		this.paketart = paketart;
	}
	
	protected PackstueckTO(PackstueckTO other) {
		this.volumen = other.volumen;
		this.id = other.id;
		this.refnr = other.refnr;
		this.gewicht = other.gewicht;
		this.sendungsnummer = other.sendungsnummer;
		this.lagerort = other.lagerort;
		this.paketart = other.paketart;
	}

	@Override
	public String toString() {
		return "PackstueckTO{" +
				"volumen=" + volumen +
				", id=" + id +
				", refnr=" + refnr +
				", gewicht=" + gewicht +
				", sendungsnummer=" + sendungsnummer +
				", lagerort='" + lagerort + '\'' +
				", paketart=" + paketart +
				'}';
	}
}