package org.anicloud.spring4.infrastructure.persistence.repository.custom;

import org.anicloud.spring4.infrastructure.persistence.model.UserDao;

/**
 * Created by zhaoyu on 15-6-15.
 */
public interface UserRepositoryCustom {
    public void removeUser(UserDao userDao);
}
