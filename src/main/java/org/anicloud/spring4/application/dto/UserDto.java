package org.anicloud.spring4.application.dto;

import org.apache.commons.lang.builder.ToStringBuilder;
import org.apache.commons.lang.builder.ToStringStyle;
import org.hibernate.validator.constraints.Length;
import org.springframework.format.annotation.DateTimeFormat;

import javax.validation.constraints.DecimalMax;
import javax.validation.constraints.DecimalMin;
import javax.validation.constraints.Past;
import javax.validation.constraints.Pattern;
import java.io.Serializable;
import java.util.Date;

/**
 * Created by zhaoyu on 15-6-9.
 */
public class UserDto implements Serializable, Cloneable {
    private static final long serialVersionUID = 1638073335646792401L;

    public int id;

    @Pattern(regexp="w{4,30}")
    public String userName;

    @Pattern(regexp = "S{6,30}")
    public String password;

    @Length(min = 2, max = 100)
    public String realName;

    @Past
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    public Date birthday;

    @DecimalMin(value = "1000.00")
    @DecimalMax(value = "1000000.00")
    public long salary;

    public UserDto() {
    }

    public UserDto(int id, String userName) {
        this.id = id;
        this.userName = userName;
    }

    public Date getBirthday() {
        return birthday;
    }

    public void setBirthday(Date birthday) {
        this.birthday = birthday;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getRealName() {
        return realName;
    }

    public void setRealName(String realName) {
        this.realName = realName;
    }

    public long getSalary() {
        return salary;
    }

    public void setSalary(long salary) {
        this.salary = salary;
    }

    public static long getSerialVersionUID() {
        return serialVersionUID;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    @Override
    public UserDto clone() {
        UserDto userDto = null;
        try {
            userDto = (UserDto) super.clone();
        } catch (CloneNotSupportedException e) {
            e.printStackTrace();
        }
        return userDto;
    }

    @Override
    public String toString() {
        return ToStringBuilder.reflectionToString(this, ToStringStyle.SIMPLE_STYLE);
    }
}
