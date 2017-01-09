package fixundbillig.sendungsverwaltung.data.sendung;

import fixundbillig.sendungsverwaltung.data.packstueck.PackstueckTO;
import fixundbillig.sendungsverwaltung.data.utils.Adresse;

import java.time.LocalDate;
import java.util.List;

public class SendungTO {
	public String sendungsnummer;
	public LocalDate anlagedatum;
	public Adresse zielort;
	public String transportauftrag;
	public String kundenNr;
	public List<PackstueckTO> packstuecke;

	public SendungTO() {
	}

	public SendungTO(String sendungsnummer, LocalDate anlagedatum, Adresse zielort, String transportauftrag, String kundenNr, List<PackstueckTO> packstuecke) {
		this.sendungsnummer = sendungsnummer;
		this.anlagedatum = anlagedatum;
		this.zielort = zielort;
		this.transportauftrag = transportauftrag;
		this.kundenNr = kundenNr;
		this.packstuecke = packstuecke;
	}

	public SendungTO(SendungTO other) {
        this.sendungsnummer = other.sendungsnummer;
        this.anlagedatum = other.anlagedatum;
        this.zielort = other.zielort;
        this.transportauftrag = other.transportauftrag;
        this.kundenNr = other.kundenNr;
        this.packstuecke = other.packstuecke;
    }

	@Override
	public String toString() {
		return "SendungTO{" +
				"sendungsnummer='" + sendungsnummer + '\'' +
				", anlagedatum=" + anlagedatum +
				", zielort=" + zielort +
				", transportauftrag='" + transportauftrag + '\'' +
				", kundenNr='" + kundenNr + '\'' +
				", packstuecke=" + packstuecke +
				'}';
	}
}
