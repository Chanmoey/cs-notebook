package com.moon.interview.calculator;

import java.util.ArrayDeque;
import java.util.Deque;


/**
 * @author Chanmoey
 * @date 2022年08月04日
 */
public class CalculatorConSole implements ICalculator {

    @Override
    public Deque<Character> makePostfix(String inputExpr) {
        char[] expr = inputExpr.replace(" ", "").toCharArray();
        Deque<Operand> operandStack = new ArrayDeque<>();
        Deque<Character> postfix = new ArrayDeque<>();

        for (char c : expr) {
            if (Character.isDigit(c)) {
                postfix.addLast(c);
            } else {
                Operand operand = Operand.of(c);
                while (!operandStack.isEmpty()) {
                    if (operandStack.peek().getPriority() >= operand.getPriority()) {
                        postfix.addLast(operandStack.removeLast().getSymbol());
                    } else {
                        operandStack.addLast(operand);
                        break;
                    }
                }
                if (operandStack.isEmpty()) {
                    operandStack.addLast(operand);
                }
            }
        }
        while (!operandStack.isEmpty()) {
            postfix.addLast(operandStack.removeLast().getSymbol());
        }
        return postfix;
    }

    @Override
    public double calculateByPostfix(Deque<Character> postfix) {
        Deque<Double> resStack = new ArrayDeque<>();
        for (Character c : postfix.stream().toList()) {
            if (Character.isDigit(c)) {
                resStack.add(Double.parseDouble(String.valueOf(c)));
            } else {
                Double a = resStack.removeLast();
                Double b = resStack.removeLast();
                switch (Operand.of(c)) {
                    case ADD -> resStack.addLast(b + a);

                    case SUBTRACT -> resStack.addLast(b - a);

                    case MULTIPLY -> resStack.addLast(b * a);

                    case DIVIDE -> resStack.addLast(b / a);
                }
            }
        }
        return resStack.getFirst();
    }
}
