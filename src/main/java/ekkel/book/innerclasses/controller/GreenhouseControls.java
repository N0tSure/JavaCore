package ekkel.book.innerclasses.controller;

/**
 * Created by cresh on 03.07.16.
 */
public class GreenhouseControls extends Controller {

    private boolean light = false;
    public class LightsOn extends Event {
        public LightsOn(long delayTime) {
            super(delayTime);
        }

        @Override
        public void action() {
            light=true;
        }

        @Override
        public String toString() {
            return "Свет включен";
        }
    }

    public class LightsOff extends Event {
        public LightsOff(long delayTime) {
            super(delayTime);
        }

        @Override
        public void action() {
            light=false;
        }

        @Override
        public String toString() {
            return "Свет выключен";
        }
    }

    private boolean water = false;
    public class WaterOn extends Event {
        public WaterOn(long delayTime) {
            super(delayTime);
        }

        @Override
        public void action() {
            water=true;
        }

        @Override
        public String toString() {
            return "Полив включен";
        }
    }

    public class WaterOff extends Event {
        public WaterOff(long delayTime) {
            super(delayTime);
        }

        @Override
        public void action() {
            water=false;
        }

        @Override
        public String toString() {
            return "Полив отключен";
        }
    }

    private boolean thermostatONDayMode = true;
    public class ThermostatNight extends Event {
        public ThermostatNight(long delayTime) {
            super(delayTime);
        }

        @Override
        public void action() {
            thermostatONDayMode=false;
        }

        @Override
        public String toString() {
            return "Термостат использует ночной режим";
        }
    }

    public class ThermostatDay extends Event {
        public ThermostatDay(long delayTime) {
            super(delayTime);
        }

        @Override
        public void action() {
            thermostatONDayMode=true;
        }

        @Override
        public String toString() {
            return "Термостат использует дневной режим";
        }
    }

    public class Bell extends Event {
        public Bell(long delayTime) {
            super(delayTime);
        }

        @Override
        public void action() {
            addEvent(new Bell(delayTime));
        }

        @Override
        public String toString() {
            return "[Звонок]";
        }
    }

    public class Restart extends Event {
        private Event[] events;
        public Restart(long delay, Event[] events) {
            super(delay);
            this.events = events;
            for (Event e : events) {
                addEvent(e);
            }
        }

        @Override
        public void action() {
            for (Event event : events) {
                event.start();
                addEvent(event);
            }
            this.start();
            addEvent(this);
        }

        @Override
        public String toString() {
            return "Перезапуск системы";
        }
    }

    public static class Terminate extends Event {
        public Terminate(long delayTime) {
            super(delayTime);
        }

        @Override
        public void action() {
            System.exit(0);
        }

        @Override
        public String toString() {
            return "Отключение";
        }
    }
}
