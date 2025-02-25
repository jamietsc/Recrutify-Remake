using Recrutify.Models;

namespace Recrutify.DataAccessLayer.Repositories
{
    //Repository für Methoden basierend auf Klasse QuestionTypeModel
    public interface IQuestionType<T> where T : class
    {
        //Fragentypen aus DB laden
        Task<IEnumerable<T>> GetQuestionTypes(int TID);
    }
}
