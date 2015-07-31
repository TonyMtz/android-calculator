package com.gamesrum.calculator;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class Engine {
    private List<Command> chain;

    public Engine() {
        this.chain = new ArrayList<>();
    }

    public double add(double input) {
        Command newLink = new Command(Operator.ADDITION, input);
        chain.add(newLink);
        return calculate();
    }

    public double sub(double input) {
        Command newLink = new Command(Operator.SUBTRACTION, input);
        chain.add(newLink);
        return calculate();
    }

    public double equals(double input) {
        Command newLink = new Command(Operator.EQUALS, input);
        chain.add(newLink);

        double total = calculate();
        chain.clear();
        return total;
    }

    private double calculate() {
        Iterator<Command> commandIterator = chain.iterator();
        double total = 0;
        Operator buffer = Operator.ADDITION;

        while (commandIterator.hasNext()) {
            Command current = commandIterator.next();
            switch (buffer) {
                case ADDITION:
                    buffer = current.getOperator();
                    total += current.getInput();
                    break;
                case SUBTRACTION:
                    buffer = current.getOperator();
                    total -= current.getInput();
                    break;
                case EQUALS:
                default:
                    break;
            }
        }

        return total;
    }
}
