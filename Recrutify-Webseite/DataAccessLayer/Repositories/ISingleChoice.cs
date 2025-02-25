using Recrutify.Models;

namespace Recrutify.DataAccessLayer.Repositories
{
    //Repository für Methoden basierend auf Klasse SingleChoiceModel
    public interface ISingleChoice<T> where T : class
    {
        //Single-Choice-Fragen aus DB laden
        Task<SingleChoiceModel> GetSingleChoiceQuestion(int TID, int FID);
    }
}
