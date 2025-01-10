using Recrutify.DataAccessLayer.Repositories;
using Recrutify.DataAccessLayer.SqlDataAccess;
using Recrutify.Models;
using System.Data.SqlClient;

namespace Recrutify.DataAccessLayer.Data
{
    public class BewerberData : IBewerber<BewerberModel>
    {
        private readonly ISqlDataAccess _db;
        public BewerberData(ISqlDataAccess db)
        {
            _db = db;
        }

        //Vorname und Nachname in die Datenbank abspeichern,
        //BID wird dabei zurückgegeben und im Model abgespeichert
        public async Task<int> InsertVornameNachname(BewerberModel model)
        {
            // Definierung der Parameter für die SQL-Abfrage
            var parameters = new { model.Vorname, model.Nachname };

            // SQL-Abfrage ohne den Prefix "model."
            string sqlQuery = "INSERT INTO Bewerber (Vorname, Nachname) VALUES (@Vorname, @Nachname); SELECT last_insert_rowid();";

            // Aufruf der SaveDataReturnID Methode, um die ID des eingefügten Datensatzes zu erhalten
            int insertedID = await _db.SaveDataReturnID(sqlQuery, parameters);

            // Setze die BID des BewerberModels auf die eingefügte ID
            model.BID = insertedID;

            return insertedID;
        }

        //zum model die gesamtpunktzahl abspeichern
        public async Task InsertPunktzahl(BewerberModel model)
        {
            var paramter = new { model.Ergebnis, model.BID };
            string sqlQuery = "UPDATE Bewerber SET Ergebnis = @Ergebnis WHERE BID = @BID;";
            await _db.SaveData(sqlQuery, paramter);
        }

        //Alle Infos des Bewerbers aus der Datenbank laden
        public async Task<List<BewerberModel>> GetEverything(List<int> bewerberIDs)
        {
            var results = new List<BewerberModel>();

            foreach (var bewerberID in bewerberIDs)
            {
                string sqlQuery = "SELECT BID, Vorname, Nachname, Ergebnis FROM Bewerber WHERE BID = @bewerberID;";
                var parameter = new { bewerberID };

                var result = await _db.LoadData<BewerberModel, dynamic>(sqlQuery, parameter);
                results.AddRange(result); // Ergebnisse zur Liste hinzufügen
            }

            return results; // Gibt die Liste von BewerberModel zurück
        }

    }
}