package fixundbillig.sendungsverwaltung.data.packstueck;

import fixundbillig.sendungsverwaltung.data.utils.Paketart;

public class PackstueckTO {
	public int id;
	public double volumen;
	public double gewicht;
	public int refnr;
	public String sendungsnummer;
	public String lagerort;
	public Paketart paketart;
	
	public PackstueckTO() {
    }
	
	public PackstueckTO(int id, int volumen, int gewicht, int refnr,
			String sendungsnummer, String lagerort, Paketart paketart) {
		super();
		this.id = id;
		this.volumen = volumen;
		this.gewicht = gewicht;
		this.refnr = refnr;
		this.sendungsnummer = sendungsnummer;
		this.lagerort = lagerort;
		this.paketart = paketart;
	}
	
	protected PackstueckTO(PackstueckTO other) {
		this.id = other.id;
		this.volumen = other.volumen;
		this.gewicht = other.gewicht;
		this.refnr = other.refnr;
		this.sendungsnummer = other.sendungsnummer;
		this.lagerort = other.lagerort;
		this.paketart = other.paketart;
	}

	@Override
	public String toString() {
		return "PackstueckTO{" +
				"id=" + id +
				", volumen=" + volumen +
				", gewicht=" + gewicht +
				", refnr=" + refnr +
				", sendungsnummer=" + sendungsnummer +
				", lagerort='" + lagerort + '\'' +
				", paketart=" + paketart +
				'}';
	}
}