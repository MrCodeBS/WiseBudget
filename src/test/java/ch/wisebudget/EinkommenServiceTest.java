package ch.wisebudget;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.math.BigDecimal;
import java.time.LocalDate;

import org.junit.jupiter.api.Test;

import ch.wisebudget.entity.Benutzer;
import ch.wisebudget.entity.Einkommen;
import ch.wisebudget.entity.Erwerbsstatus;
import ch.wisebudget.entity.Kanton;
import ch.wisebudget.repository.KantonRepository;
import ch.wisebudget.service.EinkommenService;
import io.quarkus.test.junit.QuarkusTest;
import jakarta.inject.Inject;

@QuarkusTest
public class EinkommenServiceTest {

    @Inject
    EinkommenService einkommenService;

    @Inject
    KantonRepository kantonRepository;

    private Benutzer erstelleTestBenutzer(LocalDate geburtsdatum, Erwerbsstatus status, String kantonKuerzel) {
        Kanton kanton = kantonRepository.findByKuerzel(kantonKuerzel);
        Benutzer benutzer = new Benutzer();
        benutzer.setName("Testperson");
        benutzer.setEmail("test@wisebudget.ch");
        benutzer.setGeburtsdatum(geburtsdatum);
        benutzer.setErwerbsstatus(status);
        benutzer.setKanton(kanton);
        return benutzer;
    }

    @Test
    void lehrlingHatTieferenSteuersatzAlsAngestellter() {
        // Beide über 18 in Zürich (AHV-pflichtig: +6.40%) mit 2000 CHF Brutto
        Benutzer lehrling = erstelleTestBenutzer(LocalDate.of(2005, 5, 10), Erwerbsstatus.LEHRLING, "ZH");
        Benutzer angestellt = erstelleTestBenutzer(LocalDate.of(2000, 5, 10), Erwerbsstatus.ANGESTELLT, "ZH");

        // Lehrling ZH (1500.01 - 5000.00): 3.10% Steuer + 6.40% AHV = 9.50% -> 190.00 CHF Abzug
        Einkommen einkommenLehrling = einkommenService.berechneEinkommen(lehrling, "2026-11", new BigDecimal("2000.00"));
        // Angestellt ZH (0.00 - 4500.00): 7.73% Steuer + 6.40% AHV = 14.13% -> 282.60 CHF Abzug
        Einkommen einkommenAngestellt = einkommenService.berechneEinkommen(angestellt, "2026-11", new BigDecimal("2000.00"));

        assertEquals(new BigDecimal("190.00"), einkommenLehrling.getSteuer());
        assertEquals(new BigDecimal("1810.00"), einkommenLehrling.getNetto());
        assertEquals(new BigDecimal("282.60"), einkommenAngestellt.getSteuer());
        assertEquals(new BigDecimal("1717.40"), einkommenAngestellt.getNetto());
        assertTrue(einkommenLehrling.getSteuer().compareTo(einkommenAngestellt.getSteuer()) < 0);
    }

    @Test
    void alterUnterUndUeberSiebzehnAhvAbzug() {
        // Geboren am 15.06.2009 -> 17. Geburtstag ist 2026, AHV-Pflicht erst ab 01.01.2027
        Benutzer unter17 = erstelleTestBenutzer(LocalDate.of(2009, 6, 15), Erwerbsstatus.LEHRLING, "ZH");
        // Geboren am 15.06.2008 -> 17. Geburtstag war 2025, AHV-Pflicht ab 01.01.2026 (+6.40%)
        Benutzer ueber17 = erstelleTestBenutzer(LocalDate.of(2008, 6, 15), Erwerbsstatus.LEHRLING, "ZH");

        // ZH Lehrling bis 1500 CHF: 0.50% Steuer (ohne AHV = 0.50%, mit AHV = 6.90%)
        Einkommen einkommenOhneAhv = einkommenService.berechneEinkommen(unter17, "2026-11", new BigDecimal("1000.00"));
        Einkommen einkommenMitAhv = einkommenService.berechneEinkommen(ueber17, "2026-11", new BigDecimal("1000.00"));

        assertEquals(new BigDecimal("5.00"), einkommenOhneAhv.getSteuer());
        assertEquals(new BigDecimal("995.00"), einkommenOhneAhv.getNetto());

        assertEquals(new BigDecimal("69.00"), einkommenMitAhv.getSteuer());
        assertEquals(new BigDecimal("931.00"), einkommenMitAhv.getNetto());
    }

    @Test
    void verschiedeneKantoneZhBeZg() {
        BigDecimal brutto = new BigDecimal("4000.00");
        LocalDate geburtsdatum = LocalDate.of(1998, 3, 20);

        Benutzer benutzerZg = erstelleTestBenutzer(geburtsdatum, Erwerbsstatus.ANGESTELLT, "ZG");
        Benutzer benutzerZh = erstelleTestBenutzer(geburtsdatum, Erwerbsstatus.ANGESTELLT, "ZH");
        Benutzer benutzerBe = erstelleTestBenutzer(geburtsdatum, Erwerbsstatus.ANGESTELLT, "BE");

        // ZG: 3.23% + 6.40% = 9.63% -> 385.20 CHF
        Einkommen zg = einkommenService.berechneEinkommen(benutzerZg, "2026-11", brutto);
        // ZH: 7.73% + 6.40% = 14.13% -> 565.20 CHF
        Einkommen zh = einkommenService.berechneEinkommen(benutzerZh, "2026-11", brutto);
        // BE: 13.40% + 6.40% = 19.80% -> 792.00 CHF
        Einkommen be = einkommenService.berechneEinkommen(benutzerBe, "2026-11", brutto);

        assertEquals(new BigDecimal("385.20"), zg.getSteuer());
        assertEquals(new BigDecimal("565.20"), zh.getSteuer());
        assertEquals(new BigDecimal("792.00"), be.getSteuer());
        assertTrue(zg.getSteuer().compareTo(zh.getSteuer()) < 0);
        assertTrue(zh.getSteuer().compareTo(be.getSteuer()) < 0);
    }

    @Test
    void keinePassendeSteuerregelWirftFehler() {
        Benutzer benutzer = erstelleTestBenutzer(LocalDate.of(1995, 1, 1), Erwerbsstatus.ANGESTELLT, "ZH");

        // Einkommen 99999.00 CHF liegt ausserhalb der definierten Stufen (bis 25000.00 CHF)
        IllegalArgumentException fehler = assertThrows(
                IllegalArgumentException.class,
                () -> einkommenService.berechneEinkommen(benutzer, "2026-11", new BigDecimal("99999.00"))
        );
        assertTrue(fehler.getMessage().contains("Keine passende Steuerregel"));
    }
}
