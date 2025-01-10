namespace Recrutify.DataAccessLayer.SqlDataAccess
{
    public interface ISqlDataAccess
    {
        string ConnectionStringName { get; set; }

        Task<IEnumerable<T>> LoadData<T, U>(string storedProcedure, U parameters, string connectionId = "DefaultConnection");
        Task SaveData<T>(string storedProcedure, T paramters, string connectionId = "DefaultConnection");
        Task<int> SaveDataReturnID<T>(string storedProcedure, T parameters, string connectionId = "DefaultConnection");
    }
}
