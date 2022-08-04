package com.moon.interview.calculator;

import java.util.Deque;
import java.util.Scanner;

/**
 * @author Chanmoey
 * @date 2022年08月04日
 */
public class CalculatorConSoleMain {

    public static void main(String[] args) {

        CalculatorConSole calculatorConSole = new CalculatorConSole();
        System.out.print("expr> ");
        Scanner scanner = new Scanner(System.in);
        String instruction = scanner.nextLine();
        while (!"exit".equals(instruction)) {
            Deque<Character> postfix = calculatorConSole.makePostfix(instruction);
            System.out.println(calculatorConSole.calculateByPostfix(postfix));
            System.out.print("expr> ");
            instruction = scanner.nextLine();
        }
    }
}
