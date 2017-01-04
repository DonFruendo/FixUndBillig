package fixundbillig.sendungsverwaltung.data.utils;

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
        String regex;
        if(strasse == null || hausnummer == null || plz == null || ort == null) {
            return false;
        }
        regex = "^[1-9]\\d*[a-z]{0,1}$";
        if(!hausnummer.matches(regex)) {
            return false;
        }
        regex = "^([A-z]|[0-9]|[ .-]|[äöüÄÖÜß])+$";
        if(!strasse.matches(regex) || !ort.matches(regex)) {
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
