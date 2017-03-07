package shield.book.charper12;

/**
 * Created by cresh on 30.03.16.
 */
public class EnumDemo3 {
    public static void main(String[] args) {
        System.out.printf("Usual speed of plane is %d km per hour.\n",Transport.AIRPLANE.getSpeed());

        System.out.println("Usual speed another transports.");

        for (Transport transport : Transport.values()) {
            System.out.printf("Usual speed of %s is %d km per hour.\n",transport,transport.getSpeed());
        }
    }
}
