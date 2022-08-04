package com.moon.interview.calculator;

import java.util.stream.Stream;

/**
 * @author Chanmoey
 * @date 2022年08月04日
 */
public enum Operand {
    ADD('+', 1),
    SUBTRACT('-', 1),
    MULTIPLY('*', 10),
    DIVIDE('/', 10);

    private final char symbol;
    private final int priority;

    public char getSymbol() {
        return symbol;
    }

    public int getPriority() {
        return priority;
    }

    Operand(char symbol, int priority) {
        this.symbol = symbol;
        this.priority = priority;
    }

    public static Operand of(char symbol) {
        return Stream.of(values())
                .filter(s -> s.symbol == symbol)
                .findAny().orElseThrow(() -> new IllegalArgumentException("No Such symbol: " + symbol));
    }
}
