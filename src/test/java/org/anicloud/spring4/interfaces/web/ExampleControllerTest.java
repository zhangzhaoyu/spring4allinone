package org.anicloud.spring4.interfaces.web;

import com.thoughtworks.xstream.io.xml.StaxDriver;
import org.anicloud.spring4.application.dto.UserDto;
import org.junit.Test;
import org.springframework.http.*;
import org.springframework.http.converter.StringHttpMessageConverter;
import org.springframework.http.converter.json.MappingJackson2HttpMessageConverter;
import org.springframework.http.converter.xml.MarshallingHttpMessageConverter;
import org.springframework.oxm.xstream.XStreamMarshaller;
import org.springframework.web.client.RestTemplate;

import java.util.Collections;

/**
 * Created by zhaoyu on 15-6-9.
 */
public class ExampleControllerTest {

    private final static String url = "http://localhost:8080/spring4/example/handler22";

    @Test
    public void testHandler22() {
        RestTemplate restTemplate = buildRestTemplate();
        UserDto userDto = new UserDto();

        HttpHeaders httpHeaders = new HttpHeaders();
        httpHeaders.setContentType(MediaType.APPLICATION_XML);
        httpHeaders.setAccept(Collections.singletonList(MediaType.APPLICATION_XML));

        HttpEntity<UserDto> requestEntity = new HttpEntity<UserDto>(userDto, httpHeaders);
        /*ResponseEntity<UserDto> responseEntity = restTemplate.exchange(url, HttpMethod.POST, requestEntity, UserDto.class, userDto);
        userDto = requestEntity.getBody();*/
        userDto = restTemplate.postForObject(url, requestEntity, UserDto.class, userDto);

        System.out.println(userDto);
    }

    private RestTemplate buildRestTemplate() {
        RestTemplate restTemplate = new RestTemplate();

        XStreamMarshaller xStreamMarshaller = new XStreamMarshaller();
        xStreamMarshaller.setStreamDriver(new StaxDriver());
        xStreamMarshaller.setAnnotatedClasses(new Class[] {UserDto.class});

        MarshallingHttpMessageConverter marshallingHttpMessageConverter =
                new MarshallingHttpMessageConverter();
        marshallingHttpMessageConverter.setMarshaller(xStreamMarshaller);
        marshallingHttpMessageConverter.setUnmarshaller(xStreamMarshaller);

        restTemplate.getMessageConverters().add(marshallingHttpMessageConverter);
        MappingJackson2HttpMessageConverter jackson2HttpMessageConverter =
                new MappingJackson2HttpMessageConverter();
        restTemplate.getMessageConverters().add(jackson2HttpMessageConverter);

        StringHttpMessageConverter stringHttpMessageConverter = new StringHttpMessageConverter();
        restTemplate.getMessageConverters().add(stringHttpMessageConverter);

        return restTemplate;
    }
}
