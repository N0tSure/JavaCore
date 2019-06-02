package com.artemsirosh.tij.enumerated.roshambo;

/**
 * Created on 08 Jan, 2019.
 *
 * @author Artemis A. Sirosh
 */
interface Item extends Competitor<Item> {

    Outcome eval(Paper p);
    Outcome eval(Scissors s);
    Outcome eval(Rock r);
}
