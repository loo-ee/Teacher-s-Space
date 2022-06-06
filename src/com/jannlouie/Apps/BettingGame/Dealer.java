package com.jannlouie.Apps.BettingGame;

public class Dealer {
    private final Die die1 = new Die();
    private final Die die2 = new Die();
    private int die1Value = 0;
    private int die2Value = 0;

    public Dealer() {}

    public void rollDice() {
        this.die1.roll();
        this.die2.roll();
        this.die1Value = die1.getValue();
        this.die2Value = die2.getValue();
    }

    public String getResult() {
        String result;
        int sum = this.die1Value + this.die2Value;

        if (sum % 2 == 0) {
            result = "Even";
        }
        else {
            result = "Odd";
        }
        return result;
    }

    public int getDie1Value() {
        return this.die1Value;
    }

    public int getDie2Value() {
        return this.die2Value;
    }
}
