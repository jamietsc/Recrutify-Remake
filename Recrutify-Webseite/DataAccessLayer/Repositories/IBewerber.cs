using Recrutify.Models;

namespace Recrutify.DataAccessLayer.Repositories
{
    public interface IBewerber<T> where T : class
    {
        Task<int> InsertVornameNachname(T model);
        Task InsertPunktzahl(T model);

        Task<List<BewerberModel>> GetEverything(List<int> bewerberID);
    }
}
