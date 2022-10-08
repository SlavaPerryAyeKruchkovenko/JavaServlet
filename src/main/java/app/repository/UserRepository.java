package app.repository;

import app.service.UserService;

import javax.servlet.http.Cookie;
import java.util.HashMap;
import java.util.Map;

public class UserRepository {
    private final Map<String, UserService> users = new HashMap<>();

    public UserService getUserByLogin(String login){
        return users.get(login);
    }
    public boolean addUser(UserService user){
        String login = user.getLogin();
        if(users.containsKey(login)){
            return false;
        }
        else{
            users.put(login, user);
            return true;
        }
    }
    public UserService getUserFromCookie(Cookie[] cookies){
        UserService user = null;
        String login = null;
        String email = null;
        String password = null;
        if(cookies !=null) {
            for(Cookie cookie: cookies) {
                if("login".equals(cookie.getName())) {
                    login = cookie.getValue();
                }
                else if("email".equals(cookie.getName())) {
                    email = cookie.getValue();
                }
                else if("password".equals(cookie.getName())) {
                    password = cookie.getValue();
                }
            }
            if(login != null && email != null && password != null){
                user = new UserService(login,password,email);
            }
        }
        return user;
    }
}
