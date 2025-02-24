using Recrutify.Models;

namespace Recrutify.DataAccessLayer.Repositories
{
    //Repository für Methoden basierend auf Klasse BewerberModel
    public interface IBewerber<T> where T : class
    {
        //Vor- und Nachname in der Datenbank abspeichern
        //BID wird zurückgegeben
        Task<int> InsertVornameNachname(T model);

        //Punktzahl in die DB abspeichern
        Task InsertPunktzahl(T model);

        //alle Daten des Bewerbers aus der DB laden
        Task<List<BewerberModel>> GetEverything(List<int> bewerberID);
    }
}