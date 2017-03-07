package shield.book.charper12;

/**
 * Created by cresh on 30.03.16.
 */

class EnumDemo {
    public static void main(String[] args) {
        Transport tp=Transport.AIRPLANE;

        System.out.println("tp = " + tp);
        System.out.println();

        tp=Transport.TRAIN;

        if (Transport.TRAIN == tp) System.out.println("tp=Transport.TRAIN\n");

        switch (tp) {
            case CAR:
                System.out.println("Vehicle transport people.");
                break;
            case TRUCK:
                System.out.println("Truck transports cargo.");
                break;
            case AIRPLANE:
                System.out.println("Airplane flies.");
                break;
            case TRAIN:
                System.out.println("Train goes on railway.");
                break;
            case BOAT:
                System.out.println("Boat goes on water.");
                break;
        }
    }
}
