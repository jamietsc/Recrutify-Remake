using Dapper;
using Microsoft.Data.Sqlite;
using System.Data;

namespace Recrutify.DataAccessLayer.SqlDataAccess
{
    public class SqlDataAccess : ISqlDataAccess
    {
        private readonly IConfiguration _config;

        public SqlDataAccess(IConfiguration config)
        {
            _config = config;
        }

        public string ConnectionStringName { get; set; }

        //Daten laden
        public async Task<IEnumerable<T>> LoadData<T, U>(string storedProcedure, U parameters, string connectionID = "DefaultConnection")
        {
            using IDbConnection connection = new SqliteConnection(_config.GetSection("ConnectionStrings")[connectionID]);
            return await connection.QueryAsync<T>(storedProcedure, parameters, commandType: CommandType.Text); // SQLite nutzt in der Regel SQL Queries statt Stored Procedures
        }

        //Daten speicerhn
        public async Task SaveData<T>(string storedProcedure, T parameters, string connectionID = "DefaultConnection")
        {
            using IDbConnection connection = new SqliteConnection(_config.GetSection("ConnectionStrings")[connectionID]);
            await connection.ExecuteAsync(storedProcedure, parameters, commandType: CommandType.Text);
        }

        //Daten Speichern und ID zurückgeben
        public async Task<int> SaveDataReturnID<T>(string storedProcedure, T parameters, string connectionID = "DefaultConnection")
        {
            using IDbConnection connection = new SqliteConnection(_config.GetSection("ConnectionStrings")[connectionID]);
            return await connection.ExecuteScalarAsync<int>(storedProcedure, parameters, commandType: CommandType.Text);
        }
    }
}