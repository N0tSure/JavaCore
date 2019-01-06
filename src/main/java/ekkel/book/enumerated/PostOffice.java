package ekkel.book.enumerated;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.Arrays;

/**
 * Created on 06 Jan, 2019.
 *
 * @author Artemis A. Sirosh
 */
public class PostOffice {

    private static final Logger LOGGER = LoggerFactory.getLogger(PostOffice.class);

    enum MailHandler {
        GENERAL_DELIVERY {
            boolean handle(Mail m) {
                switch(m.getGeneralDelivery()) {
                    case YES:
                        LOGGER.info("Using general delivery for: {}.", m);
                        return true;

                    default:
                        return false;
                }
            }
        },
        MACHINE_SCAN {
            boolean handle(Mail m) {
                switch(m.getScannability()) {
                    case UNSCANNABLE:
                        return false;

                    default:
                        switch(m.getAddress()) {
                            case INCORRECT:
                                return false;

                            default:
                                LOGGER.info("Delivering {} automatically.", m);
                                return true;
                        }
                }
            }
        },
        VISUAL_INSPECTION {
            boolean handle(Mail m) {
                switch(m.getReadability()) {
                    case ILLEGIBLE:
                        return false;

                    default:
                        switch(m.getAddress()) {
                            case INCORRECT:
                                return false;
                            default:
                                LOGGER.info("Delivering {} normally.", m);
                                return true;
                        }
                }
            }
        },
        RETURN_TO_SENDER {
            boolean handle(Mail m) {
                switch(m.getReturnAddress()) {
                    case MISSING:
                        return false;
                    default:
                        LOGGER.info("Returning {} to sender.", m);
                        return true;
                }
            }
        };

        abstract boolean handle(Mail m);
    }

    static MailHandler handler(final Mail mail) {
        return Arrays.stream(MailHandler.values())
                .filter(mailHandler -> mailHandler.handle(mail))
                .findFirst()
                .orElseThrow(() -> new IllegalStateException(mail + " is a dead letter."));
    }
}
