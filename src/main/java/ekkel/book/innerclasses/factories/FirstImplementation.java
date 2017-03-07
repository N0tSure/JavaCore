package ekkel.book.innerclasses.factories;

/**
 * Created by cresh on 11.06.16.
 */
class FirstImplementation implements Service {
    private String label = "First Implementation";
    private FirstImplementation() {
        //no-op
    }

    @Override
    public void first() {
        System.out.println(label + ": first()");
    }

    @Override
    public void second() {
        System.out.println(label + ": second()");
    }

    public static ServiceFactory serviceFactory = new ServiceFactory() {
        @Override
        public Service getService() {
            return new FirstImplementation();
        }
    };
}
