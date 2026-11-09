package ch.wisebudget;

import static io.restassured.RestAssured.given;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import ch.wisebudget.entity.Benutzer;
import ch.wisebudget.entity.Berechtigung;
import ch.wisebudget.entity.Budget;
import ch.wisebudget.entity.Budgetmitglied;
import ch.wisebudget.repository.BenutzerRepository;
import ch.wisebudget.repository.BudgetRepository;
import ch.wisebudget.repository.BudgetmitgliedRepository;
import io.quarkus.test.junit.QuarkusTest;
import io.quarkus.test.security.TestSecurity;
import jakarta.inject.Inject;
import jakarta.transaction.Transactional;

@QuarkusTest
public class BerechtigungTest {

    @Inject
    BudgetRepository budgetRepository;

    @Inject
    BenutzerRepository benutzerRepository;

    @Inject
    BudgetmitgliedRepository budgetmitgliedRepository;

    @BeforeEach
    @Transactional
    void setzeSarahAufLesen() {
        Budget budget = budgetRepository.findById(1L);
        Benutzer sarah = benutzerRepository.findByEmail("sarah@wisebudget.ch");
        Budgetmitglied mitglied = budgetmitgliedRepository.findByBudgetUndBenutzer(budget, sarah);
        if (mitglied != null) {
            mitglied.setBerechtigung(Berechtigung.LESEN);
        }
    }

    @Test
    @TestSecurity(user = "sarah@wisebudget.ch", roles = "USER")
    void lesenBenutzerDarfKeineTransaktionBuchen() {
        given()
                .redirects().follow(false)
                .formParam("kategorieId", 1)
                .formParam("betrag", "50.00")
                .formParam("datum", "2026-11-10")
                .formParam("beschreibung", "Unerlaubte Buchung")
                .when()
                .post("/budgets/1/transaktionen")
                .then()
                .statusCode(403);
    }

    @Test
    @TestSecurity(user = "admin@wisebudget.ch", roles = "ADMIN")
    void nichtMitgliedDarfBudgetNichtOeffnen() {
        // admin@wisebudget.ch ist nicht Mitglied in Budget 1
        given()
                .when()
                .get("/budgets/1")
                .then()
                .statusCode(403);
    }

    @Test
    @TestSecurity(user = "lukas@wisebudget.ch", roles = "USER")
    void besitzerDarfBudgetOeffnen() {
        given()
                .when()
                .get("/budgets/1")
                .then()
                .statusCode(200);
    }
}
