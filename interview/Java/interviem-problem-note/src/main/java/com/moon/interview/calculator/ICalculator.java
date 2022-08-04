package com.moon.interview.calculator;

import java.util.Deque;

/**
 * @author Chanmoey
 * @date 2022年08月04日
 */
public interface ICalculator {

    Deque<Character> makePostfix(String s);

    double calculateByPostfix(Deque<Character> postfix);
}
