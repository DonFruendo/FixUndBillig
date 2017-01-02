package fixundbillig.sendungsverwaltung.to;

import java.util.Date;
import java.util.List;

import fixundbillig.sendungsverwaltung.utils.*;

public class SendungTO {
	public String sendungsnummer;
	public Date Anlagedatum;
	public Adresse zielort;
	public String transportauftrag;
	public String kundenNr;
	public List<PackstueckTO> packstuecke;
}
