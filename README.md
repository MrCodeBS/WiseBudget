# WiseBudget (Modul 223 – Multi-User-Applikationen)

WiseBudget ist eine Multi-User-Budgetapplikation für die Schweiz nach der **50/30/20-Regel** (`NEEDS` / `WANTS` / `SPAREN`). Benutzer erfassen ihr monatliches Bruttoeinkommen, worauf die Applikation anhand von Kanton, Erwerbsstatus, Alter und Einkommensstufe die Steuern (Richtwerte gemäss ESTV-Steuerrechner) sowie den AHV/IV/EO + ALV-Abzug (6.4 % ab dem 1. Januar nach dem 17. Geburtstag) schätzt und das Nettoeinkommen auf die drei Töpfe aufteilt.

## Applikation starten

Voraussetzung: **Java 21**.

Unter Linux / macOS / Git Bash:
```bash
./mvnw quarkus:dev
```

Unter Windows (PowerShell / CMD):
```powershell
.\mvnw.cmd quarkus:dev
```

Die Applikation ist anschliessend unter **http://localhost:8080** erreichbar.
Im Dev- und Test-Profil wird eine In-Memory-H2-Datenbank (`drop-and-create`) verwendet und automatisch mit den Daten aus `src/main/resources/import.sql` befüllt.

Tests ausführen:
```bash
./mvnw test
```

## Standard-Testbenutzer

Alle vordefinierten Testbenutzer haben das Passwort **`passwort123`**:

| Name | E-Mail | Rolle | Erwerbsstatus | Kanton | Geburtsdatum | Gemeinsames Budget («WG Budget Zürich») |
|---|---|---|---|---|---|---|
| **Admin** | `admin@wisebudget.ch` | `ADMIN` | `ANGESTELLT` | `ZH` | `1985-04-12` | Kein Mitglied |
| **Lukas Meier** | `lukas@wisebudget.ch` | `USER` | `LEHRLING` | `ZH` | `2008-06-15` | `BESITZER` |
| **Sarah Keller** | `sarah@wisebudget.ch` | `USER` | `ANGESTELLT` | `BE` | `2001-09-20` | `SCHREIBEN` |

## Beschreibung der Entities

- **`Benutzer`**: Registrierter Benutzer mit Name, eindeutiger E-Mail (`@Username`), BCrypt-Passwort-Hash (`@Password`), Rolle (`USER` / `ADMIN`, `@Roles`), Geburtsdatum, Erwerbsstatus (`LEHRLING`, `ANGESTELLT`, `STUDENT`, `ANDERE`) und Wohnsitzkanton (`@ManyToOne`).
- **`Kanton`**: Schweizer Kanton mit Name und zweistelligem Kürzel (z. B. `ZH`, `BE`, `ZG`).
- **`Steuerregel`**: Vereinfachte Steuerregel pro Kanton, Erwerbsstatus, Altersbereich (`alterVon`–`alterBis`), Einkommensstufe (`einkommenVon`–`einkommenBis`), Steuersatz in Prozent (`satzProzent`) und Steuerjahr (`gueltigJahr`).
- **`Budget`**: Ein privates (`PRIVAT`) oder geteiltes (`GEMEINSAM`) Budget mit prozentualer Aufteilung auf `needsProzent`, `wantsProzent` und `sparenProzent` sowie einem `@Version`-Feld für Optimistic Locking.
- **`Budgetmitglied`**: Verknüpfungstabelle zwischen `Benutzer` und `Budget` mit der jeweiligen Berechtigung (`BESITZER`, `SCHREIBEN`, `LESEN`). Besitzt einen Unique-Constraint auf `(benutzer_id, budget_id)`.
- **`Einkommen`**: Erfasstes Monatseinkommen (`monat` im Format `YYYY-MM`) eines Benutzers für ein Budget mit `brutto`, berechneten Abzügen (`steuer`) und `netto`.
- **`Kategorie`**: Ausgabenkategorie innerhalb eines Budgets, fest zugeordnet zu einem der drei Töpfe (`NEEDS`, `WANTS`, `SPAREN`).
- **`Transaktion`**: Einzelne Ausgabe innerhalb eines Budgets, gebucht auf eine `Kategorie`, mit Betrag (`betrag`), Datum (`datum`), Beschreibung (`beschreibung`) und dem erfassenden Benutzer (`erfasstVon`).
