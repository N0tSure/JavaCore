package com.artemsirosh.tij.concurrency;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

import java.util.Random;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

/**
 * Created at 18-07-2019
 *
 * @author Artem Sirosh 'ASir2089@gmail.com'
 */
class BankTellerSimulationTest {

    private static Teller provideNewServingTeller(
            ExecutorService executor, CustomerLine customers, SerialNumberGenerator numberGenerator
    ) {
        final Teller teller = new Teller(numberGenerator.nextSerialNumber(), customers);
        executor.execute(teller);
        return teller;
    }

    @ParameterizedTest(name = "{index}# {arguments} second delay.")
    @DisplayName("Simulation of bank teller")
    @ValueSource(ints = {5, 10, 20})
    void simulation(int delay) throws InterruptedException {
        final ExecutorService executor = Executors.newCachedThreadPool();
        final CustomerLine customers = new CustomerLine(50);
        final Random random = new Random(47);
        final SerialNumberGenerator numberGenerator = new SerialNumberGenerator();

        executor.execute(
                CustomerGenerator.builder()
                        .setCustomers(customers)
                        .setRandom(random)
                        .setSpawnRestInterval(400)
                        .setMaxServiceTime(1000)
                        .build()
        );

        executor.execute(
                TellerManager.builder()
                        .setCustomers(customers)
                        .setAdjustmentPeriod(500)
                        .setTellerSupplier(() -> provideNewServingTeller(executor, customers, numberGenerator))
                        .build()
        );

        TimeUnit.SECONDS.sleep(delay);
        executor.shutdownNow();

    }
}
