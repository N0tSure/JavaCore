package ekkel.book.innerclasses.extention.extended;

import ekkel.book.innerclasses.extention.Extendable;
import ekkel.book.innerclasses.extention.template.Template;

/**
 * Created by cresh on 30.05.16.
 */
public class Deployed extends Extendable {

    public Template template() {
        return new Deployed().inner();
    }

    public static void main(String[] args) {
        Deployed dd = new Deployed();
        String s = dd.template().param();
        System.out.println(s);
    }
}
