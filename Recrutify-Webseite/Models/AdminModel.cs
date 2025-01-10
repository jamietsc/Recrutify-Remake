namespace Recrutify.Models
{
    //Model für die Admin Seite
    public class AdminModel
    {
        public int UID { get; set; }
        public string Name { get; set; } = string.Empty;
        public string Benutzername { get; set; } = string.Empty;
        public string Passwort { get; set; } = string.Empty;
        public int TID { get; set; }
    }
}