package org.anicloud.spring4.infrastructure.persistence.converter;


import org.anicloud.spring4.application.dto.UserDto;
import org.springframework.core.convert.converter.Converter;

/**
 * Created by zhaoyu on 15-6-10.
 */
public class StringToUserConverter implements Converter<String, UserDto> {

    @Override
    public UserDto convert(String s) {
        UserDto user = new UserDto();
        if (s != null) {
            String[] items = s.split(":");
            user.id = Integer.valueOf(items[0]);
            user.userName = user.userName;
        }
        return user;
    }
}
