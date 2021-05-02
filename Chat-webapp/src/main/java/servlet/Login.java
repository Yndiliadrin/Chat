package servlet;

import Controller.Encrypt;
import hu.alkfejl.dao.UserDAO;
import hu.alkfejl.dao.UserDAOImpl;
import hu.alkfejl.model.User;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.io.PrintWriter;

@WebServlet("/login")
public class Login extends HttpServlet {

    private Encrypt encrypt = new Encrypt();
    private UserDAO dao;

    public Login() {
        dao = new UserDAOImpl();
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        RequestDispatcher view = req.getRequestDispatcher("pages/login.html");
        view.forward(req,resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        resp.setContentType("text/html; charset=UTF-8");
        HttpSession session = req.getSession();
        PrintWriter out = resp.getWriter();
        req.setCharacterEncoding("UTF-8");
        resp.setCharacterEncoding("UTF-8");
        if (req.getParameter("log") != null) {
            User user = dao.findUser(req.getParameter("uname"), encrypt.encryptIt(req.getParameter("pass")));
            if (user != null) {
                session.setAttribute("username", user.getUsername());
                session.setAttribute("uID", user.getID());
                session.setAttribute("status", "logged_in");
                session.setAttribute("child", "rooms");
                session.setAttribute("query", 1);
                resp.sendRedirect("home");
            } else {
                out.println("<script type =\"text/javascript\">\n" +
                        "            window.onload = function() {\n" +
                        "            what();\n" +
                        "            function what(){\n" +
                        "                document.getElementById('errorMsg').innerHTML = 'Rossz felhasználónév, vagy jelszó';\n" +
                        "            };\n" +
                        "        }\n" +
                        "        </script>");
                RequestDispatcher rd = req.getRequestDispatcher("/pages/login.html");
                rd.include(req,resp);
            }
        } else if (req.getParameter("reg") != null) {
            resp.sendRedirect("regiszt");
        } else if (req.getParameter("logOF") != null){
            session.invalidate();
            resp.sendRedirect("login");
        }else {
            resp.sendRedirect("login");
        }
    }
}
