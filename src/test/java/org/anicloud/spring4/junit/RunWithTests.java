package org.anicloud.spring4.junit;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Suite;

/**
 * Created by zhaoyu on 15-4-8.
 */
@RunWith(Suite.class)
@Suite.SuiteClasses({
        CalculatorTest.class,
        AssertTests.class
})
public class RunWithTests {

    @Test
    public void testRunWith() {
        System.out.println("run test with.");
    }

}
