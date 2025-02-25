using System.Collections.Generic;
using System.Threading.Tasks;
using Recrutify.Models;

namespace Recrutify.DataAccessLayer.Repositories
{
    //Repository für Methoden basierend auf Klasse FreeTextModel
    public interface IFreeText<T> where T : class
    {
        //Alle MultipleChoice Fragen aus der Datenbank laden
        Task<FreeTextModel> GetFreeTextQuestion(int TID, int FID);

        //Fügt Freitext-Antwort in DB ein
        Task InsertFreeTextAnswer(T model, int BID, int currentFreeTextAnswer);
    }
}
