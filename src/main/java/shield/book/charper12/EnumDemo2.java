package shield.book.charper12;

/**
 * Created by cresh on 30.03.16.
 */
class EnumDemo2 {
    public static void main(String[] args) {
        Transport tp;

        System.out.println("Transport: ");

        for (Transport allTransport : Transport.values()) {
            System.out.println(allTransport);
        }

        System.out.println();
        tp=Transport.valueOf("Train".toUpperCase());
        System.out.println(tp);
    }
}
