using Recrutify.DataAccessLayer.Repositories;
using Recrutify.DataAccessLayer.SqlDataAccess;
using Recrutify.Data;
using Recrutify.Models;

namespace Recrutify.DataAccessLayer.Data
{
    public class MultipleChoiceData : IMultipleChoice<MultipleChoiceModel>
    {
        private readonly ISqlDataAccess _db;
        public MultipleChoiceData(ISqlDataAccess db)
        {
            _db = db;
        }

        //sämtliche Fragen zu einem Test aus der Datenbank laden
        public async Task<IEnumerable<MultipleChoiceModel>> GetMultipleChoice(int TID)
        {
            string sqlQuery = "SELECT FID, Text, Antwort_1, Antwort_2, Antwort_3, Antwort_4," +
                "Richtig_1, Richtig_2, Richtig_3, Richtig_4 FROM MultipleChoiceFragen WHERE TID = @TID;";

            return await _db.LoadData<MultipleChoiceModel, dynamic>(sqlQuery, new { TID });
        }


    }
}
 