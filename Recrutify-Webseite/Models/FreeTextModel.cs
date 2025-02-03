namespace Recrutify.Models
{
    public class FreeTextModel
    {
        public int FID { get; set; }
        public String Fragentext { get; set; } = string.Empty;

        public String Antwort_Freitext_1 {  get; set; } = string.Empty;
        public String Antwort_Freitext_2 { get; set; } = string.Empty;
        public String Antwort_Freitext_3 { get; set; } = string.Empty;
    }
}
