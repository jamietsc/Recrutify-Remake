using Recrutify.Models;

namespace Recrutify.DataAccessLayer.Repositories
{
    //Klasse f�r Methoden basierend auf Klasse BewerberTestModel
    public interface IBewerberTest<T> where T : class
    {
        //TID und BID in der Verkn�pfungstabelle speichern
        Task InsertTIDandBID(T model);

        //Zu einer TID alle BIDs aus der DB laden
        Task<IEnumerable<BewerberTestModel>> GetBIDs(int? TID);
    }
}