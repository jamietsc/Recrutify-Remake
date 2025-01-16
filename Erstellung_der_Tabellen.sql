-- Tabelle Unternehmen
CREATE TABLE Unternehmen (
                             UID INTEGER PRIMARY KEY AUTOINCREMENT,
                             Name VARCHAR(255) NOT NULL,
                             Benutzername VARCHAR(255) NOT NULL,
                             Passwort VARCHAR(255) NOT NULL,
                             Vorname VARCHAR(255),
                             Nachname VARCHAR(255),
                             is_admin BOOLEAN NOT NULL DEFAULT 0
);

-- Tabelle Test
CREATE TABLE Test (
                      TID INT PRIMARY KEY,
                      Dauer TIME,
                      UID INT,
                      FOREIGN KEY (UID) REFERENCES Unternehmen(UID)
);

-- Tabelle Bewerber
CREATE TABLE Bewerber (
                          BID INT PRIMARY KEY,
                          Vorname VARCHAR(255),
                          Nachname VARCHAR(255),
                          Ergebnis INT
);

-- Tabelle Multiple-Choice-Fragen
CREATE TABLE MultipleChoiceFragen (
                                      FID INT PRIMARY KEY,
                                      Text TEXT,
                                      Antwort_1 TEXT,
                                      Antwort_2 TEXT,
                                      Antwort_3 TEXT,
                                      Antwort_4 TEXT,
                                      Richtig_1 BOOLEAN,
                                      Richtig_2 BOOLEAN,
                                      Richtig_3 BOOLEAN,
                                      Richtig_4 BOOLEAN,
                                      TID INT,
                                      FOREIGN KEY (TID) REFERENCES Test(TID)
);

-- Tabelle Bewerber_Test (Beziehung zwischen Bewerber und Test)
CREATE TABLE Bewerber_Test (
                               BID INT,
                               TID INT,
                               PRIMARY KEY (BID, TID),
                               FOREIGN KEY (BID) REFERENCES Bewerber(BID),
                               FOREIGN KEY (TID) REFERENCES Test(TID)
);

INSERT INTO Unternehmen (Name, Benutzername, Passwort, Vorname, Nachname , is_admin)
VALUES  ('FBB', 'JentschJ', 1234, 'Jamie', 'Jentsch', 1);

DELETE FROM Test WHERE TID LIKE NULL;

INSERT INTO Test (TID, Dauer, UID) VALUES (1, 1, 2);
INSERT INTO Test (TID, Dauer, UID) VALUES (2, 1, 2);
INSERT INTO Test (TID, Dauer, UID) VALUES (3, 1, 2);