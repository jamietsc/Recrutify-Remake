using Recrutify.DataAccessLayer.Repositories;
using Recrutify.DataAccessLayer.SqlDataAccess;
using Recrutify.Models;

namespace Recrutify.DataAccessLayer.Data
{
    //Klasse für Methoden basierend auf Klasse BewerberTestModel
    public class BewerberTestData : IBewerberTest<BewerberTestModel>
    {
        private readonly ISqlDataAccess _db;
        public BewerberTestData(ISqlDataAccess db)
        {
            _db = db;
        }

        //TID und BID in der Datenbank abspeichern 
        public async Task InsertTIDandBID(BewerberTestModel model)
        {
            var paramters = new { model.TID, model.BID };
            string sqlQuery = "INSERT INTO Bewerber_Test (TID, BID) VALUES (@TID, @BID); SELECT last_insert_rowid();";
            await _db.SaveData(sqlQuery, paramters);
        }

        //Zur TID alle BIDs aus der Datenbank laden -> für Adminpage
        public async Task<IEnumerable<BewerberTestModel>> GetBIDs(int? TID)
        {
            string sqlQuery = "SELECT BID FROM Bewerber_Test WHERE TID = @TID";
            return await _db.LoadData<BewerberTestModel, dynamic>(sqlQuery, new { TID });
        }
    }
}