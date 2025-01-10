using Recrutify.Models;

namespace Recrutify.DataAccessLayer.Repositories
{
    public interface IUnternehmen<T> where T : class
    {
        Task<int> GetUIDFromTID(int TID);

        Task<String> GetName(int UID);
    }
}
