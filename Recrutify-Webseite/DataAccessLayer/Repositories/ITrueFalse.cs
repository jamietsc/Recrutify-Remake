using Recrutify.Models;

namespace Recrutify.DataAccessLayer.Repositories
{
    public interface ITrueFalse<T> where T : class
    {
        Task<TrueFalseModel> GetTrueFalseQuestion(int TID, int FID);
    }
}
