package com.gamesrum.calculator;

public class Command {
    private Operator operator;
    private double input;

    public Command(Operator operator, double input) {
        this.operator = operator;
        this.input = input;
    }

    public Operator getOperator() {
        return operator;
    }

    public void setOperator(Operator operator) {
        this.operator = operator;
    }

    public double getInput() {
        return input;
    }

    public void setInput(double input) {
        this.input = input;
    }
}
