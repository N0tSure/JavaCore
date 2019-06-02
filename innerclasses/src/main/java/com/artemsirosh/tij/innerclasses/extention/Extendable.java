package com.artemsirosh.tij.innerclasses.extention;

import com.artemsirosh.tij.innerclasses.extention.template.Template;

/**
 * Created by cresh on 30.05.16.
 */
public class Extendable {
    protected class Inner implements Template {
       public String param() { return "Extendable -> Inner"; }
    }

    protected Inner inner() {
        return new Inner();
    }
}
