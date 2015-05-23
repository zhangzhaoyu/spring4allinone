package org.anicloud.spring4.json;

import com.google.gson.Gson;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

/**
 * Created by zhaoyu on 15-4-7.
 */
class BagOfPrimitives {
    private int value1 = 1;
    private String value2 = "abc";
    private transient int value3 = 3;

    BagOfPrimitives() {
    }

    @Override
    public String toString() {
        return "BagOfPrimitives{" +
                "value1=" + value1 +
                ", value2='" + value2 + '\'' +
                ", value3=" + value3 +
                '}';
    }
}

public class GsonTest {
    private Gson gson;

    @Before
    public void before() {
        gson = new Gson();
    }

    @Test
    public void testGsonSerialization() {
        print(gson.toJson(1));
        print(gson.toJson("abcd"));
    }

    @Test
    public void testGsonDeserialization() {
        Boolean flag = gson.fromJson("false", Boolean.class);
        assertFalse("false value", flag);
    }

    @Test
    public void testObjectSerialization() {
        BagOfPrimitives primitives = new BagOfPrimitives();
        String gsonString = gson.toJson(primitives);
        print(gson.toJson(primitives));
        print(gson.fromJson(gsonString, BagOfPrimitives.class));
    }

    private void print(Object object) {
        System.out.println(object);
    }
}
