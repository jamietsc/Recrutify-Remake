using System.Collections.Generic;
using System.Threading.Tasks;
using Recrutify.Models;

namespace Recrutify.DataAccessLayer.Repositories
{
    public interface IMultipleChoice<T> where T : class
    {
        //Alle MultipleChoice Fragen aus der Datenbank laden
        Task<MultipleChoiceModel> GetMultipleChoiceQuestion(int TID, int FID);
    }
}
