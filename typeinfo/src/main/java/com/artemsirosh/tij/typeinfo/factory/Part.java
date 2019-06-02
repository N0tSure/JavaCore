package com.artemsirosh.tij.typeinfo.factory;

import java.lang.reflect.Proxy;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

/**
 * Created by cresh on 10.08.16.
 */
public class Part {
    @SuppressWarnings("unchecked")
    static Factory<? extends Part> getNullPartFactory() {
        return (Factory) Proxy.newProxyInstance(Part.class.getClassLoader(),
                new Class[]{Factory.class},new NullPartProxyHandler());
    }
    @Override
    public String toString() {
        return this.getClass().getSimpleName();
    }

    static List<Factory<? extends Part>> factories = new ArrayList<>();
    static List<Class<? extends Part>> parts = new ArrayList<>();
    static {
        factories.add(new FuelFilter.Factory());
        factories.add(new AirFilter.Factory());
        factories.add(new CabinAirFilter.Factory());
        factories.add(new OilFilter.Factory());
        factories.add(new FanBelt.Factory());
        factories.add(new PowerSteeringBelt.Factory());
        factories.add(new GeneratorBelt.Factory());
        factories.add(getNullPartFactory());
        partsLoader();
    }

    private static Random random = new Random(47);
    public static Part createRandom() {
        int n = random.nextInt(factories.size());
        return factories.get(n).create();
    }
    public static Part createPart() throws InstantiationException, IllegalAccessException {
        int n = random.nextInt(parts.size());
        return parts.get(n).newInstance();
    }

    private static void partsLoader() {
        parts.add(FuelFilter.class);
        parts.add(AirFilter.class);
        parts.add(CabinAirFilter.class);
        parts.add(OilFilter.class);
        parts.add(FanBelt.class);
        parts.add(PowerSteeringBelt.class);
        parts.add(GeneratorBelt.class);
    }
}
