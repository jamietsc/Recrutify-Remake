namespace Recrutify.DataAccessLayer.SqlDataAccess
{
    public interface ISqlDataAccess
    {
        string ConnectionStringName { get; set; }

        //Daten laden
        Task<IEnumerable<T>> LoadData<T, U>(string storedProcedure, U parameters, string connectionId = "DefaultConnection");

        //Daten speicerhn
        Task SaveData<T>(string storedProcedure, T paramters, string connectionId = "DefaultConnection");

        //Daten Speichern und ID zurückgeben
        Task<int> SaveDataReturnID<T>(string storedProcedure, T parameters, string connectionId = "DefaultConnection");
    }
}