using Recrutify.DataAccessLayer.Repositories;
using Recrutify.DataAccessLayer.SqlDataAccess;
using Recrutify.Models;

namespace Recrutify.DataAccessLayer.Data
{
    public class UnternehmenData : IUnternehmen<TestModel>
    {
        private readonly ISqlDataAccess _db;

        public UnternehmenData(ISqlDataAccess db)
        {
            _db = db;
        }

        //zur TestID die passende UnternehmensID holen
        public async Task<int> GetUIDFromTID(int TID)
        {
            var parameters = new { TID };
            string sqlQuery = "SELECT UID FROM Test WHERE TID = @TID";
            var result = await _db.LoadData<int, dynamic>(sqlQuery, parameters);
            return result.FirstOrDefault();
        }

        //Namen des Unternehmens aus der DB zu passenden Datenbank holen
        public async Task<String> GetName(int UID)
        {
            var parameters = new { UID };
            string sqlQuery = "SELECT Name FROM Unternehmen WHERE UID = @UID";
            var result = await _db.LoadData<String, dynamic>(sqlQuery, parameters);
            return result.FirstOrDefault();
        }

        //Dauer des Tests aus der DB holen
        public async Task<int> GetDauer(int TID)
        {
            var parameters = new { TID };
            string sqlQuery = "SELECT Dauer FROM Test WHERE TID = @TID";
            var result = await _db.LoadData<int, dynamic>(sqlQuery, parameters);
            return result.FirstOrDefault();
        }
    }
}
