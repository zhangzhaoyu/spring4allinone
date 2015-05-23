package org.anicloud.spring4.junit;

import org.hamcrest.core.CombinableMatcher;
import org.junit.Test;

import static org.hamcrest.Matchers.*;
import static org.junit.Assert.*;

/**
 * Created by zhaoyu on 15-4-7.
 */
public class AssertTests {
    @Test
    public void testAssertArrayEquals() {
        byte[] expected = "zhang".getBytes();
        byte[] actual = "zhang".getBytes();

        assertArrayEquals("failure - byte array not same", expected, actual);
    }

    @Test
    public void testAssertEquals() {
        assertEquals("failure - strings are not equal", "zhang", "zhang");
    }

    @Test
    public void testAssertFalse() {
        assertFalse("failure - should be false", false);
    }

    @Test
    public void testAssertNotNull() {
        assertNotNull("Should not be null.", new Object());
    }

    @Test
    public void testAssertThatBothContainsString() {
        assertThat("albumen", both(containsString("a")).and(containsString("b")));
    }

    @Test
    public void testAssertThatHamcrestCoreMathers() {
        assertThat("good", allOf(equalTo("good"), startsWith("good")));
        assertThat("good", not(allOf(equalTo("bad"), equalTo("good"))));
        assertThat("good", anyOf(equalTo("bad"), equalTo("good")));
        assertThat(7, not(CombinableMatcher.<Integer> either(equalTo(3)).or(equalTo(4))));
        assertThat(new Object(), not(sameInstance(new Object())));
    }
}
