package fixundbillig.sendungsverwaltung.utils;

public class Adresse {
    String strasse;
    String hausnummer;
    String plz;
	String ort;

    public Adresse(String strasse, String hausnummer, String plz, String ort) {
        this.strasse = strasse;
        this.hausnummer = hausnummer;
        this.plz = plz;
        this.ort = ort;
    }

    public boolean isValid() {
        if(strasse == null || hausnummer == null || plz == null || ort == null) {
            return false;
        }
        if(!hausnummer.matches("^[1-9]\\d*[a-z]$")) {
            return false;
        }

        return true;
    }

    @Override
    public String toString() {
        return "Adresse{" +
                "strasse='" + strasse + '\'' +
                ", hausnummer='" + hausnummer + '\'' +
                ", plz='" + plz + '\'' +
                ", ort='" + ort + '\'' +
                '}';
    }
}
