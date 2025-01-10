namespace Recrutify.Models
{
    public class BewerberModel
    {
        public int BID { get; set; }
        public string Vorname { get; set; } = string.Empty;
        public string Nachname { get; set; } = string.Empty;
        public int Ergebnis { get; set; }

        public int TID { get; set; }
    }
}