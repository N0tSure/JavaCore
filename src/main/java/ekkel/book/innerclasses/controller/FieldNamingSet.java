package ekkel.book.innerclasses.controller;

public enum FieldNamingSet {
    LIGHT_ON("light_on"), LIGHT_OFF("light_off"), WATER_ON("water_on"), WATER_OFF("water_off"),
    THERMOSTAT_DAY("thermostat_day"), THERMOSTAT_NIGHT("thermostat_night"), BELL("bell"), RESTART("restart"),
    TERMINATE("terminate");

    private String field;

    FieldNamingSet(String field) {
        this.field = field;
    }

    public String getFieldName() {
        return field;
    }

}
