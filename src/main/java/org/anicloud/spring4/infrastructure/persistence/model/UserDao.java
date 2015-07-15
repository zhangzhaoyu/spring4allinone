package org.anicloud.spring4.infrastructure.persistence.model;

import javax.persistence.*;
import java.io.Serializable;

/**
 * Created by zhaoyu on 15-6-15.
 */
@Entity
@Table(name = "user")
public class UserDao implements Serializable {
    private static final long serialVersionUID = -4379265942371445048L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    public Long id;

    public String firstName;
    public String lastName;

    public int age;

    public UserDao() {
    }

    public UserDao(String firstName, String lastName) {
        this.firstName = firstName;
        this.lastName = lastName;
    }
}
