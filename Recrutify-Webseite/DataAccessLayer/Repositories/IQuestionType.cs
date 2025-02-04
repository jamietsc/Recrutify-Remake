using Recrutify.Models;

namespace Recrutify.DataAccessLayer.Repositories
{
    public interface IQuestionType<T> where T : class
    {
        Task<IEnumerable<T>> GetQuestionTypes(int TID);
    }
}
