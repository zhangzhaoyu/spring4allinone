package org.anicloud.spring4.infrastructure.jms;

import org.anicloud.spring4.application.dto.UserDto;

/**
 * Created by zhaoyu on 15-7-12.
 */
public interface AlertService {
    public void sendUserInfoDto(UserDto user);
}
