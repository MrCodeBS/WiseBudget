-- Kantone
INSERT INTO Kanton (name, kuerzel) VALUES ('Zürich', 'ZH');
INSERT INTO Kanton (name, kuerzel) VALUES ('Bern', 'BE');
INSERT INTO Kanton (name, kuerzel) VALUES ('Zug', 'ZG');

-- Steuerregeln für 2026
-- Quelle Richtwerte: ESTV Steuerrechner (https://swisstaxcalculator.estv.admin.ch)
-- Durchschnittliche Steuerbelastung (Bund, Kanton, Gemeinde) in % des Bruttoarbeitseinkommens
-- für Alleinstehende (ledig, ohne Kinder, konfessionslos) in den Kantonshauptorten Zürich, Bern und Zug.

-- Zürich (kanton_id = 1, Hauptort Zürich)
INSERT INTO Steuerregel (kanton_id, erwerbsstatus, alterVon, alterBis, einkommenVon, einkommenBis, satzProzent, gueltigJahr)
VALUES (1, 'LEHRLING', 14, 30, 0.00, 1500.00, 0.50, 2026);
INSERT INTO Steuerregel (kanton_id, erwerbsstatus, alterVon, alterBis, einkommenVon, einkommenBis, satzProzent, gueltigJahr)
VALUES (1, 'LEHRLING', 14, 30, 1500.01, 5000.00, 3.10, 2026);
INSERT INTO Steuerregel (kanton_id, erwerbsstatus, alterVon, alterBis, einkommenVon, einkommenBis, satzProzent, gueltigJahr)
VALUES (1, 'ANGESTELLT', 18, 65, 0.00, 4500.00, 7.73, 2026);
INSERT INTO Steuerregel (kanton_id, erwerbsstatus, alterVon, alterBis, einkommenVon, einkommenBis, satzProzent, gueltigJahr)
VALUES (1, 'ANGESTELLT', 18, 65, 4500.01, 7500.00, 11.28, 2026);
INSERT INTO Steuerregel (kanton_id, erwerbsstatus, alterVon, alterBis, einkommenVon, einkommenBis, satzProzent, gueltigJahr)
VALUES (1, 'ANGESTELLT', 18, 65, 7500.01, 25000.00, 14.36, 2026);
INSERT INTO Steuerregel (kanton_id, erwerbsstatus, alterVon, alterBis, einkommenVon, einkommenBis, satzProzent, gueltigJahr)
VALUES (1, 'STUDENT', 18, 35, 0.00, 5000.00, 2.50, 2026);
INSERT INTO Steuerregel (kanton_id, erwerbsstatus, alterVon, alterBis, einkommenVon, einkommenBis, satzProzent, gueltigJahr)
VALUES (1, 'ANDERE', 14, 99, 0.00, 15000.00, 7.73, 2026);

-- Bern (kanton_id = 2, Hauptort Bern)
INSERT INTO Steuerregel (kanton_id, erwerbsstatus, alterVon, alterBis, einkommenVon, einkommenBis, satzProzent, gueltigJahr)
VALUES (2, 'LEHRLING', 14, 30, 0.00, 1500.00, 1.20, 2026);
INSERT INTO Steuerregel (kanton_id, erwerbsstatus, alterVon, alterBis, einkommenVon, einkommenBis, satzProzent, gueltigJahr)
VALUES (2, 'LEHRLING', 14, 30, 1500.01, 5000.00, 5.40, 2026);
INSERT INTO Steuerregel (kanton_id, erwerbsstatus, alterVon, alterBis, einkommenVon, einkommenBis, satzProzent, gueltigJahr)
VALUES (2, 'ANGESTELLT', 18, 65, 0.00, 4500.00, 13.40, 2026);
INSERT INTO Steuerregel (kanton_id, erwerbsstatus, alterVon, alterBis, einkommenVon, einkommenBis, satzProzent, gueltigJahr)
VALUES (2, 'ANGESTELLT', 18, 65, 4500.01, 7500.00, 16.61, 2026);
INSERT INTO Steuerregel (kanton_id, erwerbsstatus, alterVon, alterBis, einkommenVon, einkommenBis, satzProzent, gueltigJahr)
VALUES (2, 'ANGESTELLT', 18, 65, 7500.01, 25000.00, 19.30, 2026);
INSERT INTO Steuerregel (kanton_id, erwerbsstatus, alterVon, alterBis, einkommenVon, einkommenBis, satzProzent, gueltigJahr)
VALUES (2, 'STUDENT', 18, 35, 0.00, 5000.00, 4.20, 2026);
INSERT INTO Steuerregel (kanton_id, erwerbsstatus, alterVon, alterBis, einkommenVon, einkommenBis, satzProzent, gueltigJahr)
VALUES (2, 'ANDERE', 14, 99, 0.00, 15000.00, 13.40, 2026);

-- Zug (kanton_id = 3, Hauptort Zug)
INSERT INTO Steuerregel (kanton_id, erwerbsstatus, alterVon, alterBis, einkommenVon, einkommenBis, satzProzent, gueltigJahr)
VALUES (3, 'LEHRLING', 14, 30, 0.00, 1500.00, 0.00, 2026);
INSERT INTO Steuerregel (kanton_id, erwerbsstatus, alterVon, alterBis, einkommenVon, einkommenBis, satzProzent, gueltigJahr)
VALUES (3, 'LEHRLING', 14, 30, 1500.01, 5000.00, 1.20, 2026);
INSERT INTO Steuerregel (kanton_id, erwerbsstatus, alterVon, alterBis, einkommenVon, einkommenBis, satzProzent, gueltigJahr)
VALUES (3, 'ANGESTELLT', 18, 65, 0.00, 4500.00, 3.23, 2026);
INSERT INTO Steuerregel (kanton_id, erwerbsstatus, alterVon, alterBis, einkommenVon, einkommenBis, satzProzent, gueltigJahr)
VALUES (3, 'ANGESTELLT', 18, 65, 4500.01, 7500.00, 5.37, 2026);
INSERT INTO Steuerregel (kanton_id, erwerbsstatus, alterVon, alterBis, einkommenVon, einkommenBis, satzProzent, gueltigJahr)
VALUES (3, 'ANGESTELLT', 18, 65, 7500.01, 25000.00, 7.52, 2026);
INSERT INTO Steuerregel (kanton_id, erwerbsstatus, alterVon, alterBis, einkommenVon, einkommenBis, satzProzent, gueltigJahr)
VALUES (3, 'STUDENT', 18, 35, 0.00, 5000.00, 0.80, 2026);
INSERT INTO Steuerregel (kanton_id, erwerbsstatus, alterVon, alterBis, einkommenVon, einkommenBis, satzProzent, gueltigJahr)
VALUES (3, 'ANDERE', 14, 99, 0.00, 15000.00, 3.23, 2026);

-- Testbenutzer (Passwort für alle: passwort123)
INSERT INTO Benutzer (name, email, passwortHash, rolle, geburtsdatum, erwerbsstatus, kanton_id)
VALUES ('Admin', 'admin@wisebudget.ch', '$2a$10$T0jxXSHzXEbjbDLfZFOvLeHbpSxVAN7kZp.OsYzECffKRVmV6uSaq', 'ADMIN', '1985-04-12', 'ANGESTELLT', 1);

INSERT INTO Benutzer (name, email, passwortHash, rolle, geburtsdatum, erwerbsstatus, kanton_id)
VALUES ('Lukas Meier', 'lukas@wisebudget.ch', '$2a$10$T0jxXSHzXEbjbDLfZFOvLeHbpSxVAN7kZp.OsYzECffKRVmV6uSaq', 'USER', '2008-06-15', 'LEHRLING', 1);

INSERT INTO Benutzer (name, email, passwortHash, rolle, geburtsdatum, erwerbsstatus, kanton_id)
VALUES ('Sarah Keller', 'sarah@wisebudget.ch', '$2a$10$T0jxXSHzXEbjbDLfZFOvLeHbpSxVAN7kZp.OsYzECffKRVmV6uSaq', 'USER', '2001-09-20', 'ANGESTELLT', 2);

-- Gemeinsames Testbudget für Lukas (BESITZER) und Sarah (SCHREIBEN)
INSERT INTO Budget (name, typ, needsProzent, wantsProzent, sparenProzent, version)
VALUES ('WG Budget Zürich', 'GEMEINSAM', 50.00, 30.00, 20.00, 0);

INSERT INTO Budgetmitglied (benutzer_id, budget_id, berechtigung)
VALUES (2, 1, 'BESITZER');
INSERT INTO Budgetmitglied (benutzer_id, budget_id, berechtigung)
VALUES (3, 1, 'SCHREIBEN');

-- Beispiel-Kategorien
INSERT INTO Kategorie (budget_id, name, topf) VALUES (1, 'Miete & Nebenkosten', 'NEEDS');
INSERT INTO Kategorie (budget_id, name, topf) VALUES (1, 'Lebensmittel', 'NEEDS');
INSERT INTO Kategorie (budget_id, name, topf) VALUES (1, 'Ausgang & Freizeit', 'WANTS');
INSERT INTO Kategorie (budget_id, name, topf) VALUES (1, 'Notgroschen', 'SPAREN');

-- Beispiel-Einkommen für 2026-11
INSERT INTO Einkommen (benutzer_id, budget_id, monat, brutto, steuer, netto)
VALUES (2, 1, '2026-11', 1200.00, 82.80, 1117.20);
INSERT INTO Einkommen (benutzer_id, budget_id, monat, brutto, steuer, netto)
VALUES (3, 1, '2026-11', 4200.00, 831.60, 3368.40);

-- Beispiel-Transaktionen für 2026-11
INSERT INTO Transaktion (budget_id, kategorie_id, erfasstVon_id, betrag, datum, beschreibung)
VALUES (1, 1, 3, 1600.00, '2026-11-01', 'Monatsmiete November');
INSERT INTO Transaktion (budget_id, kategorie_id, erfasstVon_id, betrag, datum, beschreibung)
VALUES (1, 2, 2, 145.50, '2026-11-04', 'Wocheneinkauf Migros');
INSERT INTO Transaktion (budget_id, kategorie_id, erfasstVon_id, betrag, datum, beschreibung)
VALUES (1, 3, 2, 48.00, '2026-11-06', 'Kino und Pizza');
INSERT INTO Transaktion (budget_id, kategorie_id, erfasstVon_id, betrag, datum, beschreibung)
VALUES (1, 4, 3, 500.00, '2026-11-02', 'Überweisung Sparkonto');
