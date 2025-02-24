using Recrutify.Models;

namespace Recrutify.DataAccessLayer.Repositories
{
    //Repository für Methoden basierend auf Klasse TrueFalseModel
    public interface ITrueFalse<T> where T : class
    {
        //Ja-Nein-Fragen aus DB laden
        Task<TrueFalseModel> GetTrueFalseQuestion(int TID, int FID);
    }
}
