package com.artemsirosh.tij.innerclasses.factories;

/**
 * Created by cresh on 11.06.16.
 */
class SecondImplementation implements Service {
    private String label = "Second Implementation";
    private SecondImplementation() {
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
            return new SecondImplementation();
        }
    };
}
