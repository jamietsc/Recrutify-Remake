/**
 * this class is used to create an object of the applicant to display them in the table correctly
 */
public class Bewerber {
    private String vorname;
    private String nachname;
    private String bewerberID;
    private String text_1;
    private String text_2;
    private String text_3;
    private Integer ergebnis;

    /**
     * Object of the applicant
     * @param BewerberID id of the applicant saved in the database
     * @param Vorname first name of the applicant
     * @param Nachname last name of the applicant
     * @param text_1 first answer in free text
     * @param text_2 second answer in free text
     * @param text_3 third answer in free text
     * @param ergebnis test result of the applicant
     */
    public Bewerber(String BewerberID,String Vorname, String Nachname, String text_1, String text_2, String text_3, int ergebnis) {
        this.bewerberID = BewerberID;
        this.vorname = Vorname;
        this.nachname = Nachname;
        this.text_1 = text_1;
        this.text_2 = text_2;
        this.text_3 = text_3;
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

    public String getText_1() {
        return text_1;
    }

    public void setText_1(String text_1) {
        this.text_1 = text_1;
    }

    public String getText_2() {
        return text_2;
    }

    public void setText_2(String text_2) {
        this.text_2 = text_2;
    }

    public String getText_3() {
        return text_3;
    }

    public void setText_3(String text_3) {
        this.text_3 = text_3;
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
