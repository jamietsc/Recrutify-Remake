﻿using Recrutify.Models;

namespace Recrutify.DataAccessLayer.Repositories
{
    //Repository für Methoden basierend auf Klasse TestModel
    public interface IUnternehmen<T> where T : class
    {
        //UID von der TID erhalten
        Task<int> GetUIDFromTID(int TID);

        //Name des Unternehmens durch die UID erhalten
        Task<String> GetName(int UID);

        //Dauer des Tests durch die TID erhalten
        Task<int> GetDauer(int TID);

        //Überprüfen, ob TID existiert
        Task<bool> CheckTID(int TID);
    }
}
