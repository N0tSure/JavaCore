package ekkel.book.typeinfo.factory;

/**
 * Created by cresh on 10.08.16.
 */
class PowerSteeringBelt extends Belt {
    static class Factory implements ekkel.book.typeinfo.factory.Factory<PowerSteeringBelt> {
        @Override
        public PowerSteeringBelt create() {
            return new PowerSteeringBelt();
        }
    }
}
