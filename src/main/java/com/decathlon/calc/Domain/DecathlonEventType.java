package com.decathlon.calc.Domain;

public enum DecathlonEventType {
    SPRINT("track"),
    LONG_JUMP("field"),
    SHOT_PUT("field"),
    HIGH_JUMP("field"),
    FOUR_HUNDRED_METER_RUN("track"),
    HURDLES("track"),
    DISCUS_THROW("field"),
    POLE_VAULT("field"),
    JAVELIN_THROW("field"),
    ONE_AND_HALF_KILOMETER_RUN("track");

    private final String eventType;
    DecathlonEventType(String eventType) {
        this.eventType = eventType;
    }

    public String getEventType() {
        return eventType;
    }
}
