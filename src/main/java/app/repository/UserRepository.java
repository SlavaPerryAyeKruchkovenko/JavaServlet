package app.repository;

import app.service.UserService;

import java.util.HashMap;
import java.util.Map;

public class UserRepository {
    private final Map<String, UserService> users = new HashMap<>();

    public UserService getUserByLogin(String login){
        return users.get(login);
    }
    public void addUser(){

    }
}
