package ekkel.book.typeinfo.factory;

/**
 * Created by cresh on 10.08.16.
 */
class FuelFilter extends Filter {
   static class Factory implements ekkel.book.typeinfo.factory.Factory<FuelFilter> {
       @Override
       public FuelFilter create() {
           return new FuelFilter();
       }
   }
}
