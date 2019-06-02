package com.artemsirosh.tij.generics.mixins;

import com.artemsirosh.tij.util.TwoTuple;
import static com.artemsirosh.tij.util.Tuple.*;
/**
 * Created by cresh on 18.12.16.
 */
class DynamicProxyMixin {
    public static void main(String[] args) {
        Object mixin = MixinProxy.newInstance(
                tuple(new BasicImpl(), Basic.class),
                tuple(new TimeStampedImpl(), TimeStamped.class),
                tuple(new SerialNumbered(), Serial.class),
                tuple(new Green(), Colored.class)
        );

        Basic basic = (Basic) mixin;
        TimeStamped stamped = (TimeStamped) mixin;
        Serial serial = (Serial) mixin;
        Colored colored = (Colored) mixin;

        basic.set("Hello");
        System.out.println(basic.get());
        System.out.println(stamped.getStamp());
        System.out.println(serial.getSerialNumber());
        System.out.println(colored.getColor().toString());
    }
}
