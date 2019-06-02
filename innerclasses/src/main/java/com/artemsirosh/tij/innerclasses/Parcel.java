package com.artemsirosh.tij.innerclasses;

/**
 * Created by cresh on 29.05.16.
 */
class Parcel {
    public Destination destination(final String dest, final float price) {
        return new Destination() {
            int cost;
            {
                cost = Math.round(price);
                if (price>100) System.out.println("Превышение бюджета!");
            }
            @Override
            public String readLabel() {
                return dest;
            }
        };
    }
    public Content contents() {
        return new Content() {
            private int value = 0;
            @Override
            public int value() {
                return value;
            }
        };
    }

    private void internalTracking(boolean b) {
        if (b) {
            class TrackinShip {
                private String id;
                private TrackinShip(String id) {
                    this.id = id;
                }
                String getId() {
                    return id;
                }
            }
            TrackinShip ship = new TrackinShip("Parcel");
            String s = ship.getId();
        }
    }
    public void track(boolean b) { internalTracking(b); }
}
