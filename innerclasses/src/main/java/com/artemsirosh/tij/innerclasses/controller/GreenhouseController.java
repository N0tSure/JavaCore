package com.artemsirosh.tij.innerclasses.controller;

/**
 * Created by cresh on 05.07.16.
 */
public class GreenhouseController {
    public static void main(String[] args) {
        GreenhouseControls gc = new GreenhouseControls();

        gc.addEvent(gc.new Bell(900));
        Event[] events = {
                gc.new ThermostatNight(0),
                gc.new LightsOn(200),
                gc.new LightsOff(400),
                gc.new WaterOn(600),
                gc.new WaterOff(800),
                gc.new ThermostatDay(1400)
        };

        gc.addEvent(gc.new Restart(2000,events));
        gc.addEvent(new  GreenhouseControls.Terminate(500000000));
        gc.run();
    }
}
