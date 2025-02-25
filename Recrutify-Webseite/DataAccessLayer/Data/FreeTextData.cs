using Recrutify.DataAccessLayer.Repositories;
using Recrutify.DataAccessLayer.SqlDataAccess;
using Recrutify.Models;
using System.Reflection.Metadata;

namespace Recrutify.DataAccessLayer.Data
{
    //Klasse für Methoden basierend auf Klasse FreeTextModel
    public class FreeTextData : IFreeText<FreeTextModel>
    {
        private readonly ISqlDataAccess _db;
        public FreeTextData(ISqlDataAccess db)
        {
            _db = db;
        }

        //Lädt eine Freitext-Frage aus der Datenbank
        public async Task<FreeTextModel> GetFreeTextQuestion(int TID, int FID)
        {
            string sqlQuery = "SELECT FID, Fragentext FROM Fragen WHERE TID = @TID AND FID = @FID;";
            var result = await _db.LoadData<FreeTextModel, dynamic>(sqlQuery, new { TID, FID });
            return result.FirstOrDefault();
        }

        //Fügt Freitext-Antwort in DB ein
        public async Task InsertFreeTextAnswer(FreeTextModel freeTextModel, int BID, int currentFreeTextAnswer)
        {
            object parameter;
            string sqlQuery;
            if (currentFreeTextAnswer == 1)
            {
                parameter = new { freeTextModel.Antwort_Freitext_1, BID };
                sqlQuery = "UPDATE Bewerber SET Antwort_Freitext_1 = @Antwort_Freitext_1 WHERE BID = @BID;";
                await _db.SaveData(sqlQuery, parameter);
            }
            else if (currentFreeTextAnswer == 2)
            {
                parameter = new { freeTextModel.Antwort_Freitext_2, BID };
                sqlQuery = "UPDATE Bewerber SET Antwort_Freitext_2 = @Antwort_Freitext_2 WHERE BID = @BID;";
                await _db.SaveData(sqlQuery, parameter);
            }
            else if (currentFreeTextAnswer == 3)
            {
                parameter = new { freeTextModel.Antwort_Freitext_3, BID };
                sqlQuery = "UPDATE Bewerber SET Antwort_Freitext_3 = @Antwort_Freitext_3 WHERE BID = @BID;";
                await _db.SaveData(sqlQuery, parameter);
            } else
            {
                throw new ArgumentException("Ungültiger Wert für currentFreeTextAnswer");
            }


        }
    }
}
