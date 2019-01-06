package ekkel.book.enumerated;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.Arrays;

/**
 * Created on 06 Jan, 2019.
 *
 * @author Artemis A. Sirosh
 */
class PostOffice {

    private static final Logger LOGGER = LoggerFactory.getLogger(PostOffice.class);

    static MailHandler handler(final Mail mail) {
        return Arrays.stream(MailHandler.values())
                .filter(mailHandler -> mailHandler.handle(mail))
                .findFirst()
                .orElseThrow(() -> new IllegalStateException(mail + " is a dead letter."));
    }

    enum MailHandler {
        GENERAL_DELIVERY {

            @Override
            boolean handle(Mail m) {
                switch (m.getGeneralDelivery()) {
                    case YES:
                        LOGGER.info("Using general delivery for: {}.", m);
                        return true;

                    default:
                        return false;
                }
            }
        },
        MACHINE_SCAN {

            @Override
            boolean handle(Mail m) {

                final boolean result;
                switch (m.getScannability()) {

                    case UNSCANNABLE:
                        result = false;
                        break;

                    default:
                        switch (m.getForward()) {
                            case YES:
                                result = false;
                                break;
                            default:
                                switch (m.getAddress()) {
                                    case INCORRECT:
                                        result = false;
                                        break;
                                    default:
                                        LOGGER.info("Delivering {} automatically.", m);
                                        result = true;
                                }
                        }
                }

                return result;
            }
        },
        VISUAL_INSPECTION {

            @Override
            boolean handle(Mail m) {

                final boolean result;
                switch (m.getReadability()) {
                    case ILLEGIBLE:
                        result = false;
                        break;

                    default:
                        switch (m.getForward()) {
                            case YES:
                                result = false;
                                break;

                            default:
                                switch (m.getAddress()) {
                                    case INCORRECT:
                                        result = false;
                                        break;

                                    default:
                                        LOGGER.info("Delivering {} normally.", m);
                                        result = true;
                                }
                        }
                }

                return result;
            }
        },
        FORWARDING {
            @Override
            boolean handle(Mail mail) {
                final boolean result;
                switch (mail.getForward()) {
                    case YES:
                        switch (mail.getScannability()) {
                            case UNSCANNABLE:
                                switch (mail.getReadability()) {
                                    case ILLEGIBLE:
                                        result = false;
                                        break;
                                    default:
                                        switch (mail.getAddress()) {
                                            case INCORRECT:
                                                result = false;
                                                break;
                                            default:
                                                LOGGER.info("Forwarding {}.", mail);
                                                result = true;
                                        }
                                }
                                break;

                            default:
                                switch (mail.getAddress()) {
                                    case INCORRECT:
                                        result = false;
                                        break;
                                    default:
                                        LOGGER.info("Forwarding {}.", mail);
                                        result = true;
                                }
                        }
                        break;

                    default:
                        result = false;
                }

                return result;
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
}
