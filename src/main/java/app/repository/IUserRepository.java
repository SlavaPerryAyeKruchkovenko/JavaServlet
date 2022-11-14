package app.repository;

import app.service.UserService;

import javax.servlet.http.Cookie;

public interface IUserRepository {
    public boolean addUser(UserService user);
    public UserService getUserByLogin(String login);
    public UserService getUserFromCookie(Cookie[] cookies);
}
