namespace Recrutify.Models
{
    //Model zum Abspeichern der Multiple Choice Fragen
    public class MultipleChoiceModel
    {
        public int FID { get; set; }
        public string Fragentext { get; set; } = string.Empty;
        public string Antwort_1 { get; set; } = string.Empty;
        public string Antwort_2 { get; set; } = string.Empty;
        public string Antwort_3 { get; set; } = string.Empty;
        public string Antwort_4 { get; set; } = string.Empty;
        public Boolean Richtig_1 { get; set; }
        public Boolean Richtig_2 { get; set; }
        public Boolean Richtig_3 { get; set; }
        public Boolean Richtig_4 { get; set; }
        public Boolean Gewaehlte_Antwort_1 { get; set; } = false;
        public Boolean Gewaehlte_Antwort_2 { get; set; } = false;
        public Boolean Gewaehlte_Antwort_3 { get; set; } = false;
        public Boolean Gewaehlte_Antwort_4 { get; set; } = false;
    }
}