package org.anicloud.spring4.junit;

/**
 * Created by zhaoyu on 15-4-7.
 */
public class Calculator {
    public int evaluate(String expression) {
        int sum = 0;
        for (String summand : expression.split("\\+")) {
            sum += Integer.valueOf(summand);
        }
        return sum;
    }
}
