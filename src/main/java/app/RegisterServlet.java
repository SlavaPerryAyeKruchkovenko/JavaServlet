package app;

import app.service.UserService;
import org.hibernate.Session;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class RegisterServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws IOException, ServletException {
        RequestDispatcher requestDispatcher = req.getRequestDispatcher("register.jsp");
        requestDispatcher.forward(req, resp);
    }
    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        String login = req.getParameter("login");
        String email = req.getParameter("email");
        String password = req.getParameter("password");

        if (login != null && password != null && email != null) {
            UserService user = new UserService(login,password,email);
            if(db.userRepository.addUser(user)){
                resp.sendRedirect("./login");
            }
            else{
                resp.sendRedirect("./register");
            }
        }
    }
}
