/**
 * this class is used to create an object of the applicant to display them in the table correctly
 */
public class Bewerber {
    private String vorname;
    private String nachname;
    private String bewerberID;
    private Integer ergebnis;

    /**
     * Object of the applicant
     * @param BewerberID id of the applicant saved in the database
     * @param Vorname first name of the applicant
     * @param Nachname last name of the applicant
     * @param ergebnis test result of the applicant
     */
    public Bewerber(String BewerberID,String Vorname, String Nachname, int ergebnis) {
        this.bewerberID = BewerberID;
        this.vorname = Vorname;
        this.nachname = Nachname;
        this.ergebnis = ergebnis;
    }

    /**
     * get the first name of the applicant
     * @return first name of the applicant
     */
    public String getVorname() {
        return vorname;
    }

    /**
     * set the first name of the applicant
     * @param vorname surname of the applicant
     */
    public void setVorname(String vorname) {
        this.vorname = vorname;
    }

    /**
     * get the name of the applicant
     * @return name of the applicant
     */
    public String getNachname() {
        return nachname;
    }

    /**
     * set the name of the applicant
     * @param nachname name of the applicant
     */
    public void setNachname(String nachname) {
        this.nachname = nachname;
    }

    /**
    public String getBewerberID() {
        return bewerberID;
    } **/


    /**
     * get the result of the applicant
     * @return result of the applicant
     */
    public int getErgebnis() {
        return ergebnis;
    }

    /**
     * set the result of  the applicant
     * @param ergebnis result of the applicant
     */
    public void setErgebnis(int ergebnis) {
        this.ergebnis = ergebnis;
    }

    /**
     * method to print the current applicant
     * @return string of the current applicant information
     */
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
