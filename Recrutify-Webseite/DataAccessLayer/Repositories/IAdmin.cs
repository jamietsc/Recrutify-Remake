using Recrutify.Models;

namespace Recrutify.DataAccessLayer.Repositories
{
    public interface IAdmin<T> where T : class
    {
        //Anmeldedaten überprüfen
        Task<bool> CheckCredentials(T model);

        //Unternehmens ID aus der DB laden
        Task<int> GetUID(T model);

        //zur UID die passenden TID laden
        Task<IEnumerable<AdminModel>> GetTID(int UID);
    }
}