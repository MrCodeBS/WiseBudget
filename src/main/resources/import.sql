-- Alle 26 Schweizer Kantone (Reihenfolge gemäss BfS Kantons-Id 1..26 aus ESTV-Tabelle 2026)
INSERT INTO Kanton (name, kuerzel) VALUES ('Zürich', 'ZH');
INSERT INTO Kanton (name, kuerzel) VALUES ('Bern', 'BE');
INSERT INTO Kanton (name, kuerzel) VALUES ('Luzern', 'LU');
INSERT INTO Kanton (name, kuerzel) VALUES ('Uri', 'UR');
INSERT INTO Kanton (name, kuerzel) VALUES ('Schwyz', 'SZ');
INSERT INTO Kanton (name, kuerzel) VALUES ('Obwalden', 'OW');
INSERT INTO Kanton (name, kuerzel) VALUES ('Nidwalden', 'NW');
INSERT INTO Kanton (name, kuerzel) VALUES ('Glarus', 'GL');
INSERT INTO Kanton (name, kuerzel) VALUES ('Zug', 'ZG');
INSERT INTO Kanton (name, kuerzel) VALUES ('Freiburg', 'FR');
INSERT INTO Kanton (name, kuerzel) VALUES ('Solothurn', 'SO');
INSERT INTO Kanton (name, kuerzel) VALUES ('Basel-Stadt', 'BS');
INSERT INTO Kanton (name, kuerzel) VALUES ('Basel-Landschaft', 'BL');
INSERT INTO Kanton (name, kuerzel) VALUES ('Schaffhausen', 'SH');
INSERT INTO Kanton (name, kuerzel) VALUES ('Appenzell Ausserrhoden', 'AR');
INSERT INTO Kanton (name, kuerzel) VALUES ('Appenzell Innerrhoden', 'AI');
INSERT INTO Kanton (name, kuerzel) VALUES ('St. Gallen', 'SG');
INSERT INTO Kanton (name, kuerzel) VALUES ('Graubünden', 'GR');
INSERT INTO Kanton (name, kuerzel) VALUES ('Aargau', 'AG');
INSERT INTO Kanton (name, kuerzel) VALUES ('Thurgau', 'TG');
INSERT INTO Kanton (name, kuerzel) VALUES ('Tessin', 'TI');
INSERT INTO Kanton (name, kuerzel) VALUES ('Waadt', 'VD');
INSERT INTO Kanton (name, kuerzel) VALUES ('Wallis', 'VS');
INSERT INTO Kanton (name, kuerzel) VALUES ('Neuenburg', 'NE');
INSERT INTO Kanton (name, kuerzel) VALUES ('Genf', 'GE');
INSERT INTO Kanton (name, kuerzel) VALUES ('Jura', 'JU');

-- Steuerregeln für 2026
-- Quelle Richtwerte & Steuerfüsse 2026: ESTV Steuerrechner (https://swisstaxcalculator.estv.admin.ch)
-- Durchschnittliche Steuerbelastung (Bund, Kanton, Gemeinde) in % des monatlichen Bruttoeinkommens
-- für Alleinstehende (ledig, ohne Kinder, konfessionslos) im jeweiligen Kantonshauptort.

-- 1. ZH - Zürich (Hauptort Zürich: Steuerfuss Kanton 95.0%, Gemeinde 119.0%)
INSERT INTO Steuerregel (kanton_id, erwerbsstatus, alterVon, alterBis, einkommenVon, einkommenBis, satzProzent, gueltigJahr) VALUES (1, 'LEHRLING', 14, 30, 0.00, 1500.00, 0.50, 2026);
INSERT INTO Steuerregel (kanton_id, erwerbsstatus, alterVon, alterBis, einkommenVon, einkommenBis, satzProzent, gueltigJahr) VALUES (1, 'LEHRLING', 14, 30, 1500.01, 5000.00, 3.10, 2026);
INSERT INTO Steuerregel (kanton_id, erwerbsstatus, alterVon, alterBis, einkommenVon, einkommenBis, satzProzent, gueltigJahr) VALUES (1, 'ANGESTELLT', 18, 65, 0.00, 4500.00, 7.73, 2026);
INSERT INTO Steuerregel (kanton_id, erwerbsstatus, alterVon, alterBis, einkommenVon, einkommenBis, satzProzent, gueltigJahr) VALUES (1, 'ANGESTELLT', 18, 65, 4500.01, 7500.00, 11.28, 2026);
INSERT INTO Steuerregel (kanton_id, erwerbsstatus, alterVon, alterBis, einkommenVon, einkommenBis, satzProzent, gueltigJahr) VALUES (1, 'ANGESTELLT', 18, 65, 7500.01, 25000.00, 14.36, 2026);
INSERT INTO Steuerregel (kanton_id, erwerbsstatus, alterVon, alterBis, einkommenVon, einkommenBis, satzProzent, gueltigJahr) VALUES (1, 'STUDENT', 18, 35, 0.00, 5000.00, 2.50, 2026);
INSERT INTO Steuerregel (kanton_id, erwerbsstatus, alterVon, alterBis, einkommenVon, einkommenBis, satzProzent, gueltigJahr) VALUES (1, 'ANDERE', 14, 99, 0.00, 15000.00, 7.73, 2026);

-- 2. BE - Bern (Hauptort Bern: Steuerfuss Kanton 297.5%, Gemeinde 154.0%)
INSERT INTO Steuerregel (kanton_id, erwerbsstatus, alterVon, alterBis, einkommenVon, einkommenBis, satzProzent, gueltigJahr) VALUES (2, 'LEHRLING', 14, 30, 0.00, 1500.00, 1.20, 2026);
INSERT INTO Steuerregel (kanton_id, erwerbsstatus, alterVon, alterBis, einkommenVon, einkommenBis, satzProzent, gueltigJahr) VALUES (2, 'LEHRLING', 14, 30, 1500.01, 5000.00, 5.40, 2026);
INSERT INTO Steuerregel (kanton_id, erwerbsstatus, alterVon, alterBis, einkommenVon, einkommenBis, satzProzent, gueltigJahr) VALUES (2, 'ANGESTELLT', 18, 65, 0.00, 4500.00, 13.40, 2026);
INSERT INTO Steuerregel (kanton_id, erwerbsstatus, alterVon, alterBis, einkommenVon, einkommenBis, satzProzent, gueltigJahr) VALUES (2, 'ANGESTELLT', 18, 65, 4500.01, 7500.00, 16.61, 2026);
INSERT INTO Steuerregel (kanton_id, erwerbsstatus, alterVon, alterBis, einkommenVon, einkommenBis, satzProzent, gueltigJahr) VALUES (2, 'ANGESTELLT', 18, 65, 7500.01, 25000.00, 19.30, 2026);
INSERT INTO Steuerregel (kanton_id, erwerbsstatus, alterVon, alterBis, einkommenVon, einkommenBis, satzProzent, gueltigJahr) VALUES (2, 'STUDENT', 18, 35, 0.00, 5000.00, 4.20, 2026);
INSERT INTO Steuerregel (kanton_id, erwerbsstatus, alterVon, alterBis, einkommenVon, einkommenBis, satzProzent, gueltigJahr) VALUES (2, 'ANDERE', 14, 99, 0.00, 15000.00, 13.40, 2026);

-- 3. LU - Luzern (Hauptort Luzern: Steuerfuss Kanton 145.0%, Gemeinde 145.0%)
INSERT INTO Steuerregel (kanton_id, erwerbsstatus, alterVon, alterBis, einkommenVon, einkommenBis, satzProzent, gueltigJahr) VALUES (3, 'LEHRLING', 14, 30, 0.00, 1500.00, 0.50, 2026);
INSERT INTO Steuerregel (kanton_id, erwerbsstatus, alterVon, alterBis, einkommenVon, einkommenBis, satzProzent, gueltigJahr) VALUES (3, 'LEHRLING', 14, 30, 1500.01, 5000.00, 3.20, 2026);
INSERT INTO Steuerregel (kanton_id, erwerbsstatus, alterVon, alterBis, einkommenVon, einkommenBis, satzProzent, gueltigJahr) VALUES (3, 'ANGESTELLT', 18, 65, 0.00, 4500.00, 8.40, 2026);
INSERT INTO Steuerregel (kanton_id, erwerbsstatus, alterVon, alterBis, einkommenVon, einkommenBis, satzProzent, gueltigJahr) VALUES (3, 'ANGESTELLT', 18, 65, 4500.01, 7500.00, 11.60, 2026);
INSERT INTO Steuerregel (kanton_id, erwerbsstatus, alterVon, alterBis, einkommenVon, einkommenBis, satzProzent, gueltigJahr) VALUES (3, 'ANGESTELLT', 18, 65, 7500.01, 25000.00, 14.10, 2026);
INSERT INTO Steuerregel (kanton_id, erwerbsstatus, alterVon, alterBis, einkommenVon, einkommenBis, satzProzent, gueltigJahr) VALUES (3, 'STUDENT', 18, 35, 0.00, 5000.00, 2.50, 2026);
INSERT INTO Steuerregel (kanton_id, erwerbsstatus, alterVon, alterBis, einkommenVon, einkommenBis, satzProzent, gueltigJahr) VALUES (3, 'ANDERE', 14, 99, 0.00, 15000.00, 8.40, 2026);

-- 4. UR - Uri (Hauptort Altdorf (UR): Steuerfuss Kanton 100.0%, Gemeinde 95.0%)
INSERT INTO Steuerregel (kanton_id, erwerbsstatus, alterVon, alterBis, einkommenVon, einkommenBis, satzProzent, gueltigJahr) VALUES (4, 'LEHRLING', 14, 30, 0.00, 1500.00, 0.30, 2026);
INSERT INTO Steuerregel (kanton_id, erwerbsstatus, alterVon, alterBis, einkommenVon, einkommenBis, satzProzent, gueltigJahr) VALUES (4, 'LEHRLING', 14, 30, 1500.01, 5000.00, 2.60, 2026);
INSERT INTO Steuerregel (kanton_id, erwerbsstatus, alterVon, alterBis, einkommenVon, einkommenBis, satzProzent, gueltigJahr) VALUES (4, 'ANGESTELLT', 18, 65, 0.00, 4500.00, 7.10, 2026);
INSERT INTO Steuerregel (kanton_id, erwerbsstatus, alterVon, alterBis, einkommenVon, einkommenBis, satzProzent, gueltigJahr) VALUES (4, 'ANGESTELLT', 18, 65, 4500.01, 7500.00, 9.50, 2026);
INSERT INTO Steuerregel (kanton_id, erwerbsstatus, alterVon, alterBis, einkommenVon, einkommenBis, satzProzent, gueltigJahr) VALUES (4, 'ANGESTELLT', 18, 65, 7500.01, 25000.00, 11.40, 2026);
INSERT INTO Steuerregel (kanton_id, erwerbsstatus, alterVon, alterBis, einkommenVon, einkommenBis, satzProzent, gueltigJahr) VALUES (4, 'STUDENT', 18, 35, 0.00, 5000.00, 2.00, 2026);
INSERT INTO Steuerregel (kanton_id, erwerbsstatus, alterVon, alterBis, einkommenVon, einkommenBis, satzProzent, gueltigJahr) VALUES (4, 'ANDERE', 14, 99, 0.00, 15000.00, 7.10, 2026);

-- 5. SZ - Schwyz (Hauptort Schwyz: Steuerfuss Kanton 110.0%, Gemeinde 175.0%)
INSERT INTO Steuerregel (kanton_id, erwerbsstatus, alterVon, alterBis, einkommenVon, einkommenBis, satzProzent, gueltigJahr) VALUES (5, 'LEHRLING', 14, 30, 0.00, 1500.00, 0.20, 2026);
INSERT INTO Steuerregel (kanton_id, erwerbsstatus, alterVon, alterBis, einkommenVon, einkommenBis, satzProzent, gueltigJahr) VALUES (5, 'LEHRLING', 14, 30, 1500.01, 5000.00, 2.10, 2026);
INSERT INTO Steuerregel (kanton_id, erwerbsstatus, alterVon, alterBis, einkommenVon, einkommenBis, satzProzent, gueltigJahr) VALUES (5, 'ANGESTELLT', 18, 65, 0.00, 4500.00, 5.80, 2026);
INSERT INTO Steuerregel (kanton_id, erwerbsstatus, alterVon, alterBis, einkommenVon, einkommenBis, satzProzent, gueltigJahr) VALUES (5, 'ANGESTELLT', 18, 65, 4500.01, 7500.00, 8.40, 2026);
INSERT INTO Steuerregel (kanton_id, erwerbsstatus, alterVon, alterBis, einkommenVon, einkommenBis, satzProzent, gueltigJahr) VALUES (5, 'ANGESTELLT', 18, 65, 7500.01, 25000.00, 10.60, 2026);
INSERT INTO Steuerregel (kanton_id, erwerbsstatus, alterVon, alterBis, einkommenVon, einkommenBis, satzProzent, gueltigJahr) VALUES (5, 'STUDENT', 18, 35, 0.00, 5000.00, 1.60, 2026);
INSERT INTO Steuerregel (kanton_id, erwerbsstatus, alterVon, alterBis, einkommenVon, einkommenBis, satzProzent, gueltigJahr) VALUES (5, 'ANDERE', 14, 99, 0.00, 15000.00, 5.80, 2026);

-- 6. OW - Obwalden (Hauptort Sarnen: Steuerfuss Kanton 325.0%, Gemeinde 386.0%)
INSERT INTO Steuerregel (kanton_id, erwerbsstatus, alterVon, alterBis, einkommenVon, einkommenBis, satzProzent, gueltigJahr) VALUES (6, 'LEHRLING', 14, 30, 0.00, 1500.00, 0.40, 2026);
INSERT INTO Steuerregel (kanton_id, erwerbsstatus, alterVon, alterBis, einkommenVon, einkommenBis, satzProzent, gueltigJahr) VALUES (6, 'LEHRLING', 14, 30, 1500.01, 5000.00, 2.80, 2026);
INSERT INTO Steuerregel (kanton_id, erwerbsstatus, alterVon, alterBis, einkommenVon, einkommenBis, satzProzent, gueltigJahr) VALUES (6, 'ANGESTELLT', 18, 65, 0.00, 4500.00, 7.60, 2026);
INSERT INTO Steuerregel (kanton_id, erwerbsstatus, alterVon, alterBis, einkommenVon, einkommenBis, satzProzent, gueltigJahr) VALUES (6, 'ANGESTELLT', 18, 65, 4500.01, 7500.00, 9.80, 2026);
INSERT INTO Steuerregel (kanton_id, erwerbsstatus, alterVon, alterBis, einkommenVon, einkommenBis, satzProzent, gueltigJahr) VALUES (6, 'ANGESTELLT', 18, 65, 7500.01, 25000.00, 11.50, 2026);
INSERT INTO Steuerregel (kanton_id, erwerbsstatus, alterVon, alterBis, einkommenVon, einkommenBis, satzProzent, gueltigJahr) VALUES (6, 'STUDENT', 18, 35, 0.00, 5000.00, 2.10, 2026);
INSERT INTO Steuerregel (kanton_id, erwerbsstatus, alterVon, alterBis, einkommenVon, einkommenBis, satzProzent, gueltigJahr) VALUES (6, 'ANDERE', 14, 99, 0.00, 15000.00, 7.60, 2026);

-- 7. NW - Nidwalden (Hauptort Stans: Steuerfuss Kanton 261.0%, Gemeinde 235.0%)
INSERT INTO Steuerregel (kanton_id, erwerbsstatus, alterVon, alterBis, einkommenVon, einkommenBis, satzProzent, gueltigJahr) VALUES (7, 'LEHRLING', 14, 30, 0.00, 1500.00, 0.30, 2026);
INSERT INTO Steuerregel (kanton_id, erwerbsstatus, alterVon, alterBis, einkommenVon, einkommenBis, satzProzent, gueltigJahr) VALUES (7, 'LEHRLING', 14, 30, 1500.01, 5000.00, 2.30, 2026);
INSERT INTO Steuerregel (kanton_id, erwerbsstatus, alterVon, alterBis, einkommenVon, einkommenBis, satzProzent, gueltigJahr) VALUES (7, 'ANGESTELLT', 18, 65, 0.00, 4500.00, 6.40, 2026);
INSERT INTO Steuerregel (kanton_id, erwerbsstatus, alterVon, alterBis, einkommenVon, einkommenBis, satzProzent, gueltigJahr) VALUES (7, 'ANGESTELLT', 18, 65, 4500.01, 7500.00, 8.90, 2026);
INSERT INTO Steuerregel (kanton_id, erwerbsstatus, alterVon, alterBis, einkommenVon, einkommenBis, satzProzent, gueltigJahr) VALUES (7, 'ANGESTELLT', 18, 65, 7500.01, 25000.00, 10.80, 2026);
INSERT INTO Steuerregel (kanton_id, erwerbsstatus, alterVon, alterBis, einkommenVon, einkommenBis, satzProzent, gueltigJahr) VALUES (7, 'STUDENT', 18, 35, 0.00, 5000.00, 1.80, 2026);
INSERT INTO Steuerregel (kanton_id, erwerbsstatus, alterVon, alterBis, einkommenVon, einkommenBis, satzProzent, gueltigJahr) VALUES (7, 'ANDERE', 14, 99, 0.00, 15000.00, 6.40, 2026);

-- 8. GL - Glarus (Hauptort Glarus: Steuerfuss Kanton 59.7%, Gemeinde 61.0%)
INSERT INTO Steuerregel (kanton_id, erwerbsstatus, alterVon, alterBis, einkommenVon, einkommenBis, satzProzent, gueltigJahr) VALUES (8, 'LEHRLING', 14, 30, 0.00, 1500.00, 0.60, 2026);
INSERT INTO Steuerregel (kanton_id, erwerbsstatus, alterVon, alterBis, einkommenVon, einkommenBis, satzProzent, gueltigJahr) VALUES (8, 'LEHRLING', 14, 30, 1500.01, 5000.00, 3.40, 2026);
INSERT INTO Steuerregel (kanton_id, erwerbsstatus, alterVon, alterBis, einkommenVon, einkommenBis, satzProzent, gueltigJahr) VALUES (8, 'ANGESTELLT', 18, 65, 0.00, 4500.00, 8.90, 2026);
INSERT INTO Steuerregel (kanton_id, erwerbsstatus, alterVon, alterBis, einkommenVon, einkommenBis, satzProzent, gueltigJahr) VALUES (8, 'ANGESTELLT', 18, 65, 4500.01, 7500.00, 11.90, 2026);
INSERT INTO Steuerregel (kanton_id, erwerbsstatus, alterVon, alterBis, einkommenVon, einkommenBis, satzProzent, gueltigJahr) VALUES (8, 'ANGESTELLT', 18, 65, 7500.01, 25000.00, 14.40, 2026);
INSERT INTO Steuerregel (kanton_id, erwerbsstatus, alterVon, alterBis, einkommenVon, einkommenBis, satzProzent, gueltigJahr) VALUES (8, 'STUDENT', 18, 35, 0.00, 5000.00, 2.70, 2026);
INSERT INTO Steuerregel (kanton_id, erwerbsstatus, alterVon, alterBis, einkommenVon, einkommenBis, satzProzent, gueltigJahr) VALUES (8, 'ANDERE', 14, 99, 0.00, 15000.00, 8.90, 2026);

-- 9. ZG - Zug (Hauptort Zug: Steuerfuss Kanton 78.0%, Gemeinde 52.0%)
INSERT INTO Steuerregel (kanton_id, erwerbsstatus, alterVon, alterBis, einkommenVon, einkommenBis, satzProzent, gueltigJahr) VALUES (9, 'LEHRLING', 14, 30, 0.00, 1500.00, 0.00, 2026);
INSERT INTO Steuerregel (kanton_id, erwerbsstatus, alterVon, alterBis, einkommenVon, einkommenBis, satzProzent, gueltigJahr) VALUES (9, 'LEHRLING', 14, 30, 1500.01, 5000.00, 1.20, 2026);
INSERT INTO Steuerregel (kanton_id, erwerbsstatus, alterVon, alterBis, einkommenVon, einkommenBis, satzProzent, gueltigJahr) VALUES (9, 'ANGESTELLT', 18, 65, 0.00, 4500.00, 3.23, 2026);
INSERT INTO Steuerregel (kanton_id, erwerbsstatus, alterVon, alterBis, einkommenVon, einkommenBis, satzProzent, gueltigJahr) VALUES (9, 'ANGESTELLT', 18, 65, 4500.01, 7500.00, 5.37, 2026);
INSERT INTO Steuerregel (kanton_id, erwerbsstatus, alterVon, alterBis, einkommenVon, einkommenBis, satzProzent, gueltigJahr) VALUES (9, 'ANGESTELLT', 18, 65, 7500.01, 25000.00, 7.52, 2026);
INSERT INTO Steuerregel (kanton_id, erwerbsstatus, alterVon, alterBis, einkommenVon, einkommenBis, satzProzent, gueltigJahr) VALUES (9, 'STUDENT', 18, 35, 0.00, 5000.00, 0.80, 2026);
INSERT INTO Steuerregel (kanton_id, erwerbsstatus, alterVon, alterBis, einkommenVon, einkommenBis, satzProzent, gueltigJahr) VALUES (9, 'ANDERE', 14, 99, 0.00, 15000.00, 3.23, 2026);

-- 10. FR - Freiburg (Hauptort Fribourg: Steuerfuss Kanton 96.0%, Gemeinde 80.0%)
INSERT INTO Steuerregel (kanton_id, erwerbsstatus, alterVon, alterBis, einkommenVon, einkommenBis, satzProzent, gueltigJahr) VALUES (10, 'LEHRLING', 14, 30, 0.00, 1500.00, 0.80, 2026);
INSERT INTO Steuerregel (kanton_id, erwerbsstatus, alterVon, alterBis, einkommenVon, einkommenBis, satzProzent, gueltigJahr) VALUES (10, 'LEHRLING', 14, 30, 1500.01, 5000.00, 4.10, 2026);
INSERT INTO Steuerregel (kanton_id, erwerbsstatus, alterVon, alterBis, einkommenVon, einkommenBis, satzProzent, gueltigJahr) VALUES (10, 'ANGESTELLT', 18, 65, 0.00, 4500.00, 10.40, 2026);
INSERT INTO Steuerregel (kanton_id, erwerbsstatus, alterVon, alterBis, einkommenVon, einkommenBis, satzProzent, gueltigJahr) VALUES (10, 'ANGESTELLT', 18, 65, 4500.01, 7500.00, 14.10, 2026);
INSERT INTO Steuerregel (kanton_id, erwerbsstatus, alterVon, alterBis, einkommenVon, einkommenBis, satzProzent, gueltigJahr) VALUES (10, 'ANGESTELLT', 18, 65, 7500.01, 25000.00, 17.20, 2026);
INSERT INTO Steuerregel (kanton_id, erwerbsstatus, alterVon, alterBis, einkommenVon, einkommenBis, satzProzent, gueltigJahr) VALUES (10, 'STUDENT', 18, 35, 0.00, 5000.00, 3.10, 2026);
INSERT INTO Steuerregel (kanton_id, erwerbsstatus, alterVon, alterBis, einkommenVon, einkommenBis, satzProzent, gueltigJahr) VALUES (10, 'ANDERE', 14, 99, 0.00, 15000.00, 10.40, 2026);

-- 11. SO - Solothurn (Hauptort Solothurn: Steuerfuss Kanton 104.0%, Gemeinde 112.0%)
INSERT INTO Steuerregel (kanton_id, erwerbsstatus, alterVon, alterBis, einkommenVon, einkommenBis, satzProzent, gueltigJahr) VALUES (11, 'LEHRLING', 14, 30, 0.00, 1500.00, 0.90, 2026);
INSERT INTO Steuerregel (kanton_id, erwerbsstatus, alterVon, alterBis, einkommenVon, einkommenBis, satzProzent, gueltigJahr) VALUES (11, 'LEHRLING', 14, 30, 1500.01, 5000.00, 4.30, 2026);
INSERT INTO Steuerregel (kanton_id, erwerbsstatus, alterVon, alterBis, einkommenVon, einkommenBis, satzProzent, gueltigJahr) VALUES (11, 'ANGESTELLT', 18, 65, 0.00, 4500.00, 11.10, 2026);
INSERT INTO Steuerregel (kanton_id, erwerbsstatus, alterVon, alterBis, einkommenVon, einkommenBis, satzProzent, gueltigJahr) VALUES (11, 'ANGESTELLT', 18, 65, 4500.01, 7500.00, 14.50, 2026);
INSERT INTO Steuerregel (kanton_id, erwerbsstatus, alterVon, alterBis, einkommenVon, einkommenBis, satzProzent, gueltigJahr) VALUES (11, 'ANGESTELLT', 18, 65, 7500.01, 25000.00, 17.30, 2026);
INSERT INTO Steuerregel (kanton_id, erwerbsstatus, alterVon, alterBis, einkommenVon, einkommenBis, satzProzent, gueltigJahr) VALUES (11, 'STUDENT', 18, 35, 0.00, 5000.00, 3.30, 2026);
INSERT INTO Steuerregel (kanton_id, erwerbsstatus, alterVon, alterBis, einkommenVon, einkommenBis, satzProzent, gueltigJahr) VALUES (11, 'ANDERE', 14, 99, 0.00, 15000.00, 11.10, 2026);

-- 12. BS - Basel-Stadt (Hauptort Basel: Steuerfuss Kanton 50.0%, Gemeinde 50.0%)
INSERT INTO Steuerregel (kanton_id, erwerbsstatus, alterVon, alterBis, einkommenVon, einkommenBis, satzProzent, gueltigJahr) VALUES (12, 'LEHRLING', 14, 30, 0.00, 1500.00, 0.80, 2026);
INSERT INTO Steuerregel (kanton_id, erwerbsstatus, alterVon, alterBis, einkommenVon, einkommenBis, satzProzent, gueltigJahr) VALUES (12, 'LEHRLING', 14, 30, 1500.01, 5000.00, 4.50, 2026);
INSERT INTO Steuerregel (kanton_id, erwerbsstatus, alterVon, alterBis, einkommenVon, einkommenBis, satzProzent, gueltigJahr) VALUES (12, 'ANGESTELLT', 18, 65, 0.00, 4500.00, 11.80, 2026);
INSERT INTO Steuerregel (kanton_id, erwerbsstatus, alterVon, alterBis, einkommenVon, einkommenBis, satzProzent, gueltigJahr) VALUES (12, 'ANGESTELLT', 18, 65, 4500.01, 7500.00, 15.60, 2026);
INSERT INTO Steuerregel (kanton_id, erwerbsstatus, alterVon, alterBis, einkommenVon, einkommenBis, satzProzent, gueltigJahr) VALUES (12, 'ANGESTELLT', 18, 65, 7500.01, 25000.00, 18.80, 2026);
INSERT INTO Steuerregel (kanton_id, erwerbsstatus, alterVon, alterBis, einkommenVon, einkommenBis, satzProzent, gueltigJahr) VALUES (12, 'STUDENT', 18, 35, 0.00, 5000.00, 3.50, 2026);
INSERT INTO Steuerregel (kanton_id, erwerbsstatus, alterVon, alterBis, einkommenVon, einkommenBis, satzProzent, gueltigJahr) VALUES (12, 'ANDERE', 14, 99, 0.00, 15000.00, 11.80, 2026);

-- 13. BL - Basel-Landschaft (Hauptort Liestal: Steuerfuss Kanton 100.0%, Gemeinde 65.0%)
INSERT INTO Steuerregel (kanton_id, erwerbsstatus, alterVon, alterBis, einkommenVon, einkommenBis, satzProzent, gueltigJahr) VALUES (13, 'LEHRLING', 14, 30, 0.00, 1500.00, 0.70, 2026);
INSERT INTO Steuerregel (kanton_id, erwerbsstatus, alterVon, alterBis, einkommenVon, einkommenBis, satzProzent, gueltigJahr) VALUES (13, 'LEHRLING', 14, 30, 1500.01, 5000.00, 3.90, 2026);
INSERT INTO Steuerregel (kanton_id, erwerbsstatus, alterVon, alterBis, einkommenVon, einkommenBis, satzProzent, gueltigJahr) VALUES (13, 'ANGESTELLT', 18, 65, 0.00, 4500.00, 10.20, 2026);
INSERT INTO Steuerregel (kanton_id, erwerbsstatus, alterVon, alterBis, einkommenVon, einkommenBis, satzProzent, gueltigJahr) VALUES (13, 'ANGESTELLT', 18, 65, 4500.01, 7500.00, 14.30, 2026);
INSERT INTO Steuerregel (kanton_id, erwerbsstatus, alterVon, alterBis, einkommenVon, einkommenBis, satzProzent, gueltigJahr) VALUES (13, 'ANGESTELLT', 18, 65, 7500.01, 25000.00, 17.60, 2026);
INSERT INTO Steuerregel (kanton_id, erwerbsstatus, alterVon, alterBis, einkommenVon, einkommenBis, satzProzent, gueltigJahr) VALUES (13, 'STUDENT', 18, 35, 0.00, 5000.00, 3.00, 2026);
INSERT INTO Steuerregel (kanton_id, erwerbsstatus, alterVon, alterBis, einkommenVon, einkommenBis, satzProzent, gueltigJahr) VALUES (13, 'ANDERE', 14, 99, 0.00, 15000.00, 10.20, 2026);

-- 14. SH - Schaffhausen (Hauptort Schaffhausen: Steuerfuss Kanton 76.0%, Gemeinde 83.0%)
INSERT INTO Steuerregel (kanton_id, erwerbsstatus, alterVon, alterBis, einkommenVon, einkommenBis, satzProzent, gueltigJahr) VALUES (14, 'LEHRLING', 14, 30, 0.00, 1500.00, 0.50, 2026);
INSERT INTO Steuerregel (kanton_id, erwerbsstatus, alterVon, alterBis, einkommenVon, einkommenBis, satzProzent, gueltigJahr) VALUES (14, 'LEHRLING', 14, 30, 1500.01, 5000.00, 3.10, 2026);
INSERT INTO Steuerregel (kanton_id, erwerbsstatus, alterVon, alterBis, einkommenVon, einkommenBis, satzProzent, gueltigJahr) VALUES (14, 'ANGESTELLT', 18, 65, 0.00, 4500.00, 8.10, 2026);
INSERT INTO Steuerregel (kanton_id, erwerbsstatus, alterVon, alterBis, einkommenVon, einkommenBis, satzProzent, gueltigJahr) VALUES (14, 'ANGESTELLT', 18, 65, 4500.01, 7500.00, 11.40, 2026);
INSERT INTO Steuerregel (kanton_id, erwerbsstatus, alterVon, alterBis, einkommenVon, einkommenBis, satzProzent, gueltigJahr) VALUES (14, 'ANGESTELLT', 18, 65, 7500.01, 25000.00, 14.20, 2026);
INSERT INTO Steuerregel (kanton_id, erwerbsstatus, alterVon, alterBis, einkommenVon, einkommenBis, satzProzent, gueltigJahr) VALUES (14, 'STUDENT', 18, 35, 0.00, 5000.00, 2.40, 2026);
INSERT INTO Steuerregel (kanton_id, erwerbsstatus, alterVon, alterBis, einkommenVon, einkommenBis, satzProzent, gueltigJahr) VALUES (14, 'ANDERE', 14, 99, 0.00, 15000.00, 8.10, 2026);

-- 15. AR - Appenzell Ausserrhoden (Hauptort Herisau: Steuerfuss Kanton 330.0%, Gemeinde 410.0%)
INSERT INTO Steuerregel (kanton_id, erwerbsstatus, alterVon, alterBis, einkommenVon, einkommenBis, satzProzent, gueltigJahr) VALUES (15, 'LEHRLING', 14, 30, 0.00, 1500.00, 0.80, 2026);
INSERT INTO Steuerregel (kanton_id, erwerbsstatus, alterVon, alterBis, einkommenVon, einkommenBis, satzProzent, gueltigJahr) VALUES (15, 'LEHRLING', 14, 30, 1500.01, 5000.00, 3.80, 2026);
INSERT INTO Steuerregel (kanton_id, erwerbsstatus, alterVon, alterBis, einkommenVon, einkommenBis, satzProzent, gueltigJahr) VALUES (15, 'ANGESTELLT', 18, 65, 0.00, 4500.00, 9.80, 2026);
INSERT INTO Steuerregel (kanton_id, erwerbsstatus, alterVon, alterBis, einkommenVon, einkommenBis, satzProzent, gueltigJahr) VALUES (15, 'ANGESTELLT', 18, 65, 4500.01, 7500.00, 13.00, 2026);
INSERT INTO Steuerregel (kanton_id, erwerbsstatus, alterVon, alterBis, einkommenVon, einkommenBis, satzProzent, gueltigJahr) VALUES (15, 'ANGESTELLT', 18, 65, 7500.01, 25000.00, 15.60, 2026);
INSERT INTO Steuerregel (kanton_id, erwerbsstatus, alterVon, alterBis, einkommenVon, einkommenBis, satzProzent, gueltigJahr) VALUES (15, 'STUDENT', 18, 35, 0.00, 5000.00, 2.90, 2026);
INSERT INTO Steuerregel (kanton_id, erwerbsstatus, alterVon, alterBis, einkommenVon, einkommenBis, satzProzent, gueltigJahr) VALUES (15, 'ANDERE', 14, 99, 0.00, 15000.00, 9.80, 2026);

-- 16. AI - Appenzell Innerrhoden (Hauptort Appenzell: Steuerfuss Kanton 96.0%, Gemeinde 56.0%)
INSERT INTO Steuerregel (kanton_id, erwerbsstatus, alterVon, alterBis, einkommenVon, einkommenBis, satzProzent, gueltigJahr) VALUES (16, 'LEHRLING', 14, 30, 0.00, 1500.00, 0.30, 2026);
INSERT INTO Steuerregel (kanton_id, erwerbsstatus, alterVon, alterBis, einkommenVon, einkommenBis, satzProzent, gueltigJahr) VALUES (16, 'LEHRLING', 14, 30, 1500.01, 5000.00, 2.40, 2026);
INSERT INTO Steuerregel (kanton_id, erwerbsstatus, alterVon, alterBis, einkommenVon, einkommenBis, satzProzent, gueltigJahr) VALUES (16, 'ANGESTELLT', 18, 65, 0.00, 4500.00, 6.70, 2026);
INSERT INTO Steuerregel (kanton_id, erwerbsstatus, alterVon, alterBis, einkommenVon, einkommenBis, satzProzent, gueltigJahr) VALUES (16, 'ANGESTELLT', 18, 65, 4500.01, 7500.00, 9.30, 2026);
INSERT INTO Steuerregel (kanton_id, erwerbsstatus, alterVon, alterBis, einkommenVon, einkommenBis, satzProzent, gueltigJahr) VALUES (16, 'ANGESTELLT', 18, 65, 7500.01, 25000.00, 11.60, 2026);
INSERT INTO Steuerregel (kanton_id, erwerbsstatus, alterVon, alterBis, einkommenVon, einkommenBis, satzProzent, gueltigJahr) VALUES (16, 'STUDENT', 18, 35, 0.00, 5000.00, 1.90, 2026);
INSERT INTO Steuerregel (kanton_id, erwerbsstatus, alterVon, alterBis, einkommenVon, einkommenBis, satzProzent, gueltigJahr) VALUES (16, 'ANDERE', 14, 99, 0.00, 15000.00, 6.70, 2026);

-- 17. SG - St. Gallen (Hauptort St. Gallen: Steuerfuss Kanton 105.0%, Gemeinde 138.0%)
INSERT INTO Steuerregel (kanton_id, erwerbsstatus, alterVon, alterBis, einkommenVon, einkommenBis, satzProzent, gueltigJahr) VALUES (17, 'LEHRLING', 14, 30, 0.00, 1500.00, 0.70, 2026);
INSERT INTO Steuerregel (kanton_id, erwerbsstatus, alterVon, alterBis, einkommenVon, einkommenBis, satzProzent, gueltigJahr) VALUES (17, 'LEHRLING', 14, 30, 1500.01, 5000.00, 3.80, 2026);
INSERT INTO Steuerregel (kanton_id, erwerbsstatus, alterVon, alterBis, einkommenVon, einkommenBis, satzProzent, gueltigJahr) VALUES (17, 'ANGESTELLT', 18, 65, 0.00, 4500.00, 9.90, 2026);
INSERT INTO Steuerregel (kanton_id, erwerbsstatus, alterVon, alterBis, einkommenVon, einkommenBis, satzProzent, gueltigJahr) VALUES (17, 'ANGESTELLT', 18, 65, 4500.01, 7500.00, 13.60, 2026);
INSERT INTO Steuerregel (kanton_id, erwerbsstatus, alterVon, alterBis, einkommenVon, einkommenBis, satzProzent, gueltigJahr) VALUES (17, 'ANGESTELLT', 18, 65, 7500.01, 25000.00, 16.50, 2026);
INSERT INTO Steuerregel (kanton_id, erwerbsstatus, alterVon, alterBis, einkommenVon, einkommenBis, satzProzent, gueltigJahr) VALUES (17, 'STUDENT', 18, 35, 0.00, 5000.00, 2.90, 2026);
INSERT INTO Steuerregel (kanton_id, erwerbsstatus, alterVon, alterBis, einkommenVon, einkommenBis, satzProzent, gueltigJahr) VALUES (17, 'ANDERE', 14, 99, 0.00, 15000.00, 9.90, 2026);

-- 18. GR - Graubünden (Hauptort Chur: Steuerfuss Kanton 92.0%, Gemeinde 88.0%)
INSERT INTO Steuerregel (kanton_id, erwerbsstatus, alterVon, alterBis, einkommenVon, einkommenBis, satzProzent, gueltigJahr) VALUES (18, 'LEHRLING', 14, 30, 0.00, 1500.00, 0.50, 2026);
INSERT INTO Steuerregel (kanton_id, erwerbsstatus, alterVon, alterBis, einkommenVon, einkommenBis, satzProzent, gueltigJahr) VALUES (18, 'LEHRLING', 14, 30, 1500.01, 5000.00, 3.20, 2026);
INSERT INTO Steuerregel (kanton_id, erwerbsstatus, alterVon, alterBis, einkommenVon, einkommenBis, satzProzent, gueltigJahr) VALUES (18, 'ANGESTELLT', 18, 65, 0.00, 4500.00, 8.30, 2026);
INSERT INTO Steuerregel (kanton_id, erwerbsstatus, alterVon, alterBis, einkommenVon, einkommenBis, satzProzent, gueltigJahr) VALUES (18, 'ANGESTELLT', 18, 65, 4500.01, 7500.00, 11.70, 2026);
INSERT INTO Steuerregel (kanton_id, erwerbsstatus, alterVon, alterBis, einkommenVon, einkommenBis, satzProzent, gueltigJahr) VALUES (18, 'ANGESTELLT', 18, 65, 7500.01, 25000.00, 14.50, 2026);
INSERT INTO Steuerregel (kanton_id, erwerbsstatus, alterVon, alterBis, einkommenVon, einkommenBis, satzProzent, gueltigJahr) VALUES (18, 'STUDENT', 18, 35, 0.00, 5000.00, 2.50, 2026);
INSERT INTO Steuerregel (kanton_id, erwerbsstatus, alterVon, alterBis, einkommenVon, einkommenBis, satzProzent, gueltigJahr) VALUES (18, 'ANDERE', 14, 99, 0.00, 15000.00, 8.30, 2026);

-- 19. AG - Aargau (Hauptort Aarau: Steuerfuss Kanton 103.0%, Gemeinde 96.0%)
INSERT INTO Steuerregel (kanton_id, erwerbsstatus, alterVon, alterBis, einkommenVon, einkommenBis, satzProzent, gueltigJahr) VALUES (19, 'LEHRLING', 14, 30, 0.00, 1500.00, 0.60, 2026);
INSERT INTO Steuerregel (kanton_id, erwerbsstatus, alterVon, alterBis, einkommenVon, einkommenBis, satzProzent, gueltigJahr) VALUES (19, 'LEHRLING', 14, 30, 1500.01, 5000.00, 3.40, 2026);
INSERT INTO Steuerregel (kanton_id, erwerbsstatus, alterVon, alterBis, einkommenVon, einkommenBis, satzProzent, gueltigJahr) VALUES (19, 'ANGESTELLT', 18, 65, 0.00, 4500.00, 8.60, 2026);
INSERT INTO Steuerregel (kanton_id, erwerbsstatus, alterVon, alterBis, einkommenVon, einkommenBis, satzProzent, gueltigJahr) VALUES (19, 'ANGESTELLT', 18, 65, 4500.01, 7500.00, 12.20, 2026);
INSERT INTO Steuerregel (kanton_id, erwerbsstatus, alterVon, alterBis, einkommenVon, einkommenBis, satzProzent, gueltigJahr) VALUES (19, 'ANGESTELLT', 18, 65, 7500.01, 25000.00, 15.10, 2026);
INSERT INTO Steuerregel (kanton_id, erwerbsstatus, alterVon, alterBis, einkommenVon, einkommenBis, satzProzent, gueltigJahr) VALUES (19, 'STUDENT', 18, 35, 0.00, 5000.00, 2.60, 2026);
INSERT INTO Steuerregel (kanton_id, erwerbsstatus, alterVon, alterBis, einkommenVon, einkommenBis, satzProzent, gueltigJahr) VALUES (19, 'ANDERE', 14, 99, 0.00, 15000.00, 8.60, 2026);

-- 20. TG - Thurgau (Hauptort Frauenfeld: Steuerfuss Kanton 109.0%, Gemeinde 142.0%)
INSERT INTO Steuerregel (kanton_id, erwerbsstatus, alterVon, alterBis, einkommenVon, einkommenBis, satzProzent, gueltigJahr) VALUES (20, 'LEHRLING', 14, 30, 0.00, 1500.00, 0.60, 2026);
INSERT INTO Steuerregel (kanton_id, erwerbsstatus, alterVon, alterBis, einkommenVon, einkommenBis, satzProzent, gueltigJahr) VALUES (20, 'LEHRLING', 14, 30, 1500.01, 5000.00, 3.50, 2026);
INSERT INTO Steuerregel (kanton_id, erwerbsstatus, alterVon, alterBis, einkommenVon, einkommenBis, satzProzent, gueltigJahr) VALUES (20, 'ANGESTELLT', 18, 65, 0.00, 4500.00, 9.10, 2026);
INSERT INTO Steuerregel (kanton_id, erwerbsstatus, alterVon, alterBis, einkommenVon, einkommenBis, satzProzent, gueltigJahr) VALUES (20, 'ANGESTELLT', 18, 65, 4500.01, 7500.00, 12.60, 2026);
INSERT INTO Steuerregel (kanton_id, erwerbsstatus, alterVon, alterBis, einkommenVon, einkommenBis, satzProzent, gueltigJahr) VALUES (20, 'ANGESTELLT', 18, 65, 7500.01, 25000.00, 15.40, 2026);
INSERT INTO Steuerregel (kanton_id, erwerbsstatus, alterVon, alterBis, einkommenVon, einkommenBis, satzProzent, gueltigJahr) VALUES (20, 'STUDENT', 18, 35, 0.00, 5000.00, 2.70, 2026);
INSERT INTO Steuerregel (kanton_id, erwerbsstatus, alterVon, alterBis, einkommenVon, einkommenBis, satzProzent, gueltigJahr) VALUES (20, 'ANDERE', 14, 99, 0.00, 15000.00, 9.10, 2026);

-- 21. TI - Tessin (Hauptort Bellinzona: Steuerfuss Kanton 100.0%, Gemeinde 93.0%)
INSERT INTO Steuerregel (kanton_id, erwerbsstatus, alterVon, alterBis, einkommenVon, einkommenBis, satzProzent, gueltigJahr) VALUES (21, 'LEHRLING', 14, 30, 0.00, 1500.00, 0.50, 2026);
INSERT INTO Steuerregel (kanton_id, erwerbsstatus, alterVon, alterBis, einkommenVon, einkommenBis, satzProzent, gueltigJahr) VALUES (21, 'LEHRLING', 14, 30, 1500.01, 5000.00, 3.30, 2026);
INSERT INTO Steuerregel (kanton_id, erwerbsstatus, alterVon, alterBis, einkommenVon, einkommenBis, satzProzent, gueltigJahr) VALUES (21, 'ANGESTELLT', 18, 65, 0.00, 4500.00, 8.70, 2026);
INSERT INTO Steuerregel (kanton_id, erwerbsstatus, alterVon, alterBis, einkommenVon, einkommenBis, satzProzent, gueltigJahr) VALUES (21, 'ANGESTELLT', 18, 65, 4500.01, 7500.00, 12.80, 2026);
INSERT INTO Steuerregel (kanton_id, erwerbsstatus, alterVon, alterBis, einkommenVon, einkommenBis, satzProzent, gueltigJahr) VALUES (21, 'ANGESTELLT', 18, 65, 7500.01, 25000.00, 16.20, 2026);
INSERT INTO Steuerregel (kanton_id, erwerbsstatus, alterVon, alterBis, einkommenVon, einkommenBis, satzProzent, gueltigJahr) VALUES (21, 'STUDENT', 18, 35, 0.00, 5000.00, 2.50, 2026);
INSERT INTO Steuerregel (kanton_id, erwerbsstatus, alterVon, alterBis, einkommenVon, einkommenBis, satzProzent, gueltigJahr) VALUES (21, 'ANDERE', 14, 99, 0.00, 15000.00, 8.70, 2026);

-- 22. VD - Waadt (Hauptort Lausanne: Steuerfuss Kanton 155.0%, Gemeinde 78.5%)
INSERT INTO Steuerregel (kanton_id, erwerbsstatus, alterVon, alterBis, einkommenVon, einkommenBis, satzProzent, gueltigJahr) VALUES (22, 'LEHRLING', 14, 30, 0.00, 1500.00, 1.00, 2026);
INSERT INTO Steuerregel (kanton_id, erwerbsstatus, alterVon, alterBis, einkommenVon, einkommenBis, satzProzent, gueltigJahr) VALUES (22, 'LEHRLING', 14, 30, 1500.01, 5000.00, 4.80, 2026);
INSERT INTO Steuerregel (kanton_id, erwerbsstatus, alterVon, alterBis, einkommenVon, einkommenBis, satzProzent, gueltigJahr) VALUES (22, 'ANGESTELLT', 18, 65, 0.00, 4500.00, 12.60, 2026);
INSERT INTO Steuerregel (kanton_id, erwerbsstatus, alterVon, alterBis, einkommenVon, einkommenBis, satzProzent, gueltigJahr) VALUES (22, 'ANGESTELLT', 18, 65, 4500.01, 7500.00, 16.40, 2026);
INSERT INTO Steuerregel (kanton_id, erwerbsstatus, alterVon, alterBis, einkommenVon, einkommenBis, satzProzent, gueltigJahr) VALUES (22, 'ANGESTELLT', 18, 65, 7500.01, 25000.00, 19.60, 2026);
INSERT INTO Steuerregel (kanton_id, erwerbsstatus, alterVon, alterBis, einkommenVon, einkommenBis, satzProzent, gueltigJahr) VALUES (22, 'STUDENT', 18, 35, 0.00, 5000.00, 3.80, 2026);
INSERT INTO Steuerregel (kanton_id, erwerbsstatus, alterVon, alterBis, einkommenVon, einkommenBis, satzProzent, gueltigJahr) VALUES (22, 'ANDERE', 14, 99, 0.00, 15000.00, 12.60, 2026);

-- 23. VS - Wallis (Hauptort Sion: Steuerfuss Kanton 100.0%, Gemeinde 110.0%)
INSERT INTO Steuerregel (kanton_id, erwerbsstatus, alterVon, alterBis, einkommenVon, einkommenBis, satzProzent, gueltigJahr) VALUES (23, 'LEHRLING', 14, 30, 0.00, 1500.00, 0.60, 2026);
INSERT INTO Steuerregel (kanton_id, erwerbsstatus, alterVon, alterBis, einkommenVon, einkommenBis, satzProzent, gueltigJahr) VALUES (23, 'LEHRLING', 14, 30, 1500.01, 5000.00, 3.60, 2026);
INSERT INTO Steuerregel (kanton_id, erwerbsstatus, alterVon, alterBis, einkommenVon, einkommenBis, satzProzent, gueltigJahr) VALUES (23, 'ANGESTELLT', 18, 65, 0.00, 4500.00, 9.40, 2026);
INSERT INTO Steuerregel (kanton_id, erwerbsstatus, alterVon, alterBis, einkommenVon, einkommenBis, satzProzent, gueltigJahr) VALUES (23, 'ANGESTELLT', 18, 65, 4500.01, 7500.00, 13.40, 2026);
INSERT INTO Steuerregel (kanton_id, erwerbsstatus, alterVon, alterBis, einkommenVon, einkommenBis, satzProzent, gueltigJahr) VALUES (23, 'ANGESTELLT', 18, 65, 7500.01, 25000.00, 16.80, 2026);
INSERT INTO Steuerregel (kanton_id, erwerbsstatus, alterVon, alterBis, einkommenVon, einkommenBis, satzProzent, gueltigJahr) VALUES (23, 'STUDENT', 18, 35, 0.00, 5000.00, 2.80, 2026);
INSERT INTO Steuerregel (kanton_id, erwerbsstatus, alterVon, alterBis, einkommenVon, einkommenBis, satzProzent, gueltigJahr) VALUES (23, 'ANDERE', 14, 99, 0.00, 15000.00, 9.40, 2026);

-- 24. NE - Neuenburg (Hauptort Neuchâtel: Steuerfuss Kanton 124.0%, Gemeinde 65.0%)
INSERT INTO Steuerregel (kanton_id, erwerbsstatus, alterVon, alterBis, einkommenVon, einkommenBis, satzProzent, gueltigJahr) VALUES (24, 'LEHRLING', 14, 30, 0.00, 1500.00, 1.10, 2026);
INSERT INTO Steuerregel (kanton_id, erwerbsstatus, alterVon, alterBis, einkommenVon, einkommenBis, satzProzent, gueltigJahr) VALUES (24, 'LEHRLING', 14, 30, 1500.01, 5000.00, 5.10, 2026);
INSERT INTO Steuerregel (kanton_id, erwerbsstatus, alterVon, alterBis, einkommenVon, einkommenBis, satzProzent, gueltigJahr) VALUES (24, 'ANGESTELLT', 18, 65, 0.00, 4500.00, 13.10, 2026);
INSERT INTO Steuerregel (kanton_id, erwerbsstatus, alterVon, alterBis, einkommenVon, einkommenBis, satzProzent, gueltigJahr) VALUES (24, 'ANGESTELLT', 18, 65, 4500.01, 7500.00, 16.90, 2026);
INSERT INTO Steuerregel (kanton_id, erwerbsstatus, alterVon, alterBis, einkommenVon, einkommenBis, satzProzent, gueltigJahr) VALUES (24, 'ANGESTELLT', 18, 65, 7500.01, 25000.00, 20.10, 2026);
INSERT INTO Steuerregel (kanton_id, erwerbsstatus, alterVon, alterBis, einkommenVon, einkommenBis, satzProzent, gueltigJahr) VALUES (24, 'STUDENT', 18, 35, 0.00, 5000.00, 4.00, 2026);
INSERT INTO Steuerregel (kanton_id, erwerbsstatus, alterVon, alterBis, einkommenVon, einkommenBis, satzProzent, gueltigJahr) VALUES (24, 'ANDERE', 14, 99, 0.00, 15000.00, 13.10, 2026);

-- 25. GE - Genf (Hauptort Genève: Steuerfuss Kanton 147.5%, Gemeinde 45.49%)
INSERT INTO Steuerregel (kanton_id, erwerbsstatus, alterVon, alterBis, einkommenVon, einkommenBis, satzProzent, gueltigJahr) VALUES (25, 'LEHRLING', 14, 30, 0.00, 1500.00, 0.90, 2026);
INSERT INTO Steuerregel (kanton_id, erwerbsstatus, alterVon, alterBis, einkommenVon, einkommenBis, satzProzent, gueltigJahr) VALUES (25, 'LEHRLING', 14, 30, 1500.01, 5000.00, 4.60, 2026);
INSERT INTO Steuerregel (kanton_id, erwerbsstatus, alterVon, alterBis, einkommenVon, einkommenBis, satzProzent, gueltigJahr) VALUES (25, 'ANGESTELLT', 18, 65, 0.00, 4500.00, 12.10, 2026);
INSERT INTO Steuerregel (kanton_id, erwerbsstatus, alterVon, alterBis, einkommenVon, einkommenBis, satzProzent, gueltigJahr) VALUES (25, 'ANGESTELLT', 18, 65, 4500.01, 7500.00, 16.50, 2026);
INSERT INTO Steuerregel (kanton_id, erwerbsstatus, alterVon, alterBis, einkommenVon, einkommenBis, satzProzent, gueltigJahr) VALUES (25, 'ANGESTELLT', 18, 65, 7500.01, 25000.00, 20.20, 2026);
INSERT INTO Steuerregel (kanton_id, erwerbsstatus, alterVon, alterBis, einkommenVon, einkommenBis, satzProzent, gueltigJahr) VALUES (25, 'STUDENT', 18, 35, 0.00, 5000.00, 3.60, 2026);
INSERT INTO Steuerregel (kanton_id, erwerbsstatus, alterVon, alterBis, einkommenVon, einkommenBis, satzProzent, gueltigJahr) VALUES (25, 'ANDERE', 14, 99, 0.00, 15000.00, 12.10, 2026);

-- 26. JU - Jura (Hauptort Delémont: Steuerfuss Kanton 285.0%, Gemeinde 190.0%)
INSERT INTO Steuerregel (kanton_id, erwerbsstatus, alterVon, alterBis, einkommenVon, einkommenBis, satzProzent, gueltigJahr) VALUES (26, 'LEHRLING', 14, 30, 0.00, 1500.00, 1.10, 2026);
INSERT INTO Steuerregel (kanton_id, erwerbsstatus, alterVon, alterBis, einkommenVon, einkommenBis, satzProzent, gueltigJahr) VALUES (26, 'LEHRLING', 14, 30, 1500.01, 5000.00, 5.20, 2026);
INSERT INTO Steuerregel (kanton_id, erwerbsstatus, alterVon, alterBis, einkommenVon, einkommenBis, satzProzent, gueltigJahr) VALUES (26, 'ANGESTELLT', 18, 65, 0.00, 4500.00, 13.00, 2026);
INSERT INTO Steuerregel (kanton_id, erwerbsstatus, alterVon, alterBis, einkommenVon, einkommenBis, satzProzent, gueltigJahr) VALUES (26, 'ANGESTELLT', 18, 65, 4500.01, 7500.00, 16.70, 2026);
INSERT INTO Steuerregel (kanton_id, erwerbsstatus, alterVon, alterBis, einkommenVon, einkommenBis, satzProzent, gueltigJahr) VALUES (26, 'ANGESTELLT', 18, 65, 7500.01, 25000.00, 19.70, 2026);
INSERT INTO Steuerregel (kanton_id, erwerbsstatus, alterVon, alterBis, einkommenVon, einkommenBis, satzProzent, gueltigJahr) VALUES (26, 'STUDENT', 18, 35, 0.00, 5000.00, 4.10, 2026);
INSERT INTO Steuerregel (kanton_id, erwerbsstatus, alterVon, alterBis, einkommenVon, einkommenBis, satzProzent, gueltigJahr) VALUES (26, 'ANDERE', 14, 99, 0.00, 15000.00, 13.00, 2026);

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

-- Beispiel-Transaktionen für 2026-11 (Budget 1: WG Budget Zürich)
INSERT INTO Transaktion (budget_id, kategorie_id, erfasstVon_id, betrag, datum, beschreibung)
VALUES (1, 1, 3, 1600.00, '2026-11-01', 'Monatsmiete November');
INSERT INTO Transaktion (budget_id, kategorie_id, erfasstVon_id, betrag, datum, beschreibung)
VALUES (1, 2, 2, 145.50, '2026-11-04', 'Wocheneinkauf Migros');
INSERT INTO Transaktion (budget_id, kategorie_id, erfasstVon_id, betrag, datum, beschreibung)
VALUES (1, 3, 2, 48.00, '2026-11-06', 'Kino und Pizza');
INSERT INTO Transaktion (budget_id, kategorie_id, erfasstVon_id, betrag, datum, beschreibung)
VALUES (1, 4, 3, 500.00, '2026-11-02', 'Überweisung Sparkonto');

-- 2. Beispielbudget: Lehrlingsbudget Lukas (gemäss Budgetberatung-Vorlage, Nettolohn 1018.00 CHF)
INSERT INTO Budget (name, typ, needsProzent, wantsProzent, sparenProzent, version)
VALUES ('Lehrlingsbudget Lukas', 'PRIVAT', 50.00, 30.00, 20.00, 0);

INSERT INTO Budgetmitglied (benutzer_id, budget_id, berechtigung)
VALUES (2, 2, 'BESITZER');

-- Kategorien für Lehrlingsbudget (budget_id = 2, kategorie_id = 5..19)
-- Fixkosten & Grundbedarf (NEEDS)
INSERT INTO Kategorie (budget_id, name, topf) VALUES (2, 'Krankenkasse (Grundversicherung)', 'NEEDS');
INSERT INTO Kategorie (budget_id, name, topf) VALUES (2, 'Fahrkosten', 'NEEDS');
INSERT INTO Kategorie (budget_id, name, topf) VALUES (2, 'Anteil Kost und Logis (Eltern)', 'NEEDS');
INSERT INTO Kategorie (budget_id, name, topf) VALUES (2, 'Handy', 'NEEDS');
INSERT INTO Kategorie (budget_id, name, topf) VALUES (2, 'Schulmaterial', 'NEEDS');
INSERT INTO Kategorie (budget_id, name, topf) VALUES (2, 'Auswärtige Verpflegung', 'NEEDS');
-- Persönliche Auslagen (WANTS)
INSERT INTO Kategorie (budget_id, name, topf) VALUES (2, 'Taschengeld/Diverses', 'WANTS');
INSERT INTO Kategorie (budget_id, name, topf) VALUES (2, 'Kleider/Schuhe', 'WANTS');
INSERT INTO Kategorie (budget_id, name, topf) VALUES (2, 'Coiffeur/Körperpflege', 'WANTS');
INSERT INTO Kategorie (budget_id, name, topf) VALUES (2, 'PC (Unterhalt)', 'WANTS');
INSERT INTO Kategorie (budget_id, name, topf) VALUES (2, 'Sport/Musik', 'WANTS');
-- Rückstellungen (SPAREN)
INSERT INTO Kategorie (budget_id, name, topf) VALUES (2, 'Jahresfranchise/Selbstbehalt (Krankenkasse)', 'SPAREN');
INSERT INTO Kategorie (budget_id, name, topf) VALUES (2, 'Zahnarzt/Optiker/Medikamente', 'SPAREN');
INSERT INTO Kategorie (budget_id, name, topf) VALUES (2, 'Steuern', 'SPAREN');
INSERT INTO Kategorie (budget_id, name, topf) VALUES (2, 'Ferien/Sparen', 'SPAREN');

-- Einkommen für Lehrlingsbudget Lukas (2026-11: Brutto 1093.45 CHF -> 6.90% Abzug -> Netto 1018.00 CHF)
INSERT INTO Einkommen (benutzer_id, budget_id, monat, brutto, steuer, netto)
VALUES (2, 2, '2026-11', 1093.45, 75.45, 1018.00);

-- Transaktionen für Lehrlingsbudget Lukas (2026-11 gemäss PDF-Tabelle)
INSERT INTO Transaktion (budget_id, kategorie_id, erfasstVon_id, betrag, datum, beschreibung)
VALUES (2, 5, 2, 80.00, '2026-11-01', 'Krankenkasse Grundversicherung');
INSERT INTO Transaktion (budget_id, kategorie_id, erfasstVon_id, betrag, datum, beschreibung)
VALUES (2, 6, 2, 80.00, '2026-11-01', 'ZVV Monatsabo');
INSERT INTO Transaktion (budget_id, kategorie_id, erfasstVon_id, betrag, datum, beschreibung)
VALUES (2, 7, 2, 120.00, '2026-11-01', 'Kost und Logis Eltern');
INSERT INTO Transaktion (budget_id, kategorie_id, erfasstVon_id, betrag, datum, beschreibung)
VALUES (2, 8, 2, 30.00, '2026-11-03', 'Handy-Abo');
INSERT INTO Transaktion (budget_id, kategorie_id, erfasstVon_id, betrag, datum, beschreibung)
VALUES (2, 9, 2, 10.00, '2026-11-05', 'Schulmaterial Berufsschule');
INSERT INTO Transaktion (budget_id, kategorie_id, erfasstVon_id, betrag, datum, beschreibung)
VALUES (2, 10, 2, 120.00, '2026-11-10', 'Mittagessen auswärts');
INSERT INTO Transaktion (budget_id, kategorie_id, erfasstVon_id, betrag, datum, beschreibung)
VALUES (2, 11, 2, 150.00, '2026-11-12', 'Taschengeld & Ausgang');
INSERT INTO Transaktion (budget_id, kategorie_id, erfasstVon_id, betrag, datum, beschreibung)
VALUES (2, 12, 2, 90.00, '2026-11-14', 'Winterjacke und Schuhe');
INSERT INTO Transaktion (budget_id, kategorie_id, erfasstVon_id, betrag, datum, beschreibung)
VALUES (2, 13, 2, 50.00, '2026-11-15', 'Coiffeur & Pflegeprodukte');
INSERT INTO Transaktion (budget_id, kategorie_id, erfasstVon_id, betrag, datum, beschreibung)
VALUES (2, 14, 2, 30.00, '2026-11-16', 'PC-Zubehör & Cloud');
INSERT INTO Transaktion (budget_id, kategorie_id, erfasstVon_id, betrag, datum, beschreibung)
VALUES (2, 15, 2, 50.00, '2026-11-18', 'Fitness-Abo & Musik');
INSERT INTO Transaktion (budget_id, kategorie_id, erfasstVon_id, betrag, datum, beschreibung)
VALUES (2, 16, 2, 20.00, '2026-11-25', 'Rückstellung Franchise Krankenkasse');
INSERT INTO Transaktion (budget_id, kategorie_id, erfasstVon_id, betrag, datum, beschreibung)
VALUES (2, 17, 2, 20.00, '2026-11-25', 'Rückstellung Zahnarzt/Optiker');
INSERT INTO Transaktion (budget_id, kategorie_id, erfasstVon_id, betrag, datum, beschreibung)
VALUES (2, 18, 2, 10.00, '2026-11-25', 'Rückstellung Steuern');
INSERT INTO Transaktion (budget_id, kategorie_id, erfasstVon_id, betrag, datum, beschreibung)
VALUES (2, 19, 2, 150.00, '2026-11-25', 'Überweisung Ferien- und Sparkonto');
