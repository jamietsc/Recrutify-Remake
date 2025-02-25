using Recrutify.Models;

namespace Recrutify.DataAccessLayer.Repositories
{
    //Repository für Methoden basierend auf Klasse AdminModel
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