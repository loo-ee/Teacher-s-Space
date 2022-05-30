package com.jannlouie.Apps.Games.BettingGame;

import java.util.Random;

public class Die {
    private final int sides = 6;
    private int value = 0;

    private final Random random = new Random();

    public Die() {}

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
