package com.artemsirosh.tij.io.xml;

import com.google.common.base.Objects;

import java.util.ArrayList;
import java.util.List;

import static com.google.common.base.Objects.equal;

/**
 * Created on 24 Jun, 2017.
 *
 * @author Artemis A. Sirosh
 */
public class People extends ArrayList<Person> {

    public People() {}

    public People(List<Person> peoples) {
        this.addAll(peoples);
    }

}
