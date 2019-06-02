package com.artemsirosh.tij.enumerated.menu;

import com.artemsirosh.tij.enumerated.Enums;

/**
 * Created on 14 Dec, 2018.
 *
 * @author Artemis A. Sirosh
 */
public enum Course {

    APPETIZER(Food.Appetizer.class),
    MAIN_COURSE(Food.MainCourse.class),
    DESSERT(Food.Dessert.class),
    COFFEE(Food.Coffee.class);

    private final Food[] values;

    Course(Class<? extends Food> kind) {
        values = kind.getEnumConstants();
    }

    public Food randomSelection() {
        return Enums.random(values);
    }

}
