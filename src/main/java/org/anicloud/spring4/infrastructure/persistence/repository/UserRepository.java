package org.anicloud.spring4.infrastructure.persistence.repository;

import org.anicloud.spring4.infrastructure.persistence.model.UserDao;
import org.anicloud.spring4.infrastructure.persistence.repository.custom.UserRepositoryCustom;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Slice;
import org.springframework.data.domain.Sort;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.NoRepositoryBean;
import org.springframework.security.core.userdetails.User;

import java.awt.print.Pageable;
import java.util.List;

/**
 * Created by zhaoyu on 15-6-15.
 */
@NoRepositoryBean
public interface UserRepository extends CrudRepository<UserDao, Long>, UserRepositoryCustom {
    //Page<UserDao> findByLastName(String lastName, Pageable pageable);
    //Slice<UserDao> findByLastName(String lastName, Pageable pageable);

    List<User> findByLastName(String lastName, Sort sort);
    //List<User> findByLastName(String lastName, Pageable pageable);

    UserDao findFirstByOrderByLastNameAsc();
    UserDao findTopByOrderByAgeDesc();

    Page<UserDao> queryFirst10ByLastName(String lastName, Pageable pageable);
    Slice<UserDao> findTop3ByLastName(String lastName, Pageable pageable);
    List<UserDao> findFirst10ByLastName(String lastName, Sort sort);
    List<UserDao> findTop10ByLastName(String lastName, Pageable pageable);
}
