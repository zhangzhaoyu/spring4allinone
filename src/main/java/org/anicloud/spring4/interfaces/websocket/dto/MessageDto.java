package org.anicloud.spring4.interfaces.websocket.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

/**
 * Created by zhaoyu on 15-5-23.
 */
public class MessageDto {
    @JsonProperty(value = "on_line_users")
    private Set<String> onLineUsers = new HashSet<String>();
    private String msg;

    public MessageDto() {
    }

    public MessageDto(Set<String> onLineUsers, String msg) {
        this.onLineUsers = onLineUsers;
        this.msg = msg;
    }

    public Set<String> getOnLineUsers() {
        return onLineUsers;
    }

    public void setOnLineUsers(Set<String> onLineUsers) {
        this.onLineUsers = onLineUsers;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    public String toJson() {
        ObjectMapper mapper = new ObjectMapper();
        String result = "";
        try {
            result = mapper.writeValueAsString(this);
        } catch (JsonProcessingException e) {
            e.printStackTrace();
        }
        return result;
    }
}
