package com.jannlouie.Game;

import java.util.Random;

public class Die {
    private int sides = 6;
    private int value = 0;

    private Random random = new Random();

    Die() {}

    public void roll() {
        final int minVal = 1;
        this.value = (random.nextInt(0, 10) % (sides - minVal + 1)) + minVal;
    }

    public int getSides() {
        return this.sides;
    }

    public int getValue() {
        return this.value;
    }
}
