using Recrutify.Models;

namespace Recrutify.DataAccessLayer.Repositories
{
    public interface ISingleChoice<T> where T : class
    {
        Task<SingleChoiceModel> GetSingleChoiceQuestion(int TID, int FID);
    }
}
