package fixundbillig.sendungsverwaltung.control;

import fixundbillig.sendungsverwaltung.packstueck.Packstueck;
import fixundbillig.sendungsverwaltung.to.PackstueckTO;
import fixundbillig.sendungsverwaltung.utils.Logger;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

/**
 * Created by Don on 02.01.2017.
 *
 * @author Don
 * @since 02.01.2017
 */
public class PackstueckManager {

    private Set<Packstueck> packstuecke;

    private static PackstueckManager ourInstance = new PackstueckManager();

    public static PackstueckManager getInstance() {
        return ourInstance;
    }

    private PackstueckManager() {
        packstuecke = new HashSet<>();
    }


    public void addPackstueck(PackstueckTO packstueckTO) {
        Packstueck packstueck = new Packstueck(packstueckTO);
        boolean setNotContaining = true;
        for(Packstueck p: packstuecke) {
            if(p.equals(packstueck)) {
                setNotContaining = false;
                break;
            }
        }
        if(setNotContaining) {
            packstuecke.add(packstueck);
            Logger.log("Added: " + packstueck);
        } else {
            Logger.log("Exists already: " + packstueck);
        }
    }

    public Packstueck getPackstueck(PackstueckTO packstueckTO) {
        Packstueck find = null;
        for(Packstueck packstueck : packstuecke) {
            if(packstueckTO.id == packstueck.getId()) {
                find = packstueck;
                break;
            }
        }
        return find;
    }
}
