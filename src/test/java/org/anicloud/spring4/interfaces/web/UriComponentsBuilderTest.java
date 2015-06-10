package org.anicloud.spring4.interfaces.web;

import org.junit.Before;
import org.junit.Test;
import org.springframework.web.util.UriComponents;
import org.springframework.web.util.UriComponentsBuilder;

/**
 * Created by zhaoyu on 15-6-10.
 */
public class UriComponentsBuilderTest {

    @Before
    public void before() {
    }

    @Test
    public void testBuilder() {
        UriComponentsBuilder uriComponentsBuilder =
                UriComponentsBuilder.fromHttpUrl("http://localhost:8080/spring4").queryParam("age", 12);
        UriComponents uriComponents = uriComponentsBuilder.build();
        System.out.println(uriComponents.toString());
    }
}
