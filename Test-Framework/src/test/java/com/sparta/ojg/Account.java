package com.sparta.ojg;

import io.cucumber.core.internal.com.fasterxml.jackson.core.JsonProcessingException;
import io.cucumber.core.internal.com.fasterxml.jackson.databind.ObjectMapper;

import java.util.Map;

public class Account {
    ObjectMapper mapper = new ObjectMapper();

    private String username = "sparta";
    private String password = "global";

    public void setUsername(String username) {
        this.username = username;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getUsername() {
        return username;
    }

    public String getPassword() {
        return password;
    }

    public String getCredentialsAsJson(){
        //Create a JSON string containing the username and password in the format the API accepts
        try {
            return mapper.writeValueAsString(Map.of(
                    "username",username,
                    "password",password
            ));
        } catch (JsonProcessingException e) {
            throw new RuntimeException(e);
        }
    }
}
