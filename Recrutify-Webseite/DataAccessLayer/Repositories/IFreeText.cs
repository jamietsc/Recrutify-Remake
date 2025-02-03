using System.Collections.Generic;
using System.Threading.Tasks;
using Recrutify.Models;

namespace Recrutify.DataAccessLayer.Repositories
{
    public interface IFreeText<T> where T : class
    {
        //Alle MultipleChoice Fragen aus der Datenbank laden
        Task<FreeTextModel> GetFreeTextQuestion(int TID, int FID);

        Task InsertFreeTextAnswer(T model, int BID, int currentFreeTextAnswer);
    }
}
