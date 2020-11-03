package com.trueprogramming.immutable;

public class Wrapper {

    private final Mutable mutable;

    public Wrapper(Mutable mutable) {
        this.mutable = mutable.clone();
    }

    //Se il chiamante modifica l’oggetto ritornato da getMutable() questo non ha effetto su quello
    //riferito in ImmutableClassWrapper
    public Mutable getMutable() {
        return mutable.clone();
    }
}
