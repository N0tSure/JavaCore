package com.artemsirosh.tij.innerclasses.extention.extended;

import com.artemsirosh.tij.innerclasses.extention.Extendable;
import com.artemsirosh.tij.innerclasses.extention.template.Template;

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
