using Recrutify.DataAccessLayer.Repositories;
using Recrutify.DataAccessLayer.SqlDataAccess;
using Recrutify.Models;

namespace Recrutify.DataAccessLayer.Data
{
    public class AdminData : IAdmin<AdminModel>
    {
        private readonly ISqlDataAccess _db;
        public AdminData(ISqlDataAccess db)
        {
            _db = db;
        }

        //Anmelde Daten überprüfen
        public async Task<bool> CheckCredentials(AdminModel model)
        {
            var parameters = new { model.Benutzername, model.Passwort };
            string sqlQuery = "SELECT COUNT(1) FROM Unternehmen WHERE Benutzername = @Benutzername AND Passwort = @Passwort;";

            var result = await _db.LoadData<int, dynamic>(sqlQuery, parameters);

            // Prüfen, ob mindestens ein Datensatz gefunden wurde
            return result.FirstOrDefault() > 0;
        }

        //zum eingegebenen Benutzernamen die UID aus der Datenbank holen und im Model abspeichern
        public async Task<int> GetUID(AdminModel model)
        {
            var paramters = new { model.Benutzername };
            string sqlQuery = "SELECT UID FROM Unternehmen WHERE Benutzername = @Benutzername;";
            var result = await _db.LoadData<int, dynamic>(sqlQuery, paramters);
            return result.FirstOrDefault();
        }

        //Zur UID die passenden TID aus der Datenbank holen 
        public async Task<IEnumerable<AdminModel>> GetTID(int UID)
        {
            string sqlQuery = "SELECT TID FROM Test WHERE UID = @UID;";
            return await _db.LoadData<AdminModel, dynamic>(sqlQuery, new { UID });
        }


    }
}