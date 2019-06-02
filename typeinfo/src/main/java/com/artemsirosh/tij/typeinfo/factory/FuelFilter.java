package com.artemsirosh.tij.typeinfo.factory;

/**
 * Created by cresh on 10.08.16.
 */
class FuelFilter extends Filter {
   static class Factory implements com.artemsirosh.tij.typeinfo.factory.Factory<FuelFilter> {
       @Override
       public FuelFilter create() {
           return new FuelFilter();
       }
   }
}
