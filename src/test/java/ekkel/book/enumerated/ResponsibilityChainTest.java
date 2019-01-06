package ekkel.book.enumerated;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.opentest4j.AssertionFailedError;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

/**
 * Created on 06 Jan, 2019.
 *
 * @author Artemis A. Sirosh
 */
class ResponsibilityChainTest {

    private static final Logger LOGGER = LoggerFactory.getLogger(ResponsibilityChainTest.class);

    @Test
    void shouldProcessGeneralDelivery() {
        final Mail mail = Mail.stream()
                .filter(m -> Mail.GeneralDelivery.YES == m.getGeneralDelivery())
                .findFirst()
                .orElseThrow(AssertionFailedError::new);

        LOGGER.info(mail.details());

        assertEquals(PostOffice.MailHandler.GENERAL_DELIVERY, PostOffice.handler(mail));
    }

    @Test
    void shouldProcessMachineScan() {
        final Mail mail = Mail.stream()
                .filter(m -> Mail.GeneralDelivery.YES != m.getGeneralDelivery())
                .filter(m -> Mail.Scannability.UNSCANNABLE != m.getScannability())
                .filter(m -> Mail.Address.INCORRECT != m.getAddress())
                .findFirst()
                .orElseThrow(AssertionFailedError::new);

        LOGGER.info(mail.details());

        assertEquals(PostOffice.MailHandler.MACHINE_SCAN, PostOffice.handler(mail));
    }

    @Test
    void shouldProcessVisualInspection() {
        final Mail mail = Mail.stream()
                .filter(m -> Mail.GeneralDelivery.YES != m.getGeneralDelivery())
                .filter(m -> Mail.Scannability.UNSCANNABLE == m.getScannability())
                .filter(m -> Mail.Readability.ILLEGIBLE != m.getReadability())
                .filter(m -> Mail.Address.INCORRECT != m.getAddress())
                .findFirst()
                .orElseThrow(AssertionFailedError::new);

        LOGGER.info(mail.details());

        assertEquals(PostOffice.MailHandler.VISUAL_INSPECTION, PostOffice.handler(mail));
    }

    @Test
    void shouldProcessReturnToSender() {
        final Mail mail = Mail.stream()
                .filter(m -> Mail.GeneralDelivery.YES != m.getGeneralDelivery())
                .filter(m -> Mail.Scannability.UNSCANNABLE == m.getScannability())
                .filter(m -> Mail.Readability.ILLEGIBLE == m.getReadability())
                .filter(m -> Mail.ReturnAddress.MISSING != m.getReturnAddress())
                .findFirst()
                .orElseThrow(AssertionFailedError::new);

        LOGGER.info(mail.details());

        assertEquals(PostOffice.MailHandler.RETURN_TO_SENDER, PostOffice.handler(mail));
    }

    @Test
    void shouldRejectDeadLetter() {
        final Mail mail = Mail.stream()
                .filter(m -> Mail.GeneralDelivery.YES != m.getGeneralDelivery())
                .filter(m -> Mail.Scannability.UNSCANNABLE == m.getScannability())
                .filter(m -> Mail.Readability.ILLEGIBLE == m.getReadability())
                .filter(m -> Mail.ReturnAddress.MISSING == m.getReturnAddress())
                .findFirst()
                .orElseThrow(AssertionFailedError::new);

        LOGGER.info(mail.details());

        IllegalStateException exception = assertThrows(IllegalStateException.class, () -> PostOffice.handler(mail));
        LOGGER.info("Mail handling failure.", exception);
    }
}
