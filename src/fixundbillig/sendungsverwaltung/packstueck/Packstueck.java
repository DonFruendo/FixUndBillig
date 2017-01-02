package fixundbillig.sendungsverwaltung.packstueck;

import fixundbillig.sendungsverwaltung.to.*;
import fixundbillig.sendungsverwaltung.utils.Paketart;

public class Packstueck extends PackstueckTO {

	public Packstueck(int volumen, int id, int refnr, int gewicht,
			SendungTO sendung, String lagerort, Paketart paketart) {
		super(volumen, id, refnr, gewicht, sendung, lagerort, paketart);
	}
	
	public Packstueck(PackstueckTO data) {
		super(data);
	}
	
}
