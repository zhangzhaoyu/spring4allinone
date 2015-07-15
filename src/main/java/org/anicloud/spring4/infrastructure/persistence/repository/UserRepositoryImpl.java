package org.anicloud.spring4.infrastructure.persistence.repository;

import org.anicloud.spring4.infrastructure.persistence.model.UserDao;
import org.anicloud.spring4.infrastructure.persistence.repository.custom.UserRepositoryCustom;
import org.springframework.data.repository.NoRepositoryBean;
import org.springframework.stereotype.Repository;

/**
 * Created by zhaoyu on 15-6-15.
 */
@NoRepositoryBean
public class UserRepositoryImpl implements UserRepositoryCustom {
    public void removeUser(UserDao userDao) {

    }
}
