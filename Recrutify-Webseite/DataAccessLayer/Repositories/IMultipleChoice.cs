using System.Collections.Generic;
using System.Threading.Tasks;
using Recrutify.Models;

namespace Recrutify.DataAccessLayer.Repositories
{
    //Repository für Methoden basierend auf Klasse MultipleChoiceModel
    public interface IMultipleChoice<T> where T : class
    {
        //Multiple-Choice-Fragen aus DB laden
        Task<MultipleChoiceModel> GetMultipleChoiceQuestion(int TID, int FID);
    }
}
