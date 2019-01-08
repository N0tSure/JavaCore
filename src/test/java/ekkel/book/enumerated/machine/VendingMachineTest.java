package ekkel.book.enumerated.machine;

import com.fasterxml.jackson.dataformat.xml.XmlMapper;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

import static org.junit.jupiter.api.Assertions.assertEquals;

/**
 * Created on 07 Jan, 2019.
 *
 * Check list:
 *
 * ------- 'Sunny' cases
 * 1) Initial state should be 'RESTING';
 * 2) In case of initial state, input money should proceed change state to
 *    'ADDING_MONEY';
 * 3) In case of 'ADDING_MONEY' input has been item and amount of money
 *    sufficient for such kind of item, state should change to 'DISPENSING';
 * 4) When state is 'DISPENSING' getting next step should proceed to
 *    'GIVING_CHANGE' state;
 * 5) In case of 'GIVING_CHANGE' have money to change, when proceed to 'RESTING'
 *    money should giving out;
 * 6) In case of 'GIVING_CHANGE' haven't money to change, when proceed to
 *    'RESTING' amount of money should not be changed;
 * 7) In case of 'ADDING_MONEY' input is item and state have insufficient money
 *    for such kind of item should return to 'ADDING_MONEY';
 * 8) In case of 'ADDING_MONEY' input is a 'ABORT_TRANSACTION' next step should
 *    be a 'GIVING_CHANGE' state;
 * 9) In case of 'RESTING' should proceed termination state if input is 'STOP';
 * 10) In case of 'ADDING_MONEY' should proceed termination state if input is 'STOP'.
 *
 * @author Artemis A. Sirosh
 */
class VendingMachineTest {

    private static final Logger LOGGER = LoggerFactory.getLogger(VendingMachineTest.class);

    private static SelectionItems items;

    @BeforeAll
    static void setUp() throws IOException {
        XmlMapper xmlMapper = new XmlMapper();
        Path xmlFile = Paths.get("./src/main/resources/items.xml");
        items = xmlMapper.readValue(Files.newInputStream(xmlFile), SelectionItems.class);
    }

    @Test
    @DisplayName("Initial state should be 'RESTING'")
    void shouldRestingInitially() {
        final VendingMachine machine = new VendingMachine();
        assertEquals(VendingMachine.State.RESTING, new VendingMachine().getState(), "Initial state");
        assertEquals(0, machine.getAmount(), "initial amount");
    }

    @Test
    @DisplayName("In case of initial state, input money should proceed change state to 'ADDING_MONEY'")
    void shouldProceedAddingMoney() {
        final VendingMachine machine = new VendingMachine();

        machine.next(Coins.QUARTER);
        LOGGER.info("Machine state: {}, money amount: {}", machine.getState(), machine.getAmount());
        assertEquals(VendingMachine.State.ADDING_MONEY, machine.getState(), "Should be in adding money state");
        assertEquals(25, machine.getAmount(), "Should contain added money");

        machine.next(Coins.QUARTER);
        LOGGER.info("Machine state: {}, money amount: {}", machine.getState(), machine.getAmount());
        assertEquals(VendingMachine.State.ADDING_MONEY, machine.getState(), "Should be in adding money state");
        assertEquals(50, machine.getAmount(), "Should contain added money");

    }

    @Test
    @DisplayName("In case of 'ADDING_MONEY' input has been item and amount of money sufficient for such kind of " +
            "item, state should change to 'DISPENSING'")
    void shouldDispenseItem() {
        final VendingMachine machine = new VendingMachine();

        machine.next(Coins.DOLLAR);
        machine.next(items.getItems().get(0));
        LOGGER.info("Machine state: {}, money amount: {}", machine.getState(), machine.getAmount());

        assertEquals(VendingMachine.State.DISPENSING, machine.getState(), "Should move to dispensing state");
        assertEquals(100, machine.getAmount(), "Should contain amount of money for dispensing");

    }

    @Test
    @DisplayName("When state is 'DISPENSING' getting next step should proceed to 'GIVING_CHANGE' state")
    void shouldGivingChangeAfterDispensing() {
        final VendingMachine machine = new VendingMachine();

        machine.next(Coins.DOLLAR);
        machine.next(items.getItems().get(0));
        machine.next();
        LOGGER.info("Machine state: {}, money amount: {}", machine.getState(), machine.getAmount());

        assertEquals(
                VendingMachine.State.GIVING_CHANGE,
                machine.getState(),
                "Should dispense item and move to giving change"
        );
        assertEquals(0, machine.getAmount(), "Should consume money for item");
    }

    @Test
    @DisplayName("In case of 'GIVING_CHANGE' have money to change, when proceed to 'RESTING' money should giving out")
    void shouldProceedToRestIfHasMoneyToChange() {
        final VendingMachine machine = new VendingMachine();

        machine.next(Coins.DOLLAR);
        machine.next(Coins.NICKEL);
        machine.next(items.getItems().get(0));
        machine.next();
        machine.next();
        LOGGER.info("Machine state: {}, money amount: {}", machine.getState(), machine.getAmount());

        assertEquals(
                VendingMachine.State.RESTING,
                machine.getState(),
                "Should move to resting"
        );
        assertEquals(0, machine.getAmount(), "Should give money as change");
    }

    @Test
    @DisplayName("In case of 'GIVING_CHANGE' haven't money to change, when proceed to 'RESTING' " +
            "amount of money should not be changed")
    void shouldProceedToRestIfHasNoMoneyToChange() {
        final VendingMachine machine = new VendingMachine();

        machine.next(Coins.DOLLAR);
        machine.next(items.getItems().get(0));
        machine.next();
        machine.next();
        LOGGER.info("Machine state: {}, money amount: {}", machine.getState(), machine.getAmount());

        assertEquals(
                VendingMachine.State.RESTING,
                machine.getState(),
                "Should move to resting"
        );
        assertEquals(0, machine.getAmount(), "Should have no money");
    }

    @Test
    @DisplayName("In case of 'ADDING_MONEY' input is item and state have insufficient money for such kind of " +
            "item should return to 'ADDING_MONEY'")
    void shouldProcessInsufficientMoneyCase() {
        final VendingMachine machine = new VendingMachine();

        machine.next(Coins.QUARTER);
        machine.next(Coins.DIME);

        machine.next(items.getItems().get(3));
        assertEquals(
                VendingMachine.State.ADDING_MONEY,
                machine.getState(),
                "Should not get an item if not enough money"
        );
        assertEquals(35, machine.getAmount(), "Should keep money");
    }

    @Test
    @DisplayName("In case of 'ADDING_MONEY' input is a 'ABORT_TRANSACTION' next step should be a " +
            "'GIVING_CHANGE' state")
    void shouldGivingChange() {
        final VendingMachine machine = new VendingMachine();

        machine.next(Coins.QUARTER);
        machine.next(Utility.ABORT_TRANSACTION);

        assertEquals(VendingMachine.State.GIVING_CHANGE, machine.getState(), "Should proceed to giving change");
        assertEquals(25, machine.getAmount(), "Should not giving money yet");
    }

    @Test
    @DisplayName("In case of 'RESTING' should proceed termination state if input is 'STOP'")
    void shouldProcessTerminationStateFromResting() {
        final VendingMachine machine = new VendingMachine();

        machine.next(Utility.STOP);
        assertEquals(VendingMachine.State.TERMINAL, machine.getState(), "Should be in terminal state");
        assertEquals(0, machine.getAmount(), "Should keep taken money");
    }

    @Test
    @DisplayName("In case of 'ADDING_MONEY' should proceed termination state if input is 'STOP'")
    void shouldProcessTerminationFromAddingMoney() {
        final VendingMachine machine = new VendingMachine();

        machine.next(Coins.DIME);
        machine.next(Utility.STOP);

        assertEquals(VendingMachine.State.TERMINAL, machine.getState(), "Should be in terminal state");
        assertEquals(10, machine.getAmount(), "Should keep taken money");
    }
}
