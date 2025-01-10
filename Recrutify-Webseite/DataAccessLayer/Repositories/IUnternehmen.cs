using Recrutify.Models;

namespace Recrutify.DataAccessLayer.Repositories
{
    public interface IUnternehmen<T> where T : class
    {
        //UID von der TID erhalten
        Task<int> GetUIDFromTID(int TID);

        //Name des Unternehmens durch die UID erhalten
        Task<String> GetName(int UID);

        //Dauer des Tests durch die TID erhalten
        Task<int> GetDauer(int TID);
    }
}
