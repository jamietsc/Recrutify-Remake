using Recrutify.DataAccessLayer.Repositories;
using Recrutify.DataAccessLayer.SqlDataAccess;
using Recrutify.Models;

namespace Recrutify.DataAccessLayer.Data
{
    public class SingleChoiceData : ISingleChoice<SingleChoiceModel>
    {
        private readonly ISqlDataAccess _db;
        public SingleChoiceData(ISqlDataAccess db)
        {
            _db = db;
        }

        //sämtliche Fragen zu einem Test aus der Datenbank laden
        public async Task<SingleChoiceModel> GetSingleChoiceQuestion(int TID, int FID)
        {
            string sqlQuery = "SELECT FID, Fragentext, Antwort_1, Antwort_2, Antwort_3, Antwort_4," +
                "Richtig_1, Richtig_2, Richtig_3, Richtig_4 FROM Fragen WHERE TID = @TID AND FID = @FID;";
            var result = await _db.LoadData<SingleChoiceModel, dynamic>(sqlQuery, new { TID , FID });
            return result.FirstOrDefault();
        }


    }
}
