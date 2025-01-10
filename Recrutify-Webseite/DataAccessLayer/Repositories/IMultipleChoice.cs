using System.Collections.Generic;
using System.Threading.Tasks;
using Recrutify.Models;

namespace Recrutify.DataAccessLayer.Repositories
{
    public interface IMultipleChoice<T> where T : class
    {
        //Alle MultipleChoice Fragen aus der Datenbank laden
        Task<IEnumerable<T>> GetMultipleChoice(int TID);
    }
}
