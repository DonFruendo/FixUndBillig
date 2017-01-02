package fixundbillig.sendungsverwaltung.to;

import fixundbillig.sendungsverwaltung.utils.Adresse;

import java.util.Date;
import java.util.List;

public class SendungTO {
	public String sendungsnummer;
	public Date anlagedatum;
	public Adresse zielort;
	public String transportauftrag;
	public String kundenNr;
	public List<PackstueckTO> packstuecke;

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
