package fixundbillig.sendungsverwaltung.data.packstueck;

import fixundbillig.sendungsverwaltung.data.utils.Paketart;

public class PackstueckTO {
	public int id;
	public int volumen;
	public int refnr;
	public int gewicht;
	public String sendungsnummer;
	public String lagerort;
	public Paketart paketart;
	
	public PackstueckTO() {
    }
	
	public PackstueckTO(int id, int volumen, int refnr, int gewicht,
			String sendungsnummer, String lagerort, Paketart paketart) {
		super();
		this.id = id;
		this.volumen = volumen;
		this.refnr = refnr;
		this.gewicht = gewicht;
		this.sendungsnummer = sendungsnummer;
		this.lagerort = lagerort;
		this.paketart = paketart;
	}
	
	protected PackstueckTO(PackstueckTO other) {
		this.id = other.id;
		this.volumen = other.volumen;
		this.refnr = other.refnr;
		this.gewicht = other.gewicht;
		this.sendungsnummer = other.sendungsnummer;
		this.lagerort = other.lagerort;
		this.paketart = other.paketart;
	}

	@Override
	public String toString() {
		return "PackstueckTO{" +
				"id=" + id +
				", volumen=" + volumen +
				", refnr=" + refnr +
				", gewicht=" + gewicht +
				", sendungsnummer=" + sendungsnummer +
				", lagerort='" + lagerort + '\'' +
				", paketart=" + paketart +
				'}';
	}
}