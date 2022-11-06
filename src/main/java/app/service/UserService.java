package app.service;
import jakarta.persistence.*;
import org.hibernate.annotations.NaturalId;

@Entity
@Table(name = "users")
public class UserService {
    private Integer idUsers;
    private String login;
    private String password;
    private String email;

    public UserService(String login,String password,String email){
        this.login = login;
        this.password = password;
        this.email = email;
    }
    public UserService(){

    }
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    public Integer getIdUsers(){return this.idUsers;}
    public void setIdUsers(Integer value){
        this.idUsers = value;
    }
    @NaturalId
    public String getLogin() {
        return this.login;
    }
    public void setLogin(String value) {
        this.login = value;
    }
    public String getPassword() {
        return this.password;
    }
    public void setPassword(String value) {
        this.password = value;
    }
    public String getEmail() {
        return this.email;
    }
    public void setEmail(String value) {
        this.email = value;
    }

}
