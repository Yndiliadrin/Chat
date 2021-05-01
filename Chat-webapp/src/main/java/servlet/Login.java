package servlet;

import Controller.Encrypt;
import Controller.UserController;
import Controller.UserControllerImpl;
import hu.alkfejl.model.User;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

@WebServlet("/login")
public class Login extends HttpServlet {

    private UserController userController = new UserControllerImpl();
    private Encrypt encrypt = new Encrypt();

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        RequestDispatcher view = req.getRequestDispatcher("pages/login.html");
        view.forward(req,resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        HttpSession session = req.getSession();
        if (req.getParameter("log") != null) {
            User user = userController.login(req.getParameter("uname"), encrypt.encryptIt(req.getParameter("pass")));
            if (user != null) {
                session.setAttribute("username", user.getUsername());
                session.setAttribute("uID", user.getID());
                session.setAttribute("status", "logged_in");
                session.setAttribute("child", "rooms");
                session.setAttribute("query", 1);
                System.out.println("session created " + (String) session.getAttribute("username"));
                resp.sendRedirect("home");
            }
        } else if (req.getParameter("reg") != null) {
            resp.sendRedirect("regiszt");
        } else if (req.getParameter("logOF") != null){
            session.invalidate();
            resp.sendRedirect("login");
            System.out.println("logged_of");
        }else {
            System.out.println("KYS");
            resp.sendRedirect("login");
        }
    }
}
