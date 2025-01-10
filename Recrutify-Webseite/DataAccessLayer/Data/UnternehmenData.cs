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

        public async Task<int> GetUIDFromTID(int TID)
        {
            var parameters = new { TID };
            string sqlQuery = "SELECT UID FROM Test WHERE TID = @TID";
            var result = await _db.LoadData<int, dynamic>(sqlQuery, parameters);
            return result.FirstOrDefault();
        }

        public async Task<String> GetName(int UID)
        {
            var parameters = new { UID };
            string sqlQuery = "SELECT Name FROM Unternehmen WHERE UID = @UID";
            var result = await _db.LoadData<String, dynamic>(sqlQuery, parameters);
            return result.FirstOrDefault();
        }
    }
}
