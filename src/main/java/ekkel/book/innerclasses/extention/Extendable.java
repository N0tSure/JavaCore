package ekkel.book.innerclasses.extention;

import ekkel.book.innerclasses.extention.template.Template;

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
