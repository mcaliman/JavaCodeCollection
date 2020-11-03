package com.trueprogramming.immutable;

public final class Immutable3 {

    private final String text;

    private Immutable3(String text) {
        this.text = text;
    }

    public String getText() {
        return this.text;
    }

    public static Immutable3 create(String text) {
        return new Immutable3(text);
    }
}
