package de.jebc;

public class Valve<T> extends Join<T, Void, T> {

    @Override
    protected T join(T input1, Void input2) {
        return input1;
    }

}
