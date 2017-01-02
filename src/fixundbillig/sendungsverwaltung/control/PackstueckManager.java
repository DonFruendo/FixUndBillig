package fixundbillig.sendungsverwaltung.control;

import java.util.ArrayList;

import fixundbillig.sendungsverwaltung.packstueck.Packstueck;
import fixundbillig.sendungsverwaltung.to.PackstueckTO;

public class PackstueckManager {
	public static PackstueckManager getInstance() {
		return null;
	}
	
	ArrayList<Packstueck> dingsen;
	
	public void addPackstueck(PackstueckTO packstueck) {
		dingsen.add(new Packstueck(packstueck));
	}
}
