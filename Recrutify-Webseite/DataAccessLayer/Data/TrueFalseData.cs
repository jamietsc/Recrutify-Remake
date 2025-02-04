using Recrutify.DataAccessLayer.Repositories;
using Recrutify.DataAccessLayer.SqlDataAccess;
using Recrutify.Models;

namespace Recrutify.DataAccessLayer.Data
{
    public class TrueFalseData: ITrueFalse<TrueFalseModel>
    {
        private readonly ISqlDataAccess _db;
        public TrueFalseData(ISqlDataAccess db)
        {
            _db = db;
        }

        public async Task<TrueFalseModel> GetTrueFalseQuestion(int TID, int FID)
        {
            string sqlQuery = "SELECT FID, Fragentext, Antwort_JaNein FROM Fragen WHERE TID = @TID AND FID = @FID;";
            var result = await _db.LoadData<TrueFalseModel, dynamic>(sqlQuery, new { TID, FID });
            return result.FirstOrDefault();
        }
    }
}
