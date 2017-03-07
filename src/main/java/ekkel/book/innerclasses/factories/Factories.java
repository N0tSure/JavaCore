package ekkel.book.innerclasses.factories;

/**
 * Created by cresh on 11.06.16.
 */
class Factories {
    private static void serviceConsumer(ServiceFactory factory) {
        Service s = factory.getService();
        s.first();
        s.second();
    }

    public static void main(String[] args) {
        serviceConsumer(FirstImplementation.serviceFactory);
        serviceConsumer(SecondImplementation.serviceFactory);
    }
}
