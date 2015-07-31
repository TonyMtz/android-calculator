package com.gamesrum.calculator;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class Engine {
    private List<Command> chain;
    private Operator lastOperator = Operator.ADDITION;

    public Engine() {
        this.chain = new ArrayList<>();
    }

    public double add(double input) {
        return addToChain(Operator.ADDITION, input);
    }

    public double sub(double input) {
        return addToChain(Operator.SUBTRACTION, input);
    }

    public double times(double input) {
        return addToChain(Operator.TIMES, input);
    }

    public double div(double input) {
        return addToChain(Operator.DIV, input);
    }

    public double equals(double input) {
        Command newLink = new Command(lastOperator, input);
        chain.add(newLink);
        lastOperator = Operator.ADDITION;

        double total = calculate();
        chain.clear();
        return total;
    }

    private double addToChain(Operator operator, double input) {
        Command newLink = new Command(lastOperator, input);
        chain.add(newLink);
        lastOperator = operator;
        return calculate();
    }

    private double calculate() {
        Iterator<Command> commandIterator = chain.iterator();
        double total = 0;

        while (commandIterator.hasNext()) {
            Command current = commandIterator.next();
            switch (current.getOperator()) {
                case ADDITION:
                    total += current.getInput();
                    break;
                case SUBTRACTION:
                    total -= current.getInput();
                    break;
                case TIMES:
                    total *= current.getInput();
                    break;
                case DIV:
                    total /= current.getInput();
                    break;
                case EQUALS:
                default:
                    break;
            }
        }

        return total;
    }
}
