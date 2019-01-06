package ekkel.book.enumerated;

import java.util.stream.Stream;

/**
 * Created on 06 Jan, 2019.
 *
 * @author Artemis A. Sirosh
 */
class Mail {

    private static long counter = 0;

    private final GeneralDelivery generalDelivery;
    private final Scannability scannability;
    private final Readability readability;
    private final Address address;
    private final Forward forward;
    private final ReturnAddress returnAddress;
    private final long id;

    private Mail(
            GeneralDelivery generalDelivery,
            Scannability scannability,
            Readability readability,
            Address address,
            Forward forward,
            ReturnAddress returnAddress
    ) {
        this.generalDelivery = generalDelivery;
        this.scannability = scannability;
        this.readability = readability;
        this.address = address;
        this.returnAddress = returnAddress;
        this.forward = forward;
        this.id = counter++;
    }

    static Mail randomMail() {
        GeneralDelivery generalDelivery= Enums.random(GeneralDelivery.class);
        Scannability scannability = Enums.random(Scannability.class);
        Readability readability = Enums.random(Readability.class);
        Address address = Enums.random(Address.class);
        Forward forward = Enums.random(Forward.class);
        ReturnAddress returnAddress = Enums.random(ReturnAddress.class);

        return new Mail(generalDelivery, scannability, readability, address, forward, returnAddress);
    }

    static Stream<Mail> stream() {
        return Stream.generate(Mail::randomMail);
    }

    String details() {
        return toString() +
                ", General Delivery: " + generalDelivery +
                ", Address Scanability: " + scannability +
                ", Address Readability: " + readability +
                ", Address Address: " + address +
                ", Mail forwarding: " + forward +
                ", Return address: " + returnAddress;
    }

    public GeneralDelivery getGeneralDelivery() {
        return generalDelivery;
    }

    public Scannability getScannability() {
        return scannability;
    }

    public Readability getReadability() {
        return readability;
    }

    public Address getAddress() {
        return address;
    }

    public Forward getForward() {
        return forward;
    }

    public ReturnAddress getReturnAddress() {
        return returnAddress;
    }

    @Override
    public String toString() {
        return "Mail #" + id;
    }

    enum GeneralDelivery {YES,NO1,NO2,NO3,NO4,NO5}
    enum Scannability {UNSCANNABLE,YES1,YES2,YES3,YES4}
    enum Readability {ILLEGIBLE,YES1,YES2,YES3,YES4}
    enum Address {INCORRECT,OK1,OK2,OK3,OK4,OK5,OK6}
    enum Forward {YES,NO1,NO2,NO3,NO4,NO5}
    enum ReturnAddress {MISSING,OK1,OK2,OK3,OK4,OK5}

}
