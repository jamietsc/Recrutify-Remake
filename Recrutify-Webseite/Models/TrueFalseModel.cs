namespace Recrutify.Models
{
    public class TrueFalseModel
    {
        public int FID { get; set; }
        public string Fragentext { get; set; } = string.Empty;
        public Boolean Antwort_JaNein { get; set; }
        public int Gewaehlte_Antwort { get; set; } = 2;
    }
}
