package com.artemsirosh.tij.innerclasses;

/**
 * Created by cresh on 30.05.16.
 */
public class ParcelTest {
    public static void main(String[] args) {
        Parcel parcel = new Parcel();
        Content c = parcel.contents();
        Destination d = parcel.destination("Tanzania",100.85F);
    }
}
