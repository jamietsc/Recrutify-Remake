namespace Recrutify.Models
{
    //HelperModel für die Seiten anzahl
    public class HelperModel
    {
        public int currentFID { get; set; } = 1;
        public int firstFID { get; set; } = 1;
        public int currentQuestionNumber => currentFID - firstFID + 1;
        public int currentFreeTextAnswer { get; set; } = 0;
        public bool useTimer { get; set; } = true;
    }
}