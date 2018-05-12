package javajournal.immutable;

public final class Immutable1 {

    private final Mutable mutable;

    public Immutable1(Mutable mutable) {
        this.mutable = new Mutable(mutable.getValue());
    }
}
