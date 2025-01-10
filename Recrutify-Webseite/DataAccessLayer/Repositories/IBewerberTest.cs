using Recrutify.Models;

namespace Recrutify.DataAccessLayer.Repositories
{
    public interface IBewerberTest<T> where T : class
    {
        Task InsertTIDandBID(T model);

        Task<IEnumerable<BewerberTestModel>> GetBIDs(int? TID);
    }
}
