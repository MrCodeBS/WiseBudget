package ch.wisebudget;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.math.BigDecimal;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicInteger;

import org.junit.jupiter.api.Test;

import ch.wisebudget.entity.Budget;
import ch.wisebudget.entity.BudgetTyp;
import ch.wisebudget.repository.BudgetRepository;
import io.quarkus.narayana.jta.QuarkusTransaction;
import io.quarkus.test.junit.QuarkusTest;
import jakarta.inject.Inject;
import jakarta.persistence.OptimisticLockException;
import jakarta.transaction.RollbackException;

@QuarkusTest
public class OptimisticLockingTest {

    @Inject
    BudgetRepository budgetRepository;

    @Test
    void zweiThreadsAendernGleichzeitigDasselbeBudget() throws Exception {
        // Neues Testbudget anlegen
        Long budgetId = QuarkusTransaction.requiringNew().call(() -> {
            Budget b = new Budget();
            b.setName("Nebenläufigkeits-Budget");
            b.setTyp(BudgetTyp.GEMEINSAM);
            b.setNeedsProzent(new BigDecimal("50.00"));
            b.setWantsProzent(new BigDecimal("30.00"));
            b.setSparenProzent(new BigDecimal("20.00"));
            budgetRepository.persist(b);
            return b.getId();
        });

        CountDownLatch beideGeladen = new CountDownLatch(2);
        CountDownLatch startSignal = new CountDownLatch(1);
        AtomicInteger erfolgreicheUpdates = new AtomicInteger(0);
        AtomicInteger optimisticLockFehler = new AtomicInteger(0);

        ExecutorService pool = Executors.newFixedThreadPool(2);
        try {
            Runnable aufgabe = () -> {
                try {
                    QuarkusTransaction.requiringNew().run(() -> {
                        Budget budget = budgetRepository.findById(budgetId);
                        beideGeladen.countDown();
                        try {
                            startSignal.await(5, TimeUnit.SECONDS);
                        } catch (InterruptedException e) {
                            Thread.currentThread().interrupt();
                        }
                        budget.setName("Geändert von " + Thread.currentThread().getName());
                        budgetRepository.getEntityManager().flush();
                    });
                    erfolgreicheUpdates.incrementAndGet();
                } catch (Exception e) {
                    if (istOptimisticLockFehler(e)) {
                        optimisticLockFehler.incrementAndGet();
                    } else {
                        throw e;
                    }
                }
            };

            Future<?> f1 = pool.submit(aufgabe);
            Future<?> f2 = pool.submit(aufgabe);

            assertTrue(beideGeladen.await(5, TimeUnit.SECONDS));
            startSignal.countDown();

            f1.get(5, TimeUnit.SECONDS);
            f2.get(5, TimeUnit.SECONDS);
        } finally {
            pool.shutdownNow();
        }

        // Genau ein Thread muss erfolgreich sein und der andere mit OptimisticLockException scheitern
        assertEquals(1, erfolgreicheUpdates.get(), "Genau ein Thread sollte erfolgreich speichern.");
        assertEquals(1, optimisticLockFehler.get(), "Genau ein Thread muss einen Optimistic-Lock-Fehler werfen.");
    }

    private boolean istOptimisticLockFehler(Throwable t) {
        Throwable cause = t;
        while (cause != null) {
            if (cause instanceof OptimisticLockException || cause instanceof RollbackException) {
                return true;
            }
            cause = cause.getCause();
        }
        return false;
    }
}
