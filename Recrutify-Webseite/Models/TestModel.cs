namespace Recrutify.Models
{
    //Model zum Speichern der Test Informationen
    public class TestModel
    {
        public int? TID { get; set; }
        public int Dauer { get; set; }
        public int UID { get; set; }
        public string Name { get; set; } = String.Empty;
    }
}
