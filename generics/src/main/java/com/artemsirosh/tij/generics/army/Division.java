package com.artemsirosh.tij.generics.army;

import java.util.ArrayList;

/**
 * Created by cresh on 29.08.16.
 */
class Division extends ArrayList<Group> {
    Division(int amountUnits, int amountGroups) {
        for (int i = 0; i < amountGroups; i++) {
            this.add(new Group(amountUnits));
        }
    }
}
