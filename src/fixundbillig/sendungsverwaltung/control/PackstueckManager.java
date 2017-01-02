package fixundbillig.sendungsverwaltung.control;

import fixundbillig.sendungsverwaltung.packstueck.Packstueck;
import fixundbillig.sendungsverwaltung.to.PackstueckTO;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Don on 02.01.2017.
 *
 * @author Don
 * @since 02.01.2017
 */
public class PackstueckManager {

    private List<Packstueck> packstuecke;

    private static PackstueckManager ourInstance = new PackstueckManager();

    public static PackstueckManager getInstance() {
        return ourInstance;
    }

    private PackstueckManager() {
        packstuecke = new ArrayList<>();
    }


    public void addPackstueck(PackstueckTO packstueck) {
        packstuecke.add(new Packstueck(packstueck));
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
