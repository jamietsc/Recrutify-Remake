public class Bewerber {
    private String vorname;
    private String nachname;
    private String bewerberID;
    private Integer ergebnis;

    public Bewerber(String BewerberID,String Vorname, String Nachname, int ergebnis) {
        this.bewerberID = BewerberID;
        this.vorname = Vorname;
        this.nachname = Nachname;
        this.ergebnis = ergebnis;
    }

    public String getVorname() {
        return vorname;
    }

    public void setVorname(String vorname) {
        this.vorname = vorname;
    }

    public String getNachname() {
        return nachname;
    }

    public void setNachname(String nachname) {
        this.nachname = nachname;
    }

    /**
    public String getBewerberID() {
        return bewerberID;
    } **/


    public int getErgebnis() {
        return ergebnis;
    }

    public void setErgebnis(int ergebnis) {
        this.ergebnis = ergebnis;
    }

    @Override
    public String toString() {
        return "Bewerber{" +
                "vorname='" + vorname + '\'' +
                ", nachname='" + nachname + '\'' +
                ", bewerberID='" + bewerberID + '\'' +
                ", ergebnis=" + ergebnis +
                '}';
    }
}
