package javajournal.immutable;

public class Mutable {

    private int value;

    public Mutable(int value) {
        this.value = value;
    }

    public int getValue() {
        return value;
    }

    public void setValue(int value) {
        this.value = value;
    }

    @Override
    protected Mutable clone()  {
        return new Mutable(this.value);
    }

}
