using Dapper;
using Microsoft.Extensions.Configuration;
using Npgsql;
using System.Data;
using System.Threading.Tasks;

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
        public async Task<IEnumerable<T>> LoadData<T, U>(string sql, U parameters, string connectionID = "DefaultConnection")
        {
            using IDbConnection connection = new NpgsqlConnection(_config.GetSection("ConnectionStrings")[connectionID]);
            return await connection.QueryAsync<T>(sql, parameters); // SQLite nutzt in der Regel SQL Queries statt Stored Procedures
        }

        //Daten speicerhn
        public async Task SaveData<T>(string sql, T parameters, string connectionID = "DefaultConnection")
        {
            using IDbConnection connection = new NpgsqlConnection(_config.GetSection("ConnectionStrings")[connectionID]);
            await connection.ExecuteAsync(sql, parameters);
        }

        //Daten Speichern und ID zurückgeben
        public async Task<int> SaveDataReturnID<T>(string sql, T parameters, string connectionID = "DefaultConnection")
        {
            using IDbConnection connection = new NpgsqlConnection(_config.GetSection("ConnectionStrings")[connectionID]);
            return await connection.ExecuteScalarAsync<int>(sql, parameters);
        }
    }
}