using Recrutify.DataAccessLayer.Repositories;
using Recrutify.DataAccessLayer.SqlDataAccess;
using Recrutify.Models;

namespace Recrutify.DataAccessLayer.Data
{
    //Klasse für Methoden basierend auf Klasse QuestionTypeModel
    public class QuestionTypeData : IQuestionType<QuestionTypeModel>
    {
        private readonly ISqlDataAccess _db;
        public QuestionTypeData(ISqlDataAccess db)
        {
            _db = db;
        }

        //Fragentypen aus DB laden
        public async Task<IEnumerable<QuestionTypeModel>> GetQuestionTypes(int TID)
        {
            string sqlQuery = "SELECT FID, Fragentyp FROM Fragen WHERE TID = @TID;";

            return await _db.LoadData<QuestionTypeModel, dynamic>(sqlQuery, new { TID });
        }
    }
}
