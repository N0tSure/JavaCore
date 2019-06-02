package com.artemsirosh.mfb.charper12;

import java.lang.annotation.ElementType;
import java.lang.annotation.Target;

@Target(ElementType.METHOD)
@interface MyAnno {
    String val();
    int num();
}
