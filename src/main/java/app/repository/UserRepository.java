package app.repository;

import app.HibernateUtil;
import app.service.UserService;
import org.hibernate.Session;

import javax.servlet.http.Cookie;
import java.util.HashMap;
import java.util.Map;

public class UserRepository {
    public boolean addUser(UserService user){
        try(Session session = HibernateUtil.getSession()){
            session.beginTransaction();
            session.persist(user);
            session.getTransaction().commit();
        }
        catch (Exception ex){
            System.out.println(ex.getMessage());
            return false;
        }
        return true;
    }
    public UserService getUserByLogin(String login){
        UserService user = null;
        try(Session session = HibernateUtil.getSession()){
            user = session.byNaturalId(UserService.class).using("login",login).load();
        }
        catch (Exception ex){
            System.out.println(ex.getMessage());
            return user;
        }
        return user;
    }
    public UserService getUserFromCookie(Cookie[] cookies){
        if(cookies !=null) {
            for(Cookie cookie: cookies) {
                if("login".equals(cookie.getName())) {
                    return this.getUserByLogin(cookie.getValue());
                }
            }
        }
        return null;
    }
}
