package fixundbillig.sendungsverwaltung.data.utils;

public class Adresse {
    private final String strasse;
    private final String hausnummer;
    private final String plz;
	private final String ort;

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
        regex = "^[1-9]\\d*[a-z]?$";
        if(!hausnummer.matches(regex)) {
            return false;
        }
        regex = "^([A-z]|[0-9]|[ .-]|[äöüÄÖÜß])+$";
        if(!strasse.matches(regex) || !ort.matches(regex)) {
            return false;
        }
        regex = "^\\d{5}$";
        return !(!plz.matches(regex) || Integer.parseInt(plz) <= 1001);
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

    public String toDB() {
        return strasse + "/" + hausnummer + "/" + plz + "/" + ort;
    }

    public static Adresse fromDb(String adresse) {
        Adresse result = null;
        String[] split = adresse.split("/");
        if(split.length == 4) {
            result = new Adresse(split[0], split[1], split[2], split[3]);
        }
        return result;
    }

    public boolean equals(Object o) {
        if(!(o instanceof  Adresse)) {
            return false;
        }
        Adresse other = (Adresse)o;
        return this.toDB().equals(other.toDB());
    }
}
