package org.anicloud.spring4.junit;

import org.junit.Test;

import java.util.ArrayList;

import static org.junit.Assert.assertEquals;

/**
 * Created by zhaoyu on 15-4-7.
 */
public class CalculatorTest {
    @Test
    public void evaluatesExpression() {
        Calculator calculator = new Calculator();
        int sum = calculator.evaluate("1+2+3");
        assertEquals(6, sum);
    }

    @Test(expected = IndexOutOfBoundsException.class)
    public void expectedException() {
        new ArrayList<Object>().get(0);
    }
}
