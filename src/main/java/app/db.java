package app;

import app.repository.IUserRepository;
import app.repository.UserRepository;
import app.repository.UserRepositorySql;

import java.sql.Connection;
import java.sql.DriverManager;
public class db {
    public static IUserRepository userRepository = new UserRepository();
}
