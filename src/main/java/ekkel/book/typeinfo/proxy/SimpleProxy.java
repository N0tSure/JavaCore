package ekkel.book.typeinfo.proxy;

/**
 * Created by cresh on 15.08.16.
 */
class SimpleProxy implements Inteface {
    private Inteface proxed;
    SimpleProxy(Inteface proxed) {
        this.proxed = proxed;
    }

    @Override
    public void doSomething() {
        System.out.println("SimpleProxy doSomething");
        proxed.doSomething();
    }

    @Override
    public void somethingElse(String argument) {
        System.out.println("SimpleProxy somethingElse " + argument);
        proxed.somethingElse(argument);
    }
}
