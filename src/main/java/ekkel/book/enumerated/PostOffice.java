package ekkel.book.enumerated;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.EnumMap;
import java.util.Map;

/**
 * Created on 06 Jan, 2019.
 *
 * @author Artemis A. Sirosh
 */
class PostOffice {

    private static final Logger LOGGER;
    private static final EnumMap<MailHandler, Handler> HANDLERS;

    static {
        LOGGER = LoggerFactory.getLogger(PostOffice.class);
        HANDLERS = new EnumMap<>(MailHandler.class);

        HANDLERS.put(
                MailHandler.GENERAL_DELIVERY,
                mail -> {
                    if (mail.getGeneralDelivery() == Mail.GeneralDelivery.YES) {
                        LOGGER.info("Using general delivery for: {}.", mail);
                        return true;
                    }

                    return false;
                }
        );

        HANDLERS.put(
                MailHandler.MACHINE_SCAN,
                mail -> {
                    final boolean canHandle = mail.getScannability() != Mail.Scannability.UNSCANNABLE &&
                            mail.getForward() != Mail.Forward.YES && mail.getAddress() != Mail.Address.INCORRECT;

                    if (canHandle) {
                        LOGGER.info("Delivering {} automatically.", mail);
                    }

                    return canHandle;
                }
        );

        HANDLERS.put(
               MailHandler.VISUAL_INSPECTION,
               mail -> {
                   final boolean canHandle = mail.getReadability() != Mail.Readability.ILLEGIBLE &&
                           mail.getForward() != Mail.Forward.YES && mail.getAddress() != Mail.Address.INCORRECT;

                   if (canHandle) {
                       LOGGER.info("Delivering {} normally.", mail);
                   }

                   return canHandle;
               }
        );

        HANDLERS.put(
                MailHandler.FORWARDING,
                mail -> {
                    final boolean canHandle = mail.getForward() == Mail.Forward.YES &&
                            (mail.getScannability() != Mail.Scannability.UNSCANNABLE ||
                                    mail.getReadability() != Mail.Readability.ILLEGIBLE) &&
                            mail.getAddress() != Mail.Address.INCORRECT;

                    if (canHandle)
                        LOGGER.info("Forwarding {}.", mail);

                    return canHandle;
                }
        );

        HANDLERS.put(
                MailHandler.RETURN_TO_SENDER,
                mail -> {
                    if (mail.getReturnAddress() != Mail.ReturnAddress.MISSING) {
                        LOGGER.info("Returning {} to sender.", mail);
                        return true;
                    }

                    return false;
                }
        );
    }

    static MailHandler handler(final Mail mail) {
        return HANDLERS.entrySet().stream()
                .filter(entry -> entry.getValue().handle(mail))
                .map(Map.Entry::getKey)
                .findFirst()
                .orElseThrow(() -> new IllegalStateException(mail + " is a dead letter."));
    }

    interface Handler {
        boolean handle(Mail mail);
    }

    enum MailHandler {
        GENERAL_DELIVERY,
        MACHINE_SCAN,
        VISUAL_INSPECTION,
        FORWARDING,
        RETURN_TO_SENDER
    }
}
