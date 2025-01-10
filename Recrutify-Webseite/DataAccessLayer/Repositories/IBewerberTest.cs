using Recrutify.Models;

namespace Recrutify.DataAccessLayer.Repositories
{
    public interface IBewerberTest<T> where T : class
    {
        //TID und BID in der Verknüpfungstabelle speichern
        Task InsertTIDandBID(T model);

        //Zu einer TID alle BIDs aus der DB laden
        Task<IEnumerable<BewerberTestModel>> GetBIDs(int? TID);
    }
}