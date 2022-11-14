package app.repository;

import app.HibernateUtil;
import app.service.UserService;
import org.hibernate.Session;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import javax.servlet.http.Cookie;

public class UserRepositorySql implements IUserRepository {
    private Connection connectionNow;
    public boolean addUser(UserService user){
        try{
            PreparedStatement st = getConnection().prepareStatement(
                    "INSERT INTO users (login, password, email) VALUES (?, ?, ?)");
            st.setString(1, user.getLogin());
            st.setString(2, user.getPassword());
            st.setString(3, user.getEmail());
            st.executeUpdate();
        }
        catch (Exception ex){
            System.out.println(ex.getMessage());
            return false;
        }
        return true;
    }
    public UserService getUserByLogin(String login){
        try{
            PreparedStatement st = getConnection().prepareStatement("SELECT login, password, email FROM users WHERE login = ?");
            st.setString(1, login);
            ResultSet rs = st.executeQuery();
            if (rs.next()) {
                return new UserService(
                        rs.getString("login"),
                        rs.getString("password"),
                        rs.getString("email"));
            } else {
                return null;
            }
        }
        catch (Exception ex){
            return  null;
        }
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
    private Connection getConnection() throws SQLException, ClassNotFoundException {
        if (connectionNow != null && !connectionNow.isClosed()) {
            return connectionNow;
        }

        Class.forName("com.mysql.cj.jdbc.Driver");
        connectionNow = DriverManager.getConnection("jdbc:mysql://localhost:3306/lab6", "root", "12345");
        return connectionNow;
    }
}
