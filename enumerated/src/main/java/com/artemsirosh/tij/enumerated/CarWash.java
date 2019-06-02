package com.artemsirosh.tij.enumerated;

import com.google.common.base.MoreObjects;

import java.util.EnumSet;
import java.util.Set;
import java.util.stream.Collectors;

/**
 * Created on 06 Jan, 2019.
 *
 * @author Artemis A. Sirosh
 */
class CarWash {

    private final EnumSet<Cycle> cycles = EnumSet.of(Cycle.RINSE, Cycle.BASIC);

    void add(Cycle cycle) {
        cycles.add(cycle);
    }

    Set<String> washCar() {
        return cycles.stream()
                .map(Cycle::action)
                .collect(Collectors.toSet());
    }

    enum Cycle {
        UNDER_BODY {
            @Override
            public String action() { return "Spraying the under body"; }
        },
        WHEEL_WASH {
            @Override
            public String action() { return "Washing the wheels"; }
        },
        PRE_WASH {
            @Override
            public String action() { return "Loosening the dirt"; }
        },
        BASIC {
            @Override
            public String action() { return "The basic wash"; }
        },
        HOT_WAX {
            @Override
            public String action() { return "Applying hot wax"; }
        },
        RINSE {
            @Override
            public String action() { return "Rinsing"; }
        },
        BLOW_DRY {
            @Override
            public String action() { return "Blowing dry"; }
        };

        abstract String action();
    }

    @Override
    public String toString() {
        return MoreObjects.toStringHelper(this.getClass())
                .add("cycles", washCar())
                .toString();
    }
}
