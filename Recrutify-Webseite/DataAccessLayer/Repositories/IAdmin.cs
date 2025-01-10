using Recrutify.Models;

namespace Recrutify.DataAccessLayer.Repositories
{
    public interface IAdmin<T> where T : class
    {
        Task<bool> CheckCredentials(T model);
        Task<int> GetUID (T model);
        Task<IEnumerable<AdminModel>> GetTID(int UID);
    }
}
